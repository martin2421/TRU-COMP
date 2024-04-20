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
    filtered_src_seqs = filter_single_word(src_seqs)  # Filter single-word prompts
    filtered_src_seqs = [element for sublist in filtered_src_seqs for element in sublist]
    print(len(filtered_src_seqs))
    # print(filtered_src_seqs)
    # for(i, name) in enumerate(filtered_src_seqs):
    #     print("#", i, " ", name, end="\n")

with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_palettes_rgb.pkl', 'rb') as fin:
    trg_seqs = pickle.load(fin)  # Load target palettes
    # Assuming filtered_seqs contains single-word prompts after filtering src_seqs
    filtered_trg_seqs = []
    for i, prompt in enumerate(src_seqs):
        if len(prompt) == 1:  # Assuming single-word prompts
            filtered_trg_seqs.append(trg_seqs[i])
    # print(len(filtered_trg_seqs))
    for(i, name) in enumerate(filtered_trg_seqs):
        print("#", i, " ", name, end="\n")

            
# one_dim_array = [item for sublist in filtered_src_seqs for item in sublist]
# print(one_dim_array)

# for(i, name) in enumerate(filtered_src_seqs):
#         print("#", i, " ", name, end="\n")     
# print(filtered_trg_seqs)