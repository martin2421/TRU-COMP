# ask user to input dimensions of first rectangle
width1 = float(input("Enter width of first rectangle: "))
length1 = float(input("Enter length of first rectangle: "))

# calculates area of first rectangle
area1 = width1 * length1

# ask user to input dimensions of second rectangle
width2 = float(input("Enter width of second rectangle: "))
length2 = float(input("Enter length of second rectangle: "))

# calculates area of second rectangle
area2 = width2 * length2

# outputs which area is bigger
if(area1 == area2):
     print(f"Both areas are the same: {area1}cm^2")
elif(area1 > area2):
     print(f"Area of first rectangle, {area1}cm^2, is bigger than the area of the second rectangle, {area2}cm^2")
elif(area2 > area1):
     print(f"Area of second rectangle, {area2}cm^2, is bigger than the area of the first rectangle, {area1}cm^2")