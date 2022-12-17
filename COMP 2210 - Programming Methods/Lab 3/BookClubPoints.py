# asks user to input number of books purchased
books = int(input("Enter the number of books you've purchased this month: "))

# calculates number of points
points = 0
if(books == 2):
     points = 5
elif(books == 4):
     points  = 15
elif(books == 6):
     points  = 30
elif(books >= 8):
     points  = 60

# print results
print(f"Points awarded: {points} points for {books} books purchased.")
