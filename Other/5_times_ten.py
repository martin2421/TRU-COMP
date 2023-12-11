def times_ten(num):
    return num * 10 # returns number * 10
    
def main():
    print("Input a number to be multiplied by ten")
    num = int(input("Enter a number: ")) # input form user
    
    print(f"{num} * 10 = {times_ten(num)}")
    
main()