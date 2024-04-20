import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import pickle
# import nltk
# from collections import Counter
# from datasets import ClassLabel
import random
import matplotlib.pyplot as plt
import numpy as np



#################################################################

# Load the data from the database

# Contains a 2D array of names
with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_names.pkl', 'rb') as fin:
    src_seqs = pickle.load(fin)
    print("Length of names: ", len(src_seqs))
    
    # Preprocess each element in the list
    # processed_src_seqs = []
    # for elem in src_seqs:
    #     if isinstance(elem, list):
    #         processed_src_seqs.append(elem)
    #     elif isinstance(elem, str):
    #         tokens = elem.split()
    #         processed_src_seqs.append(tokens)
    #     else:
    #         # Convert other types to string
    #         processed_src_seqs.append(str(elem))
    #         pass
    
    
    # for i, elem in enumerate(src_seqs):
    #     if not isinstance(elem, list):
    #         print(f"Element {i}: {elem}")
    # for i, prompt in enumerate(src_seqs):
    #     if isinstance(prompt, list):  # Check if the prompt is a list
    #         src_seqs[i] = ' '.join(prompt)  # Convert the list to a string

# Contains a 3D array of colors (one palette per name, 5 colors per palette, RGB values per color)
with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_palettes_rgb.pkl', 'rb') as fin:
    trg_seqs = pickle.load(fin)
    print("Length of colors: ", len(trg_seqs))
#################################################################










#################################################################

## Define the Conditional Generator
class ConditionalGenerator(nn.Module):
    def __init__(self, latent_dim, palette_size, text_dim):
        super(ConditionalGenerator, self).__init__()
        self.latent_dim = latent_dim
        self.palette_size = palette_size
        self.text_dim = text_dim

        self.model = nn.Sequential(
            nn.Linear(latent_dim + text_dim, 256),
            nn.LeakyReLU(0.2),
            nn.Linear(256, 512),
            nn.BatchNorm1d(512),
            nn.LeakyReLU(0.2),
            nn.Linear(512, palette_size * 3),
            nn.Tanh()
        )

    def forward(self, z, text_input):
        input = torch.cat([z, text_input], dim=1)
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
        input = torch.cat([x.view(-1, self.palette_size * 3), text_input], dim=1)
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
            
            d_loss = criterion(d_real.squeeze(), torch.ones_like(d_real).squeeze()) + criterion(d_fake.squeeze(), torch.zeros_like(d_fake).squeeze())
            d_optimizer.zero_grad()
            d_loss.backward()
            d_optimizer.step()

            # Train the Generator
            z = torch.randn(real_palettes.size(0), latent_dim)
            fake_palettes = generator(z, text_inputs)
            
            g_loss = criterion(discriminator(fake_palettes, text_inputs).squeeze(), torch.ones_like(d_real).squeeze())
            g_optimizer.zero_grad()
            g_loss.backward()
            g_optimizer.step()

        print(f"Epoch {epoch+1}/{num_epochs}, Generator Loss: {g_loss.item():.4f}, Discriminator Loss: {d_loss.item():.4f}")
    
#################################################################









#################################################################

# Preprocess color palettes
def preprocess_color_palettes(color_palettes):
    # Reshape and normalize color palettes
    color_palettes = torch.tensor(color_palettes, dtype=torch.float32)  # Convert to tensor
    return color_palettes


# Preprocess prompts
def preprocess_prompts(prompts, max_length=None):
    prompt_indices_list = []
    word_to_index = {}

    for prompt in prompts:
        if isinstance(prompt, list):  # Ensure prompt is a list of words
            prompt_tokens = prompt
        elif isinstance(prompt, str):  # Tokenize if prompt is a string
            prompt_tokens = prompt.split()
        else:
            continue  # Skip if prompt is neither list nor string

        prompt_indices = []

        for word in prompt_tokens:
            if word not in word_to_index:
                word_to_index[word] = len(word_to_index)
            prompt_indices.append(word_to_index[word])

        if max_length is not None:
            prompt_indices += [0] * (max_length - len(prompt_indices))

        prompt_indices_list.append(prompt_indices)

    indexed_prompts = torch.tensor(prompt_indices_list, dtype=torch.long)
    return indexed_prompts


# Preprocess prompts
# def preprocess_prompts(prompts, max_length):
    
#     # Find the maximum length of the inner lists
#     max_length = max(len(sublist) for sublist in src_seqs)

#     # Pad each sublist to the maximum length
#     padded_list = [sublist + [''] * (max_length - len(sublist)) for sublist in src_seqs]

    
#     # Map words to numerical representations
#     word_to_index = {word: i for i, word in enumerate(sorted(set(word for sublist in padded_list for word in sublist)))}
    
#     text_inputs = [[word_to_index[word] for word in sublist] for sublist in padded_list] # Convert to tensor
    
#     indexed_prompts = torch.tensor(text_inputs).to(torch.float32)
    
#     return indexed_prompts




#################################################################









#################################################################

# Preprocess color palettes and prompts
# color_palettes_tensor = preprocess_color_palettes(trg_seqs)
# print("Color palettes tensor size:", color_palettes_tensor.size())
# text_inputs = preprocess_prompts(src_seqs, max_length=11)
# print("Text inputs tensor size:", text_inputs.size())

# if color_palettes_tensor.size(0) != text_inputs.size(0):
#     print("Size mismatch between color palettes and text inputs tensors!")

