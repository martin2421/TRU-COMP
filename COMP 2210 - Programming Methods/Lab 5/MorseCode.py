# list containing morse code for alphabet
morse_list = [' ', '--..--', '.-.-.-', '..--..', '-----', 
              '.----', '..---', '...--', '....-',
              '.....', '-....', '--...', '---..',
              '----.', '.-', '-...', '-.-.', '-..',
              '.', '..-.', '--.', '....', '..', '.---',
              '-.-', '.-..', '--', '-.', '---', '.--.',
              '--.-', '.-.', '...', '-', '..-', '...-',
              '.--', '-..-', '-.-', '--..']

# list containing characters
characters = [' ', ',', '.', '?', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
             'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']

# asks user for input
string = input("Enter a string to convert: ")

result = ""

# iterate through each letter and match the
# letter with its morse code
for letter in string:
     try:
          result += morse_list[characters.index(letter.capitalize())] + " "
     except:
          print("Character", letter, "not found") # error

# output
print(result)
print(string.upper())
