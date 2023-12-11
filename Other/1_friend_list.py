# function to display dictionary
def displayDictionary(dictionary):
    print("\nPhone Number\tName")
    for phone, name in dictionary.items(): # loop through the key-value pairs
        print(f"{phone}:\t{name}")

# function to add a friend to the dictionary
def addFriend(dictionary):
    print("\nEnter Friend's details:") 
    phone = input("Enter 10-digit phone number: ")
    name = input("Enter name: ")
    dictionary[phone] = name # add value (name) to key (phone)
    print("Record added!")
    displayDictionary(dictionary)

# function to remove a friend from the dictionary
def deleteFriend(dictionary):
    phone = input("\nEnter phone number to delete: ")
    if phone in dictionary: # check if phone exists in the dictionary
        del dictionary[phone] # deletes it
        print("Record deleted!")
        displayDictionary(dictionary)
    else:
        print("Phone Number not found!")

# function to change the information of a friend in the dictionary
def changeFriend(dictionary):
    old_phone = input("\nEnter phone number to change: ")
    del dictionary[old_phone] # delete phone number
    addFriend(dictionary) # add a new entry 
    print("Record updated!")

# main function
def main():
    dictionary = {} # initialize the empty dictionary
    
    # infinite loop
    while True:
        print("\nOptions:")
        print("1. Display friend's list")
        print("2. Add a friend")
        print("3. Delete a friend")
        print("4. Change a friend")

        choice = input("Enter your choice (1-4): ")

        if choice == '1':
            displayDictionary(dictionary)
        elif choice == '2':
            addFriend(dictionary)
        elif choice == '3':
            deleteFriend(dictionary)
        elif choice == '4':
            changeFriend(dictionary)
        else:
            print("Invalid choice!")

        option = input("\nDo you want to continue (y/n)? ").lower()
        if option != 'y':
            break

main()
