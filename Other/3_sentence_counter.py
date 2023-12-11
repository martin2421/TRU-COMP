# function to create the dictionary from the sentence
def createDictionary(sentence):
    dictionary = {} # initialize the empty dictionary

    # split the sentence into words
    words = sentence.split()

    # loop over each word
    for word in words:
         
        # remove any punctuation from the word
        word = word.strip('.,!?-')

        # get the length
        length = len(word)

        if length in dictionary: 
            dictionary[length] += 1 # increase the value of that length
        else:
            dictionary[length] = 1 # add the length and set the value to 1

    return dictionary

def main():
    sentence = input("Enter a sentence: ")
    dictionary = createDictionary(sentence)

    # display the result
    print("Dictionary created from the sentence:")
    for length, count in dictionary.items(): # loop through each key-value pair
        print(f"{length}: {count}")
        
main()
