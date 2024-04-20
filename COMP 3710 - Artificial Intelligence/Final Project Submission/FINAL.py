import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import pickle
import nltk
from collections import Counter
from datasets import ClassLabel
import matplotlib.pyplot as plt
import numpy as np

# Load the data from the database
with open("hexcolor_vf/train_names.pkl", 'rb') as fin:
    src_seqs = pickle.load(fin)  # Load source sequences

with open("hexcolor_vf/train_palettes_rgb.pkl", 'rb') as fin:
    trg_seqs = pickle.load(fin)  # Load target palettes


## Define the Conditional Generator

class ConditionalGenerator(nn.Module):
    def __init__(self, latent_dim, palette_size, text_dim):
        super(ConditionalGenerator, self).__init__()
        self.latent_dim = latent_dim  # Dimension of the latent noise vector
        self.palette_size = palette_size  # Number of colors in the generated palette
        self.text_dim = text_dim  # Dimension of the text input representation (numerical encoding)

        self.model = nn.Sequential(
            nn.Linear(latent_dim + text_dim, 256),  # Combine latent vector and text input
            nn.LeakyReLU(0.2),  # Leaky ReLU activation for non-linearity
            nn.Linear(256, 512),  # Increase feature representation size
            nn.BatchNorm1d(512),  # Batch normalization for gradient stability
            nn.LeakyReLU(0.2),  # Leaky ReLU activation for non-linearity
            nn.Linear(512, palette_size * 3),  # Output layer for RGB values of each color
            nn.Tanh()  # Tanh activation to normalize output between -1 and 1 (suitable for RGB)
        )

    def forward(self, z, text_input):
        input = torch.cat([z, text_input], dim=1)  # Concatenate latent vector and text input
        return self.model(input).view(-1, self.palette_size, 3)  # Reshape output to palette size x 3


# Define the Conditional Discriminator
class ConditionalDiscriminator(nn.Module):
    def __init__(self, palette_size, text_dim):
        super(ConditionalDiscriminator, self).__init__()
        self.palette_size = palette_size  # Number of colors in the input palette
        self.text_dim = text_dim  # Dimension of the text input representation (numerical encoding)

        self.model = nn.Sequential(
            nn.Linear(palette_size * 3 + text_dim, 256),  # Combine flattened palette and text input
            nn.LeakyReLU(0.2),  # Leaky ReLU activation for non-linearity
            nn.Linear(256, 128),  # Decrease feature representation size
            nn.BatchNorm1d(128),  # Batch normalization for gradient stability
            nn.LeakyReLU(0.2),  # Leaky ReLU activation for non-linearity
            nn.Linear(128, 1),  # Output layer for discrimination score
            nn.Sigmoid()  # Sigmoid activation to output probability between 0 and 1
        )

    def forward(self, x, text_input):
        input = torch.cat([x.view(-1, palette_size * 3), text_input], dim=1)  # Flatten palette and concatenate with text input
        return self.model(input)  # Pass concatenated input through the network


# Training loop
def train(generator, discriminator, data_loader, num_epochs, latent_dim, text_dim):
    criterion = nn.BCELoss()  # Loss function for binary cross-entropy (suitable for discriminator)
    g_optimizer = optim.Adam(generator.parameters(), lr=0.0001)  # Optimizer for generator with learning rate 0.0001
    d_optimizer = optim.Adam(discriminator.parameters(), lr=0.0001)  # Optimizer for discriminator with learning rate 0.0001

    for epoch in range(num_epochs):
        for real_palettes, text_inputs in data_loader:
            # Train the Discriminator
            z = torch.randn(real_palettes.size(0), latent_dim)  # Create random noise vector for generator
            fake_palettes = generator(z, text_inputs)  # Generate fake palettes conditioned on text inputs

            d_real = discriminator(real_palettes, text_inputs)  # Discriminator's output for real palettes
            d_fake = discriminator(fake_palettes.detach(), text_inputs)  # Discriminator's output for detached fake palettes (prevents gradients flowing back to generator)

            # Discriminator loss: combines loss for real and fake palettes
            d_loss = criterion(d_real.squeeze(), torch.ones_like(d_real).squeeze()) + criterion(d_fake.squeeze(), torch.zeros_like(d_fake).squeeze())

            d_optimizer.zero_grad()  # Reset gradients for discriminator
            d_loss.backward()  # Backpropagate loss through discriminator
            d_optimizer.step()  # Update discriminator weights

            # Train the Generator
            z = torch.randn(real_palettes.size(0), latent_dim)  # Create random noise for generator
            fake_palettes = generator(z, text_inputs)  # Generate fake palettes conditioned on text inputs

            # Generator loss: aims to fool the discriminator into classifying fake palettes as real
            g_loss = criterion(discriminator(fake_palettes, text_inputs).squeeze(), torch.ones_like(d_real).squeeze())

            g_optimizer.zero_grad()  # Reset gradients for generator
            g_loss.backward()  # Backpropagate loss through generator
            g_optimizer.step()  # Update generator weights

            print(f"Epoch {epoch+1}/{num_epochs}, Generator Loss: {g_loss.item():.4f}, Discriminator Loss: {d_loss.item():.4f}")

