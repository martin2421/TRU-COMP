# Question 2: Tip, Tax and Total

# asks user to input the cost of the meal
cost = float(input("Enter the cost of your meal: "))

# calculates tip and tax costs
tip = cost * 0.18
tax = cost * 0.07

# prints output
print(
    f"Cost of meal : ${cost:.2f}\n  Tip: ${tip:.2f}\n  Tax: ${tax:.2f}\nTotal: ${(cost + tip + tax):.2f}"
)
