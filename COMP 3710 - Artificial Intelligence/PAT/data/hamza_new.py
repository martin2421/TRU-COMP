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


def filter_single_word(src_seqs):
    filtered_seqs = [seq for seq in src_seqs if len(seq) == 1]
    return filtered_seqs


# Load the data from the database
with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_names.pkl', 'rb') as fin:
    src_seqs = pickle.load(fin)  # Load source sequences
    filtered_src_seqs = filter_single_word(
        src_seqs)  # Filter single-word prompts

with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_palettes_rgb.pkl', 'rb') as fin:
    trg_seqs = pickle.load(fin)  # Load target palettes
    # Assuming filtered_seqs contains single-word prompts after filtering src_seqs
    filtered_trg_seqs = []
    for i, prompt in enumerate(src_seqs):
        if len(prompt) == 1:  # Assuming single-word prompts
            filtered_trg_seqs.append(trg_seqs[i])

BATCH_SIZE = 64  # Batch size for training
NUM_EPOCHS = 5  # Number of training epochs
clip_value = 5.0  # Gradient clipping value


# Define the Conditional Generator
class ConditionalGenerator(nn.Module):
    def __init__(self, latent_dim, palette_size, text_dim, hidden_dim=256, num_layers=2):
        super(ConditionalGenerator, self).__init__()
        self.latent_dim = latent_dim  # Dimension of the latent noise vector
        self.palette_size = palette_size  # Number of colors in the generated palette
        # Dimension of the text input representation (numerical encoding)
        self.text_dim = text_dim
        self.hidden_dim = hidden_dim
        self.num_layers = num_layers

        # Define the hidden layers
        self.model = nn.Sequential()
        self.model.add_module('linear1', nn.Linear(
            latent_dim + text_dim, hidden_dim))
        self.model.add_module('act1', nn.LeakyReLU(0.2))
        for i in range(num_layers - 1):  # Add additional hidden layers
            self.model.add_module(
                f'linear{i+2}', nn.Linear(hidden_dim, hidden_dim))
            self.model.add_module(f'act{i+2}', nn.LeakyReLU(0.2))

        self.model.add_module('linear_final', nn.Linear(
            hidden_dim, palette_size * 3))
        # Output layer for RGB values
        self.model.add_module('tanh_final', nn.Tanh())

    def forward(self, z, text_input):
        text_input = text_input.unsqueeze(1)
        input = torch.cat([z, text_input], dim=1)
        return self.model(input).view(-1, self.palette_size, 3)

# Define the Conditional Discriminator


class ConditionalDiscriminator(nn.Module):
    def __init__(self, palette_size, text_dim, hidden_dim=128, num_layers=2):
        super(ConditionalDiscriminator, self).__init__()
        self.palette_size = palette_size
        self.text_dim = text_dim
        self.hidden_dim = hidden_dim  # New parameter for hidden layer dimension
        self.num_layers = num_layers  # New parameter for number of hidden layers

        # Define the hidden layers
        self.model = nn.Sequential()
        self.model.add_module('linear1', nn.Linear(
            palette_size * 3 + text_dim, hidden_dim))
        self.model.add_module('act1', nn.LeakyReLU(0.2))
        for i in range(num_layers - 1):  # Add additional hidden layers
            self.model.add_module(
                f'linear{i+2}', nn.Linear(hidden_dim, hidden_dim))
            self.model.add_module(f'act{i+2}', nn.LeakyReLU(0.2))

        self.model.add_module('linear_final', nn.Linear(hidden_dim, 1))
        # Output layer for probability
        self.model.add_module('sigmoid_final', nn.Sigmoid())

    def forward(self, x, text_input):
        print(f"Shape of x: {x.size()}")
        print(f"Original shape of text_input: {text_input.size()}")
        print(f"Original data type of text_input: {text_input.dtype}")
        text_input = text_input.view(text_input.size(0), -1, 1)
        print(f"Shape of text_input after view(): {text_input.size()}")
        total_elements = text_input.numel()
        print(f"Total elements: {total_elements}")
        num_non_zero = text_input.sum()
        vocabulary_size = int(num_non_zero.item())
        print(f"Vocabulary size: {vocabulary_size}")
        text_input = text_input.view(text_input.size(0), -1, 1)
        print(f"Shape of text_input after reshape: {text_input.size()}")
        input = torch.cat([x, text_input], dim=1)
        print(f"Shape of input after cat(): {input.size()}")
        return self.model(input)

