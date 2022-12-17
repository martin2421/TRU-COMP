
# keeps count of the following items
uppercases = 0
lowercases = 0
digits = 0
whitespaces = 0

try:
    # opens a file
    file = open("C:\\Users\\marti\\Documents\\TRU\\COMP\\COMP 2210 - Programming Methods\\Lab 5\\text.txt", "r")

    # iterates through each line in the file
    for line in file:
         # iterates through each character in the line
         for character in line:
              if(character.islower()):
                   lowercases += 1
              elif(character.isupper()):
                   uppercases += 1
              elif(character.isspace()):
                   whitespaces += 1
              elif(character.isdigit()):
                   digits += 1
         
    # prints results 
    print("Number of uppercases:", uppercases)
    print("Number of lowercases:", lowercases)
    print("Number of digits:", digits)
    print("Number of whitespaces:", whitespaces) 
    
    # close file
    file.close()

# exception thrown if file was not found
except FileNotFoundError:
     print("File not found!")

