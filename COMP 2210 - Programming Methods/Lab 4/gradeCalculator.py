
#
# User Defined Functions
#

def determine_grade(value):
     if(value >= 0 and value < 60):
          return f"{value} ==> F"
     elif(value >= 60 and value <= 69):
          return f"{value} ==> D"
     elif(value >= 70 and value <= 79):
          return f"{value} ==> C"
     elif(value >= 80 and value <= 89):
          return f"{value} ==> B"
     elif(value >= 90 and value <= 100):
          return f"{value} ==> A"
     else:
          return "Incorrect input..."
     
def calc_average(grade1, grade2, grade3, grade4, grade5):
     total = grade1 + grade2 + grade3 + grade4 + grade5
     average = total / 5
     return f"Your average is: {determine_grade(average)}"

#
# main() function
#

def main():
    print("Enter your test scores to calculate final grade and average.")
    
    grade1 = float(input("Enter first score: "))
    print(determine_grade(grade1))

    grade2 = float(input("Enter second score: "))
    print(determine_grade(grade2))

    grade3 = float(input("Enter third score: "))
    print(determine_grade(grade3))

    grade4 = float(input("Enter fourth score: "))
    print(determine_grade(grade4))

    grade5 = float(input("Enter fifth score: "))
    print(determine_grade(grade5))
    
    print(calc_average(grade1, grade2, grade3, grade4, grade5))
    
main()