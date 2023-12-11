def intro():
    print("Cups to Ounces Converter")
    print("(1 Cup = 8 Fluid Ounces)")
    
def ounces(cups):
    return cups * 8 # 1 cup = 8 ounces
    
def main():
    intro()
    cups = int(input("Enter the number of cups to convert to ounces: ")) # input from user
    print(f"{cups} Cups to Ounces => {ounces(cups)} Ounces")
    
main() # call the main method