# Training loop
def train(generator, discriminator, data_loader, num_epochs, latent_dim, text_dim):
    # Loss function for binary cross-entropy (suitable for discriminator)
    criterion = nn.MSELoss()
    # Optimizer for generator with learning rate 0.0001
    g_optimizer = optim.Adam(generator.parameters(), lr=0.0001)
    # Optimizer for discriminator with learning rate 0.0001
    d_optimizer = optim.Adam(discriminator.parameters(), lr=0.0001)

    for epoch in range(num_epochs):
        for real_palettes, text_inputs in data_loader:
            # Train the Discriminator
            # Create random noise vector for generator
            z = torch.randn(real_palettes.size(0), latent_dim)
            # Generate fake palettes conditioned on text inputs
            fake_palettes = generator(z, text_inputs)

            print(f"Shape of real_palettes: {real_palettes.size()}")
            
            # Discriminator's output for real palettes
            d_real = discriminator(real_palettes, text_inputs)
            # Discriminator's output for detached fake palettes (prevents gradients flowing back to generator)
            d_fake = discriminator(fake_palettes.detach(), text_inputs)

            # Discriminator loss: combines loss for real and fake palettes
            d_loss = criterion(d_real.squeeze(), torch.ones_like(d_real).squeeze(
            )) + criterion(d_fake.squeeze(), torch.zeros_like(d_fake).squeeze())

            d_optimizer.zero_grad()  # Reset gradients for discriminator
            d_loss.backward()  # Backpropagate loss through discriminator
            d_optimizer.step()  # Update discriminator weights

            # Train the Generator
            # Create random noise for generator
            z = torch.randn(real_palettes.size(0), latent_dim)
            
            # Generate fake palettes conditioned on text inputs
            fake_palettes = generator(z, text_inputs)

            # Generator loss: aims to fool the discriminator into classifying fake palettes as real
            g_loss = criterion(discriminator(
                fake_palettes, text_inputs).squeeze(), torch.ones_like(d_real).squeeze())

            g_optimizer.zero_grad()  # Reset gradients for generator
            fake_palettes = generator(z, text_inputs)
            g_loss = criterion(fake_palettes, real_palettes)
            g_loss.backward()  # Backpropagate loss through generator
            nn.utils.clip_grad_norm_(generator.parameters(), clip_value)
            g_optimizer.step()  # Update generator weights

            print(
                f"Epoch {epoch+1}/{num_epochs}, Generator Loss: {g_loss.item():.4f}, Discriminator Loss: {d_loss.item():.4f}")

# Preprocess color palettes
def preprocess_color_palettes(color_palettes):
    # Convert color palettes to PyTorch tensor and normalize (if necessary)
    color_palettes = torch.tensor(color_palettes, dtype=torch.float32)
    
    # Reshape if necessary to ensure dimensions (batch_size, palette_size, 3)
    if len(color_palettes.size()) == 2:
        print("Inside if statement for unsqueeze")
        color_palettes = color_palettes.unsqueeze(2)  # Add missing channel dimension

    return color_palettes


def convert_words_to_numbers(words):
    # Create a word-to-number mapping dictionary
    word_to_index = {}
    words = [item for sublist in words for item in sublist]
    index = 0
    for word in set(words):  # Get unique words
        word_to_index[word] = index
        index += 1

    # Convert words to numbers
    numbers = []
    for word in words:
        if word in word_to_index:
            numbers.append(word_to_index[word])
        else:
            # Handle unknown words (e.g., assign a special index or ignore)
            numbers.append(-1)  # Example: Assign -1 for unknown words
    
    numbers = torch.tensor(numbers, dtype=torch.float32)
    return numbers, word_to_index

