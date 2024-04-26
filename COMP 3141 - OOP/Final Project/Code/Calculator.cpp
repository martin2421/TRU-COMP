#include <iostream>
#include "Calculator.h"

using namespace std;

// default constructor
Calculator::Calculator()
{
    num1 = 0;
}

// get another input from the user
void Calculator::getNumber()
{
    do
    {
        cout << "Enter a number: ";
        cin >> num1;

        if (cin.fail()) // check if input is a number
        {
            cout << "Incorrect value. Try again!\n";
            cin.clear(); // clear the error flag
            cin.ignore(100, '\n'); // discard the row including \n
        }
    } while (cin.fail());
}

// returns the first number
double Calculator::getFirstNumber()
{
    return num1;
}

// sets the first number
void Calculator::setFirstNumber(int num)
{
    num1 = num;
}

// destructor
Calculator::~Calculator()
{
}
