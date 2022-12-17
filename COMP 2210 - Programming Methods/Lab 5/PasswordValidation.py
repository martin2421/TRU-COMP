# validates a user password
def valid_password(password):
     
     # boolean variables to check requirements
     correct_length = False
     has_uppercase = False
     has_lowercase = False
     has_digit = False
     
     # checks length
     if(len(password) >= 7):
          correct_length = True
          
          # iterate through each character and compare with criteria
          for character in password:
               if(character.isupper()):
                    has_uppercase = True
               if(character.islower()):
                    has_lowercase = True
               if(character.isdigit()):
                    has_digit = True
              
     # checks if criteria has been met      
     if(correct_length and has_uppercase and has_lowercase and has_digit):
          return True
     else:
          return False
   
# sentinel value
valid = False

while(not valid):
     
     # asks user for input
     string = input("Enter your password: ")
     
     # runs function and prints output
     if(valid_password(string)):
          print("Valid password!")
          valid = True
     else:
          print("Invalid password!")     


