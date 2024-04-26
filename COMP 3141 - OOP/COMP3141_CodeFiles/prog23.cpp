#include <iostream>
#include <stdexcept> // for invalid_argument exception class
#include "Time.h"    // include definitionof class Time from Time.h
#include "Employee.h"
using namespace std;

int main()
{
    Time inT(23, 10, 3);
    Time outT(24, 0, 0);
    // inT.setTime();
    Employee manager("Mike", "Mathew", inT, outT);
    cout << endl;
    manager.print();
}
