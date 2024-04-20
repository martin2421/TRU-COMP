import pickle
import numpy as np
import matplotlib.cm as cm
import matplotlib.pyplot as plt

# change num to see the different colors
num = 500  # goes from 0 to 9165

def filter_single_word(src_seqs):
  filtered_seqs = [seq for seq in src_seqs if len(seq) == 1]
  return filtered_seqs

# Getting the prompts
with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_names.pkl', 'rb') as fin:
    src_seqs = pickle.load(fin)
    # print("Length of names: ", len(src_seqs))
    # Print elements causing the issue
    # print("Length of names: ", len(src_seqs))
    # print(src_seqs)
    # print(src_seqs[num])
    # src_seqs = filter_single_word(src_seqs)
    # for(i, name) in enumerate(src_seqs):
    #     print("#", i, " ", name, end="\n")
    # for i, elem in enumerate(src_seqs):
    #     if not isinstance(elem, list):
    #         print(f"Element {i}: {elem}")

# Getting the colors
with open(r'C:\Users\marti\Documents\TRU\COMP\COMP 3710 - AI\PAT\data\hexcolor_vf\train_palettes_rgb.pkl', 'rb') as fin:
    trg_seqs = pickle.load(fin)
    # print("Length of colors: ", len(trg_seqs))
    # print(trg_seqs)
    # print(trg_seqs[num])
    # for(i, palette) in enumerate(trg_seqs):
    #     print("#", i, " ", palette, end="\n")
    

# for i in range(round(len(src_seqs) / 100)):
#     print("#", i, " ", src_seqs[i], end="\n")


# print the content in the names
# print(src_seqs) # prints a 2D array of names

# print the content in the colors
# print(trg_seqs) # prints a 3D array of colors
# print(type(trg_seqs)) # prints the type of trg_seqs

# print the type and content of the colors
# print(trg_seqs) # list - 9166 lists of 5 tuples of RGB values (9166 palettes)
# print(trg_seqs[num]) # list - 5 tuples of RGB values (5 colors)
# print(trg_seqs[num][num]) # tuple - RGB values


# for i in range(round(len(src_seqs))):
#     if(len(src_seqs[i]) <= 2):
#         print("#", i, " ", src_seqs[i], end="\n")


# change enumerate() number to get different colors
# for index, palettes in enumerate(trg_seqs[num:num+1]):
for index, palettes in enumerate(trg_seqs[num:num+1]):
    image_r, image_g, image_b = [], [], []
    for palette in palettes:
        image_r.append(palette[0]/255)
        image_g.append(palette[1]/255)
        image_b.append(palette[2]/255)

# printing out the colors with visualization
fig, (ax0, ax1, ax2, ax3, ax4) = plt.subplots(
    nrows=1, ncols=5, sharex=True, figsize=(10, 2))
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
