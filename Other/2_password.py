# function to check length of password
def checkLength(password):
    return len(password) >= 10

# function to check if password has uppercase letter
def checkUppercase(password):
    valid = False
    for letter in password: # loop through each letter in the string
         if letter.isupper(): valid = True
    return valid

# function to check if password has lowercase letter
def checkLowercase(password):
    valid = False
    for letter in password: # loop through each letter in the string
         if letter.islower(): valid = True
    return valid

# function to check if password has a digit
def checkDigit(password):
    valid = False
    for letter in password: # loop through each letter in the string
         if letter.isdigit(): valid = True
    return valid

# function to check if password is valid
def checkValid(password):
    return (
        checkLength(password) and
        checkUppercase(password) and
        checkLowercase(password) and
        checkDigit(password)
    ) # all the tests must be valid for the password to be valid

# function to get password from user
def inputPassword():
    while True:
        password = input("\nEnter your password: ")
        if checkValid(password):
            print("Password is valid!")
            break
        else:
            print("Password is NOT valid!")

# main function
def main():
    print("\nPassword Requirements:")
    print("* Must be at least 10 characters long.")
    print("* Must contain at least one uppercase letter.")
    print("* Must contain at least one lowercase letter.")
    print("* Must contain at least one numeric digit.")

    inputPassword()
    
main()
