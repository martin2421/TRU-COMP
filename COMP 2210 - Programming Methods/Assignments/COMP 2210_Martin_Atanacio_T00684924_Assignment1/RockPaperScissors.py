import random as ri

# creates a random selection for the computer
def computerChoice():
     num = ri.randint(1, 3) # random int between 1 - 3
     
     # returns decision as a string
     if(num == 1):
          return "ROCK"
     elif(num ==2):
          return "PAPER"
     else:
          return "SCISSORS"
     
# asks user to input their decision
def userChoice():
     choice = input("Enter ROCK, PAPER, OR SCISSORS: ")
     
     # if input is incorrect, it will keep prompting the user for input
     while (choice != "ROCK" and choice != "PAPER" and choice != "SCISSORS"):
          choice = input("Enter ROCK, PAPER, OR SCISSORS: ")
          
     return choice # returns the user's choice as a string

# decides a winner. accepts both the computers choice and the user's choice
def winner(compChoice, userChoice):
     
     print("Computer's Choice: " + compChoice) # prints computers choice
     
     # computer chooses rock
     if(compChoice == "ROCK"): 
          if(userChoice == "SCISSORS"):
               print("Computer Wins! Rock smashes Scissors.")
               return 0 # 0 implies no error
          elif(userChoice == "PAPER"):
               print("User Wins! Paper wraps Rock.")
               return 0
          else:
               return -1 # -1 implies a tie (both choices were the same)
     
     # computer chooses paper
     elif(compChoice == "PAPER"): 
          if(userChoice == "ROCK"):
               print("Computer Wins! Paper wraps Rock.")
               return 0
          elif(userChoice == "SCISSORS"):
               print("User wins! Scissors cuts Paper.")
               return 0
          else:
               return -1
          
     # computer chooses scissors
     elif(compChoice == "SCISSORS"): 
          if(userChoice == "PAPER"):
               print("Computer wins! Scissors cuts Paper.")
               return 0
          elif(userChoice == "ROCK"):
               print("User wins! Rock smashes Scissors.")
               return 0
          else:
               return -1
          
def main():
     computersChoice = computerChoice() # gets computer's choice
     usersChoice = userChoice() # gets user's input
     
     # flag variable used to indicate if it was a tie
     flag = winner(computersChoice, usersChoice)
     
     # checks if game resulted in a tie, if yes, then  it plays again
     while(flag == -1):
          print("It's a TIE! Play again...")
          flag = winner(computerChoice(), userChoice())
     
# calls main method       
main()

