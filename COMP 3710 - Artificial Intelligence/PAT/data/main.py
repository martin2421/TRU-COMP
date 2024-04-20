# Import necessary libraries
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

NUM_EPOCHS = 10
BATCH_SIZE = int(9165/6)

# Define a class for the conditional generator
class ConditionalGenerator(nn.Module):
    def __init__(self, latent_dim: int, palette_size: int, text_dim: int):
        # Initialize the parent class
        super(ConditionalGenerator, self).__init__()
        # Store the dimensions
        self.latent_dim = latent_dim
        self.palette_size = palette_size
        self.text_dim = text_dim
        # Define the generator model
        self.model = nn.Sequential(
            # Linear layer with LeakyReLU activation
            nn.Linear(latent_dim + text_dim, 256),
            nn.LeakyReLU(0.2),
            # Linear layer with LeakyReLU activation
            nn.Linear(256, 512),
            nn.BatchNorm1d(512),
            nn.LeakyReLU(0.2),
            # Linear layer with Tanh activation
            nn.Linear(512, palette_size * 3),
            nn.Tanh()
        )

    def forward(self, z: torch.Tensor, text_input: torch.Tensor) -> torch.Tensor:
        # Concatenate the latent vector and text input
        input = torch.cat([z, text_input], dim=1)
        # Pass through the generator model
        return self.model(input).view(-1, self.palette_size, 3)

# Define a class for the conditional discriminator


class ConditionalDiscriminator(nn.Module):
    def __init__(self, palette_size: int, text_dim: int):
        # Initialize the parent class
        super(ConditionalDiscriminator, self).__init__()
        # Store the dimensions
        self.palette_size = palette_size
        self.text_dim = text_dim
        # Define the discriminator model
        self.model = nn.Sequential(
            # Linear layer with LeakyReLU activation
            nn.Linear(palette_size * 3 + text_dim, 256),
            nn.LeakyReLU(0.2),
            # Linear layer with LeakyReLU activation
            nn.Linear(256, 128),
            nn.BatchNorm1d(128),
            nn.LeakyReLU(0.2),
            # Linear layer with Sigmoid activation
            nn.Linear(128, 1),
            nn.Sigmoid()
        )

    def forward(self, x: torch.Tensor, text_input: torch.Tensor) -> torch.Tensor:
        # Concatenate the input and text input
        input = torch.cat(
            [x.view(-1, self.palette_size * 3), text_input], dim=1)
        # Pass through the discriminator model
        return self.model(input)

# Define a function for training the generator and discriminator


def train(
    generator: ConditionalGenerator,
    discriminator: ConditionalDiscriminator,
    data_loader: DataLoader,
    num_epochs: int,
    latent_dim: int,
    text_dim: int
) -> None:
     
    # Define the loss function and optimizers
    criterion = nn.MSELoss()
    g_optimizer = optim.Adam(generator.parameters(), lr=0.0003)
    d_optimizer = optim.Adam(discriminator.parameters(), lr=0.0003)

    # Train for the specified number of epochs
    for epoch in range(num_epochs):
        for real_palettes, text_inputs in data_loader:
            # Train the discriminator
            z = torch.randn(real_palettes.size(0), latent_dim)
            fake_palettes = generator(z, text_inputs)

            d_real = discriminator(real_palettes, text_inputs)
            d_fake = discriminator(fake_palettes.detach(), text_inputs)

            d_loss = criterion(d_real.squeeze(), torch.ones_like(d_real).squeeze(
            )) + criterion(d_fake.squeeze(), torch.zeros_like(d_fake).squeeze())

            d_optimizer.zero_grad()
            d_loss.backward()
            d_optimizer.step()

            # Train the generator
            z = torch.randn(real_palettes.size(0), latent_dim)
            fake_palettes = generator(z, text_inputs)

            g_loss = criterion(discriminator(
                fake_palettes, text_inputs).squeeze(), torch.ones_like(d_real).squeeze())

            g_optimizer.zero_grad()
            g_loss.backward()
            g_optimizer.step()

            # Print the losses
            print(
                f"Epoch {epoch+1}/{num_epochs}, Generator Loss: {g_loss.item():.4f}, Discriminator Loss: {d_loss.item():.4f}")

