CODE = {'A': ')', 'a': '0', 'B': '(', 'b': '9', 'C': '*', 'c': '8',
        'D': '&', 'd': '7', 'E': '^', 'e': '6', 'F': '%', 'f': '5',
        'G': '$', 'g': '4', 'H': '#', 'h': '3', 'I': '@', 'i': '2',
        'J': '!', 'j': '1', 'K': 'Z', 'k': 'z', 'L': 'Y', 'l': 'y',
        'M': 'X', 'm': 'x', 'N': 'W', 'n': 'w', 'O': 'V', 'o': 'v',
        'P': 'U', 'p': 'u', 'Q': 'T', 'q': 't', 'R': 'S', 'r': 's',
        'S': 'R', 's': 'r', 'T': 'Q', 't': 'q', 'U': 'P', 'u': 'p',
        'V': 'O', 'v': 'o', 'W': 'N', 'w': 'n', 'X': 'M', 'x': 'm',
        'Y': 'L', 'y': 'l', 'Z': 'K', 'z': 'k', '!': 'J', '1': 'j',
        '@': 'I', '2': 'i', '#': 'H', '3': 'h', '$': 'G', '4': 'g',
        '%': 'F', '5': 'f', '^': 'E', '6': 'e', '&': 'D', '7': 'd',
        '*': 'C', '8': 'c', '(': 'B', '9': 'b', ')': 'A', '0': 'a',
        ':': ',', ',': ':', '?': '.', '.': '?', '<': '>', '>': '<',
        "'": '"', '"': "'", '+': '-', '-': '+', '=': ';', ';': '=',
        '{': '[', '[': '{', '}': ']', ']': '}', ' ': '/', '\n': '**'}

# method to encrypt a file
def encryptFile():
    try:
        # opens file
        file = open(
            "C:\\Users\\marti\\Documents\\TRU\\COMP\\COMP 2210 - Programming Methods\\Lab 5\\file.txt", "r")

        # variable storing the encrypted text
        encrypted_text = ""

        # iterate through each line in the file
        for line in file:

            # iterate through each character in the line
            for letter in line:
                encrypted_text += str(CODE.get(letter))

            # write content to a second file
            encrypted_file = open(
                "C:\\Users\\marti\\Documents\\TRU\\COMP\\COMP 2210 - Programming Methods\\Lab 5\\encryptedfile.txt", "w")
            encrypted_file.write("Encrypted Text:\n")
            encrypted_file.write(encrypted_text)

        # close file
        file.close()

    # exception thrown if file was not found
    except FileNotFoundError:
        print("File not found!")
        
# method to decrypt a file
def decryptFile():
    try:
        encryptedfile = open("C:\\Users\\marti\\Documents\\TRU\\COMP\\COMP 2210 - Programming Methods\\Lab 5\\file2.txt", "r")
        
        decrypted_text = ""
        
        for line in encryptedfile:
            for letter in line:
                decrypted_text += CODE[letter]
                
        decrypted_file = open("C:\\Users\\marti\\Documents\\TRU\\COMP\\COMP 2210 - Programming Methods\\Lab 5\\decryptedfile.txt", "w")
        decrypted_file.write("Decrypted Text:\n")
        decrypted_file.write(decrypted_text)
        
        encryptedfile.close()
    
    except FileNotFoundError:
        print("File not found!")

encryptFile()
decryptFile()