# Preprocess prompts (text descriptions)
def preprocess_prompts(prompts):
    # Create a dictionary mapping unique words to numerical indices
    unique_words = []
    for sublist in prompts:
        if len(sublist) == 1:
            unique_words.append(sublist[0])
    unique_words = sorted(set(unique_words))
    word_to_index = {word: i for i, word in enumerate(unique_words)}

    # Only consider single-word prompts for one-hot encoding
    text_inputs = torch.zeros(len([sublist for sublist in prompts if len(
        sublist) == 1]), len(word_to_index))  # Adjust size based on single-word prompts
    index = 0
    print("TEXT INPUTS BEFORE LOOP: ", text_inputs)
    print(f"Shape of text_inputs before loop: {text_inputs.size()}")
    print(f"Data type of text_inputs: {text_inputs.dtype}")
    text_inputs.requires_grad = False
    for i, sublist in enumerate(prompts):
        if len(sublist) == 1:
            word_index = word_to_index[sublist[0]]
            # Print word_index for verification
            print(f"Word index for prompt {i}: {word_index}")
            scatter_value = 1.0  # Value to scatter (should be 1.0)
            scatter_indices = torch.tensor([[index, word_index]])  # Indices for scattering
             # Print values for verification
            print(f"Scatter value: {scatter_value}")
            print(f"Scatter indices: {scatter_indices}")
            # Use scatter_ to set a single active element (1.0)
            text_inputs.scatter_(1, torch.tensor([[index, word_index]]), 1.0)
            # Print text_inputs after scatter for verification
            print(f"text_inputs after scatter for prompt {i}: {text_inputs[index]}")
            index += 1  # Increment index for next single-word prompt
            
    print("TEXT INPUTS AFTER LOOP: ", text_inputs)
    return text_inputs, word_to_index


# Preprocess color palettes (no change)
color_palettes_tensor = preprocess_color_palettes(filtered_trg_seqs)

# Preprocess prompts
# Call the function to get text_inputs
text_inputs, word_to_index = convert_words_to_numbers(filtered_src_seqs)
print("text_inputs: ", text_inputs)
print("SHAPE OF TEXT INPUTS 2: ", text_inputs.shape)

# Create PyTorch dataset and dataloader
dataset = TensorDataset(color_palettes_tensor, text_inputs)
data_loader = DataLoader(dataset, batch_size=BATCH_SIZE, shuffle=False)

# Hyperparameters
palette_size = 5  # Number of colors in the generated palette
latent_dim = 100  # Dimension of the latent noise vector
# Dimension of the text input representation (number of unique words)
text_dim = 1

# Create the Generator and Discriminator
generator = ConditionalGenerator(latent_dim, palette_size, text_dim)
discriminator = ConditionalDiscriminator(palette_size, text_dim)

# Train the conditional cGAN
train(generator, discriminator, data_loader, num_epochs=NUM_EPOCHS,
      latent_dim=latent_dim, text_dim=text_dim)

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

# Assuming word_to_index is created elsewhere (modify if necessary)
word_to_index = {word: i for i, word in enumerate(
    sorted(set(word for sublist in filtered_src_seqs for word in sublist)))}
# Create reverse mapping
index_to_word = {i: word for word, i in word_to_index.items()}

# Convert each numerical list to prompt strings
prompts = [' '.join([index_to_word[float(value)]
                    for value in sublist if value != 0]) for sublist in text_inputs_list]

# Generate new color palettes
new_palettes = generator(z, text_inputs)


def visualize_color_palette(color_palette, is_original=False):
    plt.figure(figsize=(10, 2))  # Set the figure size
    for i in range(len(color_palette)):
        if is_original:
            # Assume original palette data is in 0-255 range
            color = [value / 255 for value in color_palette[i]]
        else:
            # Generated palette data is already in 0-1 range
            color = color_palette[i].detach().numpy()

        color = np.clip(color, 0, 1)  # Clip values to [0, 1] range
        # Plot each color as a filled rectangle
        plt.fill_between([i, i + 1], 0, 1, color=color)
    plt.axis('off')  # Turn off axis
    plt.show()  # Show the plot


def showOriginal(original_index):
    # Check if the index is within the valid range
    if 0 <= original_index < len(filtered_trg_seqs):
        try:
            original_palette = filtered_trg_seqs[original_index]
            print(f"Original color palette for index {original_index}:")
            visualize_color_palette(torch.tensor(
                original_palette, dtype=torch.float32))
        except IndexError:
            print(f"No original palette found for index {original_index}")
    else:
        print(f"No original palette found for index {original_index}")


generated_palettes = []

for i, (palette, prompt) in enumerate(zip(new_palettes, filtered_src_seqs)):
    # Display the prompt along with the color palette
    print(f"Prompt for color palette {i+1}: {' '.join(prompt)}")

    print(f"Generated color palette {i+1}:")
    visualize_color_palette(palette)

    print(f"Original color palette for index {i+1}:")
    visualize_color_palette(filtered_trg_seqs[i], is_original=True)

    generated_palettes.append(palette)
