# ask user to input age
age = int(input("Enter age of person: "))

# prints the classification of the person
if(age <= 1):
     print("Person is an infant.")
elif(age > 1 and age < 13):
     print("Person is a child.")
elif(age >= 13 and age < 20):
     print("Person is a teenager.")
elif(age >= 20):
     print("Person is an adult.")
     