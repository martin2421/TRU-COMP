// Fig. 9.20: Employee.cpp
// Employee class member-function definitions.
#include <iostream>
#include "Employee.h" // Employee class definition
#include "Time.h"     // Date class definition
using namespace std;

// constructor uses member initializer list to pass initializer
// values to constructors of member objects
Employee::Employee(const string &first, const string &last, const Time &inTime, const Time &outTime)
    : firstName(first), // initialize firstName
      lastName(last),   // initialize lastName
      inTime(inTime),   // initialize birthDate
      outTime(outTime)  // initialize hireDate
{
   // output Employee object to show when constructor is called
   cout << "Employee object constructor: "
        << firstName << ' ' << lastName << endl;
} // end Employee constructor

// print Employee object
void Employee::print() const
{
   cout << lastName << ", " << firstName << "  Came In at: ";
   inTime.printStandard();
   cout << "  Goes Out : ";
   outTime.printStandard();
   cout << endl;
} // end function print

// output Employee object to show when its destructor is called
Employee::~Employee()
{
   cout << "Employee object destructor: "
        << lastName << ", " << firstName << endl;
} // end ~Employee destructor
