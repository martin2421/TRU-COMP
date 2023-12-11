def hasRealSolution(a, b, c):
    if ((b**2) - (4 * a * c) < 0): # use formula
        return False
    else:
        return True
        
def main():
    print("Find if the numbers have a real solution")
    print("Formula used:    b^2 - 4ac")
    
    a = int(input('Enter value of a: ')) # input from user
    b = int(input('Enter value of b: '))
    c = int(input('Enter value of c: '))
    
    if (hasRealSolution(a, b, c)): # check if true or false
        print("Formula has a real solution!")
    else:
        print("Formula does not have a real solution :(")
        
main()