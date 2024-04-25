import numpy as np
from scipy.integrate import quad

# Constants for species A (propylene)
k1 = 0.40556
k2 = 0.00973
k3 = 0.01744
k4 = 6.81341
k5 = 0.07300

X1 = 0.801
X2 = 0.021
X3 = 0.027
X4 = 0.107
X5 = 0.027

Fp0 = 0.236
Fnh30 = 0.283
F020 = 2.5

# Define the rate equation for species A
def rate_equation_A(x):
    ra = (
        -k1 * Fp0 * (1 - X1) * Fnh30 * (1 - X1) * F020 ** (3 / 2) * (1 - X1) ** (3 / 2) +
        2 * (
            -k2 * Fp0 ** 2 * (1 - X2) ** 2 * Fnh30 ** 3 * (1 - X2) ** 3 * F020 ** (3 / 2) * (1 - X2) ** (3 / 2)) +
            -k3 * Fp0 * (1 - X3) * Fnh30 ** 3 * (1 - X3) ** 3 * F020 ** 3 * (1 - X3) ** 3 +
            -k4 * Fp0 * (1 - X4) * F020 ** (3 / 2) * (1 - X4) ** (3 / 2) +
            -k5 * Fp0 * (1 - X5) * F020 ** (1 / 2) * (1 - X5) ** (1 / 2)
    )
    return ra

# Perform the integration to calculate weight of the catalyst for species A
w1, _ = quad(lambda x: Fp0 / (-rate_equation_A(x)), 0, 0.9793)

# Constants for species B
k1b = 0.40556
k2b = 0.00973
k3b = 0.01744

X1b = 0.801
X2b = 0.021
X3b = 0.027

# Define the rate equation for species B
def rate_equation_B(x):
    rb = (
        -k1b * Fp0 * (1 - X1b) * Fnh30 * (1 - X1b) * F020 ** (3 / 2) * (1 - X1b) ** (3 / 2) +
        3 * (
            2 * -k2b * Fp0 ** 2 * (1 - X2b) ** 2 * Fnh30 ** 3 * (1 - X2b) ** 3 * F020 ** (3 / 2) * (1 - X2b) ** (3 / 2) +
            -k3b * Fp0 * (1 - X3b) * Fnh30 ** 3 * (1 - X3b) ** 3 * F020 ** 3 * (1 - X3b) ** 3
        )
    )
    return rb

# Perform the integration to calculate weight of the catalyst for species B
w2, _ = quad(lambda x: Fnh30 / (-rate_equation_B(x)), 0, 0.75125)

# Constants for species C
k1c = 0.40556
k2c = 0.00973
k3c = 0.01744
k4c = 6.81341
k5c = 0.07300

X1c = 0.801
X2c = 0.021
X3c = 0.027
X4c = 0.107
X5c = 0.027

# Define the rate equation for species C
def rate_equation_C(x):
    rc = (
        3/2 * (-k1c * Fp0 * (1 - X1c) * Fnh30 * (1 - X1c) * F020 ** (3 / 2) * (1 - X1c) ** (3 / 2)) +
        3/2 * (2 * -k2c * Fp0 ** 2 * (1 - X2c) ** 2 * Fnh30 ** 3 * (1 - X2c) ** 3 * F020 ** (3 / 2) * (1 - X2c) ** (3 / 2)) +
        3 * (-k3c * Fp0 * (1 - X3c) * Fnh30 ** 3 * (1 - X3c) ** 3 * F020 ** 3 * (1 - X3c) ** 3) +
        3/2 * (-k4c * Fp0 * (1 - X4c) * F020 ** (3 / 2) * (1 - X4c) ** (3 / 2)) +
        1/2 * (-k5c * Fp0 * (1 - X5c) * F020 ** (1 / 2) * (1 - X5c) ** (1 / 2))
    )
    return rc

# Perform the integration to calculate weight of the catalyst for species C
w3, _ = quad(lambda x: F020 / (-rate_equation_C(x)), 0, 0.9709)

# Sum of weights for all species
total_weight = w1 + w2 + w3

# Calculate conversion for each reactor
# First reactor (x6)
x6, _ = quad(lambda x: Fp0 / (-rate_equation_A(x)), 0, total_weight / 4)

# Second reactor (x7)
Fp1 = Fp0 * (1 - x6)
x7, _ = quad(lambda x: Fp1 / (-rate_equation_A(x)), x6, x6 + 0.1)  # Adjust upper limit as needed

# Third reactor (x8)
Fp2 = Fp1 * (1 - x7)
x8, _ = quad(lambda x: Fp2 / (-rate_equation_A(x)), x7, x7 + 0.1)  # Adjust upper limit as needed

# Fourth reactor (conversion of reactor 4, x9)
Fp3 = Fp2 * (1 - x8)
x9, _ = quad(lambda x: Fp3 / (-rate_equation_A(x)), x8, x8 + 0.1)  # Adjust upper limit as needed


# Sum of conversions for all reactors
total_conversion_A = x6 + x7 + x8 + x9

print("Weight of the catalyst for species A (propylene):", w1)
print("Weight of the catalyst for species B:", w2)
print("Weight of the catalyst for species C:", w3)
print("Total weight of the catalyst for all species:", total_weight)

print("Conversion of reactor 1 for species A:", x6)
print("Conversion of reactor 2 for species A:", x7)
print("Conversion of reactor 3 for species A:", x8)
print("Conversion of reactor 4 for species A:", x9)
print("Sum of conversions for all reactors for species A:", total_conversion_A)