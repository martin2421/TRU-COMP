#ifndef BASICCALCULATOR_H_INCLUDED
#define BASICCALCULATOR_H_INCLUDED

#include "Calculator.h"

class BasicCalculator : public Calculator
{
public:
    ~BasicCalculator();

    void add();
    void subtract();
    void multiply();
    void divide();
    void square();
    void squareRoot();
    void getNumbers();

private:
    double num2;
};

#endif // BASICCALCULATOR_H_INCLUDED
