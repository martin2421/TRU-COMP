import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import pickle
import nltk
from collections import Counter
from datasets import ClassLabel
from torch.utils.data import TensorDataset, DataLoader
from nltk.tokenize import word_tokenize
from sklearn.preprocessing import MinMaxScaler

# Download NLTK resources (you only need to do this once)
# nltk.download('punkt')

# Load the data from the database
with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_names.pkl', 'rb') as fin:
    src_seqs = pickle.load(fin)

with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_palettes_rgb.pkl', 'rb') as fin:
    trg_seqs = pickle.load(fin)

# Define the Conditional Generator


class ConditionalGenerator(nn.Module):
    def __init__(self, latent_dim, palette_size, text_dim):
        super(ConditionalGenerator, self).__init__()
        self.palette_size = palette_size
        self.model = nn.Sequential(
            nn.Linear(latent_dim + text_dim, 256),  # Adjust the input size here
            nn.LeakyReLU(0.2, inplace=True),
            nn.Linear(256, 512),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Linear(512, palette_size * 3),
            nn.Tanh()
        )

    def forward(self, z, text):
        input = torch.cat([z, text], 1)
        return self.model(input).view(-1, self.palette_size, 3)

# Define the Conditional Discriminator


class ConditionalDiscriminator(nn.Module):
    def __init__(self, palette_size, text_dim):
        super(ConditionalDiscriminator, self).__init__()
        self.palette_size = palette_size
        self.text_dim = text_dim

        self.model = nn.Sequential(
            nn.Linear(palette_size * 3 + text_dim, 256),
            nn.LeakyReLU(0.2),
            nn.Linear(256, 128),
            nn.BatchNorm1d(128),
            nn.LeakyReLU(0.2),
            nn.Linear(128, 1),
            nn.Sigmoid()
        )

    def forward(self, x, text_input):
        input = torch.cat(
            [x.view(-1, self.palette_size * 3), text_input], dim=1)
        return self.model(input)

# Training loop


def train(generator, discriminator, data_loader, num_epochs, latent_dim, text_dim):
    criterion = nn.BCELoss()
    g_optimizer = optim.Adam(generator.parameters(), lr=0.0002)
    d_optimizer = optim.Adam(discriminator.parameters(), lr=0.0002)

    for epoch in range(num_epochs):
        for real_palettes, text_inputs in data_loader:
            # Train the Discriminator
            z = torch.randn(real_palettes.size(0), latent_dim)
            fake_palettes = generator(z, text_inputs)

            d_real = discriminator(real_palettes, text_inputs)
            d_fake = discriminator(fake_palettes.detach(), text_inputs)

            d_loss = criterion(d_real.squeeze(), torch.ones_like(d_real).squeeze(
            )) + criterion(d_fake.squeeze(), torch.zeros_like(d_fake).squeeze())
            d_optimizer.zero_grad()
            d_loss.backward()
            d_optimizer.step()

            # Train the Generator
            z = torch.randn(real_palettes.size(0), latent_dim)
            fake_palettes = generator(z, text_inputs)

            g_loss = criterion(discriminator(
                fake_palettes, text_inputs).squeeze(), torch.ones_like(d_real).squeeze())
            g_optimizer.zero_grad()
            g_loss.backward()
            g_optimizer.step()

        print(
            f"Epoch {epoch+1}/{num_epochs}, Generator Loss: {g_loss.item():.4f}, Discriminator Loss: {d_loss.item():.4f}")

# Preprocess color palettes


def preprocess_color_palettes(color_palettes):
    # Reshape and normalize color palettes
    color_palettes = torch.tensor(color_palettes)  # Convert to tensor

    return color_palettes

# Preprocess prompts


def preprocess_prompts(prompts, max_length):

    # Find the maximum length of the inner lists
    max_length = max(len(sublist) for sublist in src_seqs)

    # Pad each sublist to the maximum length
    padded_list = [sublist + [''] * (max_length - len(sublist))
                   for sublist in src_seqs]

    # Map words to numerical representations
    word_to_index = {word: i for i, word in enumerate(
        sorted(set(word for sublist in padded_list for word in sublist)))}
    text_inputs = [[word_to_index[word] for word in sublist]
                   for sublist in padded_list]  # Convert to tensor
    indexed_prompts = torch.tensor(text_inputs).to(torch.float32)
    return indexed_prompts


# Preprocess color palettes
color_palettes_tensor = preprocess_color_palettes(trg_seqs)

# Preprocess prompts
text_inputs = preprocess_prompts(src_seqs, 11)

# Create PyTorch dataset and dataloader
dataset = TensorDataset(color_palettes_tensor, text_inputs)
data_loader = DataLoader(dataset, batch_size=64, shuffle=True)

# Hyperparameters
palette_size = 5
latent_dim = 100
text_dim = 11

# Create the Generator and Discriminator
generator = ConditionalGenerator(latent_dim, palette_size, text_dim)
discriminator = ConditionalDiscriminator(palette_size, text_dim)


# Train the conditional cGAN
train(generator, discriminator, data_loader,
      num_epochs=10, latent_dim=100, text_dim=11)

# Generate new color palettes
z = torch.randn(5, latent_dim)
text_inputs = torch.tensor([[1.0, 0.0, 0.0],
                            [0.0, 1.0, 0.0],
                            [0.0, 0.0, 1.0],
                            [1.0, 1.0, 0.0],
                            [1.0, 0.0, 1.0]])


new_palettes = generator(z, text_inputs)
print("Generated color palettes:")
for palette in new_palettes:
    print("Generated color palette:")
    print(palette)