# Preprocess color palettes
def preprocess_color_palettes(color_palettes):
    # Convert color palettes to PyTorch tensor and normalize (if necessary)
    color_palettes = torch.tensor(color_palettes, dtype=torch.float32)

    return color_palettes

# Preprocess prompts (text descriptions)
def preprocess_prompts(prompts):
    # Find the maximum length of inner lists in the prompts data
    max_length = max(len(sublist) for sublist in prompts)

    # Pad shorter sublists with empty strings to match the maximum length
    padded_list = [sublist + [''] * (max_length - len(sublist)) for sublist in prompts]

    # Create a dictionary mapping unique words to numerical indices
    word_to_index = {word: i for i, word in enumerate(sorted(set(word for sublist in padded_list for word in sublist)))}

    # Create a reverse mapping from indices to words
    index_to_word = {index: word for word, index in word_to_index.items()}

    # Convert each sublist of words into a tensor of numerical indices
    text_inputs = [[word_to_index[word] for word in sublist] for sublist in padded_list]
    indexed_prompts = torch.tensor(text_inputs).to(torch.float32)  # Convert to tensor and move to GPU (if available)

    return indexed_prompts, index_to_word

# Preprocess color palettes
color_palettes_tensor = preprocess_color_palettes(trg_seqs)

# Preprocess prompts
text_inputs, index_to_word = preprocess_prompts(src_seqs)  # Assuming the maximum prompt length is 11 (modify if needed)

# Create PyTorch dataset and dataloader
dataset = TensorDataset(color_palettes_tensor, text_inputs)
data_loader = DataLoader(dataset, batch_size=9165, shuffle=True)

# Hyperparameters
palette_size = 5  # Number of colors in the generated palette
latent_dim = 100  # Dimension of the latent noise vector
text_dim = 11  # Dimension of the text input representation (number of unique words)

# Create the Generator and Discriminator
generator = ConditionalGenerator(latent_dim, palette_size, text_dim)
discriminator = ConditionalDiscriminator(palette_size, text_dim)

# Train the conditional cGAN
train(generator, discriminator, data_loader, num_epochs=10, latent_dim=latent_dim, text_dim=text_dim)

# Generate new color palettes
z = torch.randn(5, latent_dim)  # Create random noise for 5 palettes
text_inputs = torch.tensor([
    [1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    [0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    [0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    [0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    [0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
])

# Convert text inputs to lists
text_inputs_list = text_inputs.tolist()

# Convert each numerical list to prompt strings
prompts = [' '.join([index_to_word[int(value)] for value in sublist if value != 0]) for sublist in text_inputs.tolist()]

# Generate new color palettes
new_palettes = generator(z, text_inputs)

def visualize_color_palette(color_palette, is_original=False):
    plt.figure(figsize=(5, 1))  # Set the figure size
    for i in range(len(color_palette)):
        if is_original:
            # Assume original palette data is in 0-255 range
            color = [value / 255 for value in color_palette[i]]
        else:
            # Generated palette data is already in 0-1 range
            color = color_palette[i].detach().numpy()

        color = np.clip(color, 0, 1)  # Clip values to [0, 1] range
        plt.fill_between([i, i + 1], 0, 1, color=color)  # Plot each color as a filled rectangle
    plt.axis('off')  # Turn off axis
    plt.show()  # Show the plot


def showOriginal(original_index):
    # Check if the index is within the valid range
    if 0 <= original_index < len(trg_seqs):
        try:
            original_palette = trg_seqs[original_index]
            print(f"Original color palette for index {original_index}:")
            visualize_color_palette(torch.tensor(original_palette, dtype=torch.float32))
        except IndexError:
            print(f"No original palette found for index {original_index}")
    else:
        print(f"No original palette found for index {original_index}")

generated_palettes = []

for i, (palette, prompt) in enumerate(zip(new_palettes, src_seqs)):
    print(f"Prompt for color palette {i+1}: {' '.join(prompt)}")  # Display the prompt along with the color palette
    
    print(f"Generated color palette {i+1}:")
    visualize_color_palette(palette)

    print(f"Original color palette for index {i+1}:")
    visualize_color_palette(trg_seqs[i], is_original=True)

    generated_palettes.append(palette)

