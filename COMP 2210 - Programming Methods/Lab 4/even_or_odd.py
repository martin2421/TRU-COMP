import random as ri

# accepts a parameter and checks if number is even or odd
def even_or_odd(num):
    if (num % 2 == 0):
        return True
    else:
         return False
    
# main function     
def main():
     
     evenCount = 0
     
     # produces 100 random integers
     for i in range(100):
          num = ri.randint(1, 100)
          print(num)
          if(even_or_odd(num)):
               evenCount += 1
          
     print("Number of evens:", evenCount)
     print("Number of odds:", (100 - evenCount))
          
      
main()
     
     
