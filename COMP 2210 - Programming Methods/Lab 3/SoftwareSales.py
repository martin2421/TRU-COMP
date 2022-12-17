# asks user to input number of packages purchased
packages = int(input("Enter the number of packages purchased: "))

# calculates discount based on number of packages
discount = 0
if(packages < 10):
     print("No discount available!")
elif(packages >= 10 and packages <= 19):
     discount = 0.1
     print("Discount available: 10%")
elif(packages >= 20 and packages <= 49):
     discount = 0.2
     print("Discount available: 20%")
elif(packages >= 50 and packages <= 99):
     discount = 0.3
     print("Discount available: 30%")
elif(packages >= 100):
     discount = 0.4
     print("Discount available: 40%")
     
# print results     
print(f"Your total without discount: ${(99 * packages)}" )
print(f"Your total after discount: ${((99 * packages) * (1 - discount))}")