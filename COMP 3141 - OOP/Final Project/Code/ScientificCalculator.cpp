#include <iostream>
#include <math.h>
#include "ScientificCalculator.h"

using namespace std;

// gets the sin of a number
void ScientificCalculator::getSin()
{
    Calculator::getNumber();
    cout << "sin(" << num1 << ") = " << sin(num1) << endl;
}

// gets the cos of a number
void ScientificCalculator::getCos()
{
    Calculator::getNumber();
    cout << "cos(" << num1 << ") = " << cos(num1) << endl;
}

// gets the tan of a number
void ScientificCalculator::getTan()
{
    Calculator::getNumber();
    cout << "tan(" << num1 << ") = " << tan(num1) << endl;
}

// gets the log of a number
void ScientificCalculator::getLog()
{
    Calculator::getNumber();
    cout << "log(" << num1 << ") = " << log(num1) << endl;
}

// gets the log10 of a number
void ScientificCalculator::getLog10()
{
    Calculator::getNumber();
    cout << "log10(" << num1 << ") = " << log10(num1) << endl;
}

// destructor
ScientificCalculator::~ScientificCalculator()
{
    cout << endl << "Exiting Scientific Calculator" << endl;
}
