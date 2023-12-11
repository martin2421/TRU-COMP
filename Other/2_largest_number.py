def max(num1, num2, num3):
    if (num1 >= num2 and num1 >= num3): # checks that num1 is largest
        return num1
    elif (num2 >= num1 and num2 >= num3): # checks that num2 is largest
        return num2
    else:
        return num3 # num3 is largest
    
def main(): 
    num1 = int(input("Enter your first number: "))
    num2 = int(input("Enter your second number: "))
    num3 = int(input("Enter your third number: "))
    
    print("Largest number: ", max(num1, num2, num3))
    
main()