color_palettes_tensor = torch.tensor(trg_seqs, dtype=torch.float32)
print("Color palettes tensor size:", color_palettes_tensor.size())

# text_inputs = torch.tensor(src_seqs, dtype=torch.long)
# ???
# text_inputs = torch.tensor(list(range(9165)), dtype=torch.long)

# text_inputs = torch.tensor(src_seqs, dtype=torch.long)
# print("Text inputs tensor size:", text_inputs.size())

# if color_palettes_tensor.size(0) != text_inputs.size(0):
#     print("Size mismatch between color palettes and text inputs tensors!")
#     print("Length of color palettes:", color_palettes_tensor.size(0))
#     print("Length of text inputs:", text_inputs.size(0))
# else:
#     print("Sizes match between color palettes and text inputs tensors!")


# Hyperparameters
palette_size = 5
latent_dim = 100
text_dim = 11

# Accept user input for the prompt
user_prompt_index = random.randint(0, len(src_seqs) - 1)
user_prompt = src_seqs[user_prompt_index]

# Preprocess the user prompt
processed_prompt = preprocess_prompts(user_prompt, max_length=11) # Specify the maximum length of prompts

# Generate new color palettes
z = torch.randn(len(trg_seqs), latent_dim) # Adjust the size to match the number of color palettes

# Repeat the processed prompt for each color palette
text_inputs = processed_prompt.repeat(len(trg_seqs), 1)  

if color_palettes_tensor.size(0) != text_inputs.size(0):
    print("Size mismatch between color palettes and text inputs tensors!")


# Ensure the shapes are correct
# assert color_palettes_tensor.size(0) == text_inputs.size(0), "Size mismatch between tensors"

# Randomly select a prompt for each color palette
random_indices = torch.randperm(9165) # ???
color_palettes_tensor = color_palettes_tensor[random_indices]
# text_inputs = text_inputs[random_indices]

# Split the data into training and validation sets
train_size = int(0.8 * len(color_palettes_tensor))
train_dataset = TensorDataset(color_palettes_tensor[:train_size], text_inputs[:train_size])
val_dataset = TensorDataset(color_palettes_tensor[train_size:], text_inputs[train_size:])

# Create dataloaders for training and validation
train_loader = DataLoader(train_dataset, batch_size=1028, shuffle=True)
val_loader = DataLoader(val_dataset, batch_size=1028, shuffle=False)


#################################################################








#################################################################

# Create PyTorch dataset and dataloader
dataset = TensorDataset(color_palettes_tensor, text_inputs)
data_loader = DataLoader(dataset, batch_size=64, shuffle=True)


# Create the Generator and Discriminator
generator = ConditionalGenerator(latent_dim, palette_size, text_dim)
discriminator = ConditionalDiscriminator(palette_size, text_dim)

# Train the conditional cGAN
train(generator, discriminator, data_loader, num_epochs=20, latent_dim=latent_dim, text_dim=text_dim)


text_inputs_list = text_inputs.tolist()

prompts = [' '.join([str(int(value)) for value in sublist if value != 0]) for sublist in text_inputs_list]

new_palettes = generator(z, text_inputs)
# print("Generated color palettes:")
# for palette in range(10):
#     print("Generated color palette:")
#     print(palette)

#################################################################














#################################################################

# Function to visualize a single color palette
def visualize_color_palette(color_palette):
    plt.figure(figsize=(5, 1))  # Set the figure size
    for i in range(len(color_palette)):
        color = color_palette[i].detach().numpy()  # Convert tensor to numpy array
        color = np.clip(color, 0, 1)  # Clip values to [0, 1] range
        plt.fill_between([i, i + 1], 0, 1, color=color)  # Plot each color as a filled rectangle
    plt.axis('off')  # Turn off axis
    plt.show()  # Show the plot
    
def showOriginal(original_index):
    for index, palettes in enumerate(trg_seqs[original_index:original_index+1]):
        image_r, image_g, image_b = [], [], []
        for palette in palettes:
            image_r.append(palette[0]/255)
            image_g.append(palette[1]/255)
            image_b.append(palette[2]/255)

    # printing out the colors with visualization
    fig, (ax0, ax1, ax2, ax3, ax4) = plt.subplots(
        nrows=1, ncols=5, sharex=True, figsize=(5, 2))
    # color 1
    ax0.imshow(np.array([[[image_r[0], image_g[0], image_b[0]]]]))
    ax0.axis('off')
    # color 2
    ax1.imshow(np.array([[[image_r[1], image_g[1], image_b[1]]]]))
    ax1.axis('off')
    # color 3
    ax2.imshow(np.array([[[image_r[2], image_g[2], image_b[2]]]]))
    ax2.axis('off')
    # color 4
    ax3.imshow(np.array([[[image_r[3], image_g[3], image_b[3]]]]))
    ax3.axis('off')
    # color 5
    ax4.imshow(np.array([[[image_r[4], image_g[4], image_b[4]]]]))
    ax4.axis('off')
    plt.show()

# Visualize each generated color palette
# for i, palette in range(10):
for i, palette in enumerate(new_palettes):
    while (i < 10):
        print(f"Name of palette {i+1}: {src_seqs[random_indices[i]]}")
        print(f"ID: {random_indices[i]}")
        print(f"Original: {showOriginal(random_indices[i])}")
        print(f"Generated color palette {i+1}:")
        visualize_color_palette(palette)
        break

#################################################################