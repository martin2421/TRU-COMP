import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import pickle
import nltk
from collections import Counter
from datasets import ClassLabel

# Download NLTK resources (you only need to do this once)
# nltk.download('punkt')

# Load the data from the database
with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_names.pkl', 'rb') as fin:
    src_seqs = pickle.load(fin)

with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_palettes_rgb.pkl', 'rb') as fin:
    trg_seqs = pickle.load(fin)

trg_seqs = [[torch.tensor(inner_tuple) for inner_tuple in outer_list] for outer_list in trg_seqs]

# Define the Conditional Generator
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
        # Print shapes for debugging
        print("Shape of z:", z.shape)
        print("Shape of text_input:", text_input.shape)
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
        # Print shapes for debugging
        print("Shape of x:", x.shape)
        print("Shape of text_input:", text_input.shape)
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


# Convert 2D array of words into sentences
sentences = [' '.join(sublist) for sublist in src_seqs]

#print(sentences)

# Tokenization
tokenized_sentences = [nltk.word_tokenize(sentence.lower()) for sentence in sentences]

# Build vocabulary
word_counts = Counter(word for sentence in tokenized_sentences for word in sentence)
vocab = {word: idx + 1 for idx, (word, _) in enumerate(word_counts.most_common())}

# Encoding sentences
text_inputs = [[vocab[word] for word in sentence] for sentence in tokenized_sentences]

color_palettes = []
for sublist in trg_seqs:
    color_palettes.append(torch.stack(sublist))

color_palettes_tensor = torch.stack(color_palettes)

# Find the maximum length of the inner lists
max_length = max(len(sublist) for sublist in src_seqs)

# Pad each sublist to the maximum length
padded_list = [sublist + [''] * (max_length - len(sublist)) for sublist in src_seqs]

# Convert the list of lists of words to a list of lists of indices
word_to_index = {word: i for i, word in enumerate(sorted(set(word for sublist in padded_list for word in sublist)))}
text_inputs = [[word_to_index[word] for word in sublist] for sublist in padded_list]

# Convert to tensor
text_inputs = torch.tensor(text_inputs)  # Ensure dtype is correct

# Now `text_inputs` has the correct shape and can be used as input to the generator

dataset = TensorDataset(color_palettes_tensor, text_inputs)
data_loader = DataLoader(dataset, batch_size=64, shuffle=True)

# Hyperparameters
palette_size = 8
latent_dim = 100
text_dim = 3

# Create the Generator and Discriminator
generator = ConditionalGenerator(latent_dim, palette_size, text_dim)
discriminator = ConditionalDiscriminator(palette_size, text_dim)

print(text_inputs.shape)
print(color_palettes_tensor.shape)

# Train the conditional cGAN
train(generator, discriminator, data_loader, num_epochs=100, latent_dim=latent_dim, text_dim=text_dim)

# Generate new color palettes
z = torch.randn(3, latent_dim)
text_inputs = torch.tensor([[1.0, 0.0, 0.0], [0.0, 1.0, 0.0], [0.0, 0.0, 1.0]])


new_palettes = generator(z, text_inputs)
print("Generated color palettes:")
for palette in new_palettes:
    print("Generated color palette:")
    print(palette)





