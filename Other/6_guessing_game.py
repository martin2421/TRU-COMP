import random

def generateRandomNumber():
    print("Random number has been generated between 1 and 100")
    return random.randint(1, 100) # random number between 1 - 100
    
def main():
    print("Guess the number game!")
    
    num = generateRandomNumber() # generate new number
    
    while True: # infinite loop
        guess = int(input("Enter your guess: ")) # input form user
        if (guess < num):
            print("Too low, try again.")
        elif (guess > num):
            print("Too high, try again.")
        else:
            print("Correct!")
            answer = input("Play Again? (Y/N): ")
            if (answer == "Y" or answer == "y"):
                num = generateRandomNumber() # new game
            else:
                break # end game
    
main()