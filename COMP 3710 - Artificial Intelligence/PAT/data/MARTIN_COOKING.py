import pickle
import numpy as np
import torch
import torch.nn as nn
import torch.nn.functional as F
import matplotlib.pyplot as plt
from torch.utils.data import Dataset, DataLoader
from sklearn.preprocessing import LabelEncoder

# Training loop
BATCH_SIZE = 1024
NUM_EPOCHS = 20

# Used to filter single-word prompts
def filter_single_word(src_seqs):
  filtered_seqs = [seq for seq in src_seqs if len(seq) == 1]
  return filtered_seqs

# Load data (assuming train_names.pkl and train_palettes_rgb.pkl are in the same directory)
with open("hexcolor_vf/train_names.pkl", "rb") as f:
     original_train_names = pickle.load(f)
     train_names = original_train_names
     train_names = filter_single_word(train_names)  # Filter single-word prompts
     train_names = [element for sublist in train_names for element in sublist]

with open("hexcolor_vf/train_palettes_rgb.pkl", "rb") as f:
     train_palettes_rgb = pickle.load(f)
     filtered_trg_seqs = []
     for i, prompt in enumerate(original_train_names):
          if len(prompt) == 1:  # Assuming single-word prompts
               filtered_trg_seqs.append(train_palettes_rgb[i])
     train_palettes_rgb = filtered_trg_seqs

# Preprocess data
num_palettes = len(train_names)
num_colors = len(train_palettes_rgb[0])
latent_dim = 100

label_encoder = LabelEncoder()
label_encoder.fit(train_names)
encoded_labels = torch.from_numpy(label_encoder.transform(train_names).reshape(num_palettes, 1))


# Define the Generator network
class Generator(nn.Module):
     def __init__(self):
          super(Generator, self).__init__()
          self.fc1 = nn.Linear(latent_dim + 1, 256)
          self.leaky_relu1 = nn.LeakyReLU(0.2)
          self.fc2 = nn.Linear(256, 512)
          self.leaky_relu2 = nn.LeakyReLU(0.2)
          self.fc3 = nn.Linear(512, num_colors * 3)
          self.output = nn.Sigmoid()

     def forward(self, noise, label):
          x = torch.cat([noise, label], dim=1)
          x = self.leaky_relu1(self.fc1(x))
          x = self.leaky_relu2(self.fc2(x))
          x = self.output(self.fc3(x))
          return x.reshape(-1, num_colors, 3)

# Define the Discriminator network
class Discriminator(nn.Module):
     def __init__(self):
          super(Discriminator, self).__init__()
          self.fc1 = nn.Linear(num_colors * 3 + 1, 512)
          self.leaky_relu1 = nn.LeakyReLU(0.2)
          self.fc2 = nn.Linear(512, 256)
          self.leaky_relu2 = nn.LeakyReLU(0.2)
          self.fc3 = nn.Linear(256, 1)
          self.output = nn.Sigmoid()

     def forward(self, palette, label):
          x = torch.cat([palette.view(-1, num_colors * 3), label], dim=1)
          x = self.leaky_relu1(self.fc1(x))
          x = self.leaky_relu2(self.fc2(x))
          x = self.output(self.fc3(x))
          return x

# Create generator and discriminator instances
generator = Generator()
discriminator = Discriminator()

# Define loss function and optimizer
criterion = nn.BCELoss()
g_optimizer = torch.optim.Adam(generator.parameters(), lr=0.0002)
d_optimizer = torch.optim.Adam(discriminator.parameters(), lr=0.0002)

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
generator.to(device)
discriminator.to(device)
encoded_labels = encoded_labels.to(device)

