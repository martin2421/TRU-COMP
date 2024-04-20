import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, Dataset
import numpy as np
import pickle

# Define your CGAN architecture (Generator and Discriminator)
class Generator(nn.Module):
    def __init__(self, input_dim, output_dim):
        super(Generator, self).__init__()
        self.fc1 = nn.Linear(input_dim, 256)
        self.fc2 = nn.Linear(256, 128)
        self.fc3 = nn.Linear(128, output_dim)

    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = torch.relu(self.fc2(x))
        x = torch.sigmoid(self.fc3(x))
        return x


class Discriminator(nn.Module):
    def __init__(self, input_dim):
        super(Discriminator, self).__init__()
        self.fc1 = nn.Linear(input_dim, 256)
        self.fc2 = nn.Linear(256, 128)
        self.fc3 = nn.Linear(128, 1)

    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = torch.relu(self.fc2(x))
        x = self.fc3(x)  # Remove sigmoid activation
        return x

# Define dataset and dataloader

class ColorPaletteDataset(Dataset):
    def __init__(self, names_file, palettes_file):
        with open(names_file, 'rb') as f:
            self.names = pickle.load(f)
        with open(palettes_file, 'rb') as f:
            self.palettes = pickle.load(f)

    def __len__(self):
        return len(self.names)

    def __getitem__(self, idx):
        name = self.names[idx]
        palette = self.palettes[idx]
        # Ensure each palette has exactly 5 tuples of RGB values
        if len(palette) != 5:
            raise ValueError(f"Palette at index {idx} does not have exactly 5 tuples.")
        # Convert palette to tensor
        palette_tensor = torch.tensor(palette, dtype=torch.float)
        return name, palette_tensor
   
# Define collate function for DataLoader
def collate_fn(batch):
    names, palettes = zip(*batch)
    palettes = torch.stack(palettes)  # Convert list of tensors to a single tensor
    return names, palettes

# Define training loop
def train(generator, discriminator, train_loader, optimizer_G, optimizer_D, criterion):
    for _, real_palette in train_loader:
        print("Real Palette Shape:", real_palette.shape)
        real_palette = real_palette.float()
        # Reshape real_palette to have a singleton dimension along dim=1
        real_palette = real_palette.unsqueeze(1)
        
        # Reshape fake_prompt to match the batch size of real_palette
        fake_prompt = torch.randn(real_palette.size(0), latent_dim).to(real_palette.device)  # Adjusted line
        
        print("Fake Prompt Shape:", fake_prompt.shape)
        
        # Train Discriminator
        optimizer_D.zero_grad()
        fake_palette = generator(torch.cat((fake_prompt, real_palette.view(real_palette.size(0), -1)), dim=1)) # Define fake_palette
        fake_output = torch.sigmoid(discriminator(torch.cat((fake_palette, real_palette.view(real_palette.size(0), -1)), dim=1)))
        real_output = torch.sigmoid(discriminator(torch.cat((real_palette.view(real_palette.size(0), -1), real_palette.view(real_palette.size(0), -1)), dim=1)))
        d_loss = criterion(fake_output, torch.zeros_like(fake_output)) + criterion(real_output, torch.ones_like(real_output))
        d_loss.backward()
        optimizer_D.step()
        
        # Train Generator
        optimizer_G.zero_grad()
        fake_palette = generator(torch.cat((fake_prompt, real_palette.view(real_palette.size(0), -1)), dim=1)) # Define fake_palette
        fake_output = torch.sigmoid(discriminator(torch.cat((fake_palette, real_palette.view(real_palette.size(0), -1)), dim=1)))
        g_loss = criterion(fake_output, torch.ones_like(fake_output))
        g_loss.backward()
        optimizer_G.step()

        # Print losses
        print(f"Generator Loss: {g_loss.item()}, Discriminator Loss: {d_loss.item()}")


# Initialize your models, optimizers, loss function, etc.
latent_dim = 64  # Dimension of latent space
input_dim = 3 + latent_dim  # Input dimension for both prompt and palette
output_dim = 15  # Output dimension for palette (5 tuples of RGB values)
lr_G = 0.0002
lr_D = 0.0002
batch_size = 64
num_epochs = 100

generator = Generator(input_dim, output_dim)
discriminator = Discriminator(input_dim + output_dim)
optimizer_G = optim.Adam(generator.parameters(), lr=lr_G)
optimizer_D = optim.Adam(discriminator.parameters(), lr=lr_D)
criterion = nn.BCELoss()

# Load your dataset
train_dataset = ColorPaletteDataset("C:\\Users\\marti\\Documents\\TRU\\COMP\\COMP 3710 - AI\\PAT\\data\\hexcolor_vf\\train_names.pkl", "C:\\Users\\marti\\Documents\\TRU\\COMP\\COMP 3710 - AI\\PAT\\data\\hexcolor_vf\\train_palettes_rgb.pkl")

# Create DataLoader
train_loader = DataLoader(train_dataset, batch_size=batch_size, shuffle=True, collate_fn=collate_fn)

# Train your CGAN
for epoch in range(num_epochs):
    print(f"Epoch {epoch+1}/{num_epochs}")
    train(generator, discriminator, train_loader, optimizer_G, optimizer_D, criterion)

# Generate color palettes based on user prompts

def generate_palette(generator, prompt):
    with torch.no_grad():
        prompt = torch.tensor(prompt).float().unsqueeze(0)
        fake_prompt = torch.randn(1, latent_dim)
        generated_palette = generator(torch.cat((fake_prompt, prompt), dim=1))
    return generated_palette.numpy()

# Example usage
user_prompt = [0.5, 0.5, 0.5]  # Example prompt
generated_palette = generate_palette(generator, user_prompt)
print("Generated Palette:")
print(generated_palette)

