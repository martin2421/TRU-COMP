#ifndef SCIENTIFICCALCULATOR_H_INCLUDED
#define SCIENTIFICCALCULATOR_H_INCLUDED

#include "Calculator.h"

class ScientificCalculator : public Calculator
{
public:
    ~ScientificCalculator();

    void getSin();
    void getCos();
    void getTan();
    void getLog();
    void getLog10();

private:
    double num2;
};

#endif // SCIENTIFICCALCULATOR_H_INCLUDED
