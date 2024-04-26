// Fig. 2.5: fig02_05.cpp
// Addition program that displays the sum of two integers.
#include <iostream> // allows program to perform input and output

// function main begins program execution
int main()
{
   // variable declarations
   int number1 = 0; // first integer to add (initialized to 0)
   int number2 = 0; // second integer to add (initialized to 0)
   int sum = 0; // sum of number1 and number2 (initialized to 0)

   std::cout << "Enter first integer: "; // prompt user for data
   std::cin >> number1; // read first integer from user into number1

   std::cout << "Enter second integer: "; // prompt user for data
   std::cin >> number2; // read second integer from user into number2

   sum = number1 + number2; // add the numbers; store result in sum
   
   //@ perform subtraction of number1 from number2
   //@ perform multiplication of number1 and number2 and store in product
   //@ perform the division of number1 with number2 and print result

   std::cout << "Sum is " << sum << std::endl; // display sum; end line
   //@ display result of subtraction
   //@ display sum of multiplication
   //@ display result of division
   
} // end function main

