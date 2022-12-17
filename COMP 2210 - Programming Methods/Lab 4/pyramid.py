
# function to print a pyramid
def pyramid(num):

    # number of spaces
    k = num - 1

    # number of rows
    for i in range(0, num):
        for j in range(0, k):
            print(end=" ")

        k = k - 1

        # number of columns
        for j in range(0, i + 1):
            print("* ", end = "")

        print("\r")

num = 4
pyramid(num)