# Define a function to preprocess the color palettes


def preprocess_color_palettes(color_palettes: list) -> torch.Tensor:
    # Convert the list to a tensor
    color_palettes = torch.tensor(color_palettes, dtype=torch.float32)
    return color_palettes

# Define a function to preprocess the prompts


def preprocess_prompts(prompts: list) -> tuple:
    # Find the maximum length of the prompts
    max_length = max(len(sublist) for sublist in prompts)
    # Pad the prompts with empty strings to the maximum length
    padded_list = [sublist + [''] * (max_length - len(sublist))
                   for sublist in prompts]

    # Create a dictionary mapping words to indices
    word_to_index = {word: i for i, word in enumerate(
        sorted(set(word for sublist in padded_list for word in sublist)))}
    # Create a dictionary mapping indices to words
    index_to_word = {index: word for word, index in word_to_index.items()}

    # Convert the prompts to indices
    text_inputs = [[word_to_index[word] for word in sublist]
                   for sublist in padded_list]
    # Convert the indices to a tensor
    indexed_prompts = torch.tensor(text_inputs).to(torch.float32)

    return indexed_prompts, index_to_word

# Define a function to visualize a color palette


def visualize_color_palette(color_palette: torch.Tensor, is_original: bool = False) -> None:
    # Create a figure
    plt.figure(figsize=(5, 1))
    # Loop over the colors in the palette
    for i in range(len(color_palette)):
        # Get the color
        if is_original:
            color = [value / 255 for value in color_palette[i]]
        else:
            color = color_palette[i].detach().numpy()
            # Clip the color values to the range [0, 1]
            color = np.clip(color, 0, 1)
        # Fill the area with the color
        plt.fill_between([i, i + 1], 0, 1, color=color)
    # Turn off the axis
    plt.axis('off')
    # Show the plot
    plt.show()

# Define the main function
def main() -> None:
    # Load the data
    with open("hexcolor_vf/train_names.pkl", "rb") as fin:
        src_seqs = pickle.load(fin)

    with open("hexcolor_vf/train_palettes_rgb.pkl", "rb") as fin:
        trg_seqs = pickle.load(fin)

    # Preprocess the data
    color_palettes_tensor = preprocess_color_palettes(trg_seqs)
    text_inputs, index_to_word = preprocess_prompts(src_seqs)

    # Create a dataset and data loader
    dataset = TensorDataset(color_palettes_tensor, text_inputs)
    data_loader = DataLoader(dataset, batch_size=BATCH_SIZE, shuffle=True)

    # Set the dimensions
    palette_size = 5
    latent_dim = 100
    text_dim = 11

    # Create the generator and discriminator
    generator = ConditionalGenerator(latent_dim, palette_size, text_dim)
    discriminator = ConditionalDiscriminator(palette_size, text_dim)

    # Train the generator and discriminator
    train(generator, discriminator, data_loader, num_epochs=NUM_EPOCHS,
          latent_dim=latent_dim, text_dim=text_dim)

    # Create a latent vector and text input
    z = torch.randn(5, latent_dim)
    text_inputs = torch.tensor([
        [1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
        [0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
        [0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
        [0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
        [0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
    ])

    # Convert the text inputs to a list
    text_inputs_list = text_inputs.tolist()
    # Create a list of prompts
    prompts = [' '.join([index_to_word[int(value)]
                        for value in sublist if value != 0]) for sublist in text_inputs_list]

    # Generate new palettes
    new_palettes = generator(z, text_inputs)

    # Create a list to store the generated palettes
    generated_palettes = []

    # Loop over the generated palettes and prompts
    for i, (palette, prompt) in enumerate(zip(new_palettes, src_seqs)):
        # Print a message
        print(f"Prompt for color palette {i+1}: {prompt}")

        # Visualize the generated palette
        print(f"Generated color palette {i+1}:")
        visualize_color_palette(palette)

        # Print a message
        print(f"Original color palette for index {i+1}:")

        # Visualize the original palette
        visualize_color_palette(trg_seqs[i], is_original=True)
        # Append the generated palette to the list
        generated_palettes.append(palette)

main()
