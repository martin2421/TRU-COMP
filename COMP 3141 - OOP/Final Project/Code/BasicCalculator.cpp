#include <iostream>
#include <math.h>
#include <stdexcept>
#include "BasicCalculator.h"
#include "DivideByZeroException.h"

using namespace std;

// gets two inputs from the user
void BasicCalculator::getNumbers()
{
    // get first number
    do
    {
        cout << "Enter first number: ";
        cin >> num1;
        if (cin.fail())
        {
            cout << "Incorrect value. Try again!\n";
            cin.clear();
            cin.ignore(100, '\n');
        }
    } while (cin.fail());

    // get second number
    do
    {
        cout << "Enter second number: ";
        cin >> num2;
        if (cin.fail())
        {
            cout << "Incorrect value. Try again!\n";
            cin.clear();
            cin.ignore(100, '\n');
        }
    } while (cin.fail());
}

// adds the two numbers
void BasicCalculator::add()
{
    // gets two numbers from user
    getNumbers();
    cout << num1 << " + " << num2 << " = " << (num1 + num2) << endl;
}

// subtracts the two numbers
void BasicCalculator::subtract()
{
    // gets two numbers from user
    getNumbers();
    cout << num1 << " - " << num2 << " = " << (num1 - num2) << endl;
}

// multiplies the two numbers
void BasicCalculator::multiply()
{
    getNumbers();
    cout << num1 << " * " << num2 << " = " << (num1 * num2) << endl;
}

// divides the two numbers
void BasicCalculator::divide()
{
    // gets two numbers from user
    getNumbers();

    // if num2 is 0, throw an exceptions
    if (num2 == 0)
    {
        throw DivideByZeroException{};
    }

    cout << num1 << " / " << num2 << " = " << (num1 / num2) << endl;
}

// finds the square root of first number
void BasicCalculator::squareRoot()
{
    // gets one number from user
    Calculator::getNumber();
    cout << "sqrt(" << num1 << ") = " << sqrt(num1) << endl;
}

// squares the first number
void BasicCalculator::square()
{
    // gets one number from user
    Calculator::getNumber();
    cout << num1 << "^2 = " << (num1 * num1) << endl;
}

// destructor
BasicCalculator::~BasicCalculator()
{
    cout << endl << "Exiting Basic Calculator" << endl;
}