for epoch in range(NUM_EPOCHS):
     for i in range(int(num_palettes / BATCH_SIZE)):
          # Sample random noise and labels
          noise = torch.randn(BATCH_SIZE, latent_dim, device=device)
          label_indices = np.random.randint(0, num_palettes, size=BATCH_SIZE)
          labels = encoded_labels[label_indices].to(device)
          
          # Train discriminator on real and fake palettes (continued)
          real_palettes = []
          palette_shape = (5, 3) # Assuming 5 colors and 3 RGB channels
          for index in label_indices:
               palette = np.array(train_palettes_rgb[index]).reshape(palette_shape)  # Convert sublist to NumPy array
               palette = torch.from_numpy(palette).float().to(device) / 255.0
               real_palettes.append(palette)
          real_palettes = torch.stack(real_palettes)  # Convert list to a tensor

          real_labels = torch.ones(BATCH_SIZE, 1, device=device)
          fake_palettes = generator(noise, labels)

          # Train discriminator
          d_optimizer.zero_grad()
          d_real_loss = criterion(discriminator(real_palettes, labels), real_labels)
          d_fake_loss = criterion(discriminator(fake_palettes.detach(), labels), torch.zeros(BATCH_SIZE, 1, device=device))
          d_loss = (d_real_loss + d_fake_loss) / 2
          d_loss.backward()
          d_optimizer.step()

          # Train generator
          g_optimizer.zero_grad()
          g_fake_labels = torch.ones(BATCH_SIZE, 1, device=device)
          g_loss = criterion(discriminator(fake_palettes, labels), g_fake_labels)
          g_loss.backward()
          g_optimizer.step()

          # Print training progress
          print(f"Epoch: {epoch+1}/{NUM_EPOCHS}, Batch: {i+1}/{int(num_palettes / BATCH_SIZE)}, d_loss: {d_loss:.4f}, g_loss: {g_loss:.4f}")

# Function to generate a color palette based on user prompt
def generate_palette(prompt):
     label_index = label_encoder.transform([prompt])[0]
     noise = torch.randn(1, latent_dim, device=device)
     label = torch.tensor([label_index]).to(device).unsqueeze(0)
     generated_palette = generator(noise, label)
     generated_palette = generated_palette.cpu().detach().numpy()[0] * 255.0
     return generated_palette.astype(int)

# Example usage
user_prompt = "youtube"
generated_palette = generate_palette(user_prompt)

# Visualize generated and original palettes
def visualize_palettes(generated_palette, original_palette, title):
     fig, ax = plt.subplots()
     
     # Print original palette values before normalization (if applicable)
     print("Original palette (before normalization):", original_palette)

     # Normalize color data if necessary (assuming original_palette has RGB values between 0-255)
     original_palette = np.array(original_palette) / 255.0

     # Print original palette values after normalization
     print("Original palette (after normalization):", original_palette)

     # ... rest of the code (generating or processing generated_palette)

     # Print generated palette values
     print("Generated palette:", generated_palette)
     
     # Assuming colors1 and colors2 represent RGB values between 0 and 1
     num_colors = len(generated_palette)  # Assuming both palettes have same length
     rows, cols = 1, num_colors  # One row to display palettes horizontally
     
     num_colors_total = len(generated_palette) + len(original_palette)
     rows = (num_colors_total + cols - 1) // cols  # Calculate rows based on total colors and columns
   
     # Reshape data into a 2D array for imshow
     all_colors = np.vstack([generated_palette, original_palette])  # Stack palettes vertically
     all_colors_reshaped = all_colors.reshape(rows, cols, 3)  # Reshape for rows, cols, RGB channels
   
     # Create the color grid using imshow
     palette_image = ax.imshow(all_colors_reshaped, aspect='auto')
   
     # Optional: Remove ticks and labels from the axes (customization)
     plt.axis('off')
   
     plt.title(title)
     plt.show()
     
# def visualize_palettes(palette1, palette2, title):
#      fig, ax = plt.subplots()
     
#      # Ensure colors1 and colors2 have the expected lengths
#      colors1 = np.array(palette1) / 255.0  # Extract RGB values
#      colors2 = np.array(palette2) / 255.0  # Extract RGB values
     
#      print(f"Length of colors1: {len(colors1)}")
#      print("Colors1: ", colors1)
#      print(f"Length of colors2: {len(colors2)}")
#      print("Colors2: ", colors2)
       
#      x_offset = 5  # Adjust offset as needed
#      x = [i + x_offset for i in range(len(colors2))]  # Add offset to all elements
#      print(f"Length of x: {len(x)}")
     
#      patches1 = ax.scatter(range(len(colors1)), np.zeros(len(colors1)), marker='o', c=colors1, label='Generated')
#      patches2 = ax.scatter(x, np.zeros(len(colors2)), marker='o', c=colors2, label='Original')
     
#      plt.xlim([0, len(colors1) + len(colors2) + 5])
#      plt.xlabel("Color Index")
#      plt.ylabel("Color Value (Normalized)")
#      plt.title(title)
#      plt.legend(handles=[patches1, patches2])
#      plt.show()
     
def find_closest_palette(generated_palette, train_palettes_rgb):
     distances = np.linalg.norm(train_palettes_rgb - generated_palette, axis=2)
     min_index = np.argmin(distances)
     return train_palettes_rgb[min_index]

original_palette = find_closest_palette(generated_palette, train_palettes_rgb)
visualize_palettes(generated_palette, original_palette, f"Color Palettes for '{user_prompt}'")


