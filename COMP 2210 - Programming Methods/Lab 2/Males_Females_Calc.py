# Question 1: Male and Female Percentages

males = int(input("Enter the number of males: "))

# ask users to input the number of females in the class
females = int(input("Enter the number of females: "))

# calculates total number of students
total_students = males + females

# calculates males percentage
males_percentage = (males / total_students) * 100

# calculates female percentage
females_percentage = (females / total_students) * 100

# prints output
print(f"Total students: {total_students}")
print(f"Males in the class: {males_percentage:.2f}%")
print(f"Females in the class: {females_percentage:.2f}%")
