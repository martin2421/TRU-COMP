#include "SalariedEmployee.h"

#include <iostream>

using std::cout;
using std::endl;

// SalariedEmployee constructor
SalariedEmployee::SalariedEmployee(const string &first,
const string &last, const string &socialSecurityNumber,
double salary, Date birthday)
    : Employee(first, last, socialSecurityNumber, birthday)
{
   setWeeklySalary(salary);

} // end SalariedEmployee constructor

// set salaried worker's salary
void SalariedEmployee::setWeeklySalary(double salary)
{
   weeklySalary = salary < 0.0 ? 0.0 : salary;

} // end function setWeeklySalary

// calculate salaried worker's pay
double SalariedEmployee::earnings() const
{
   double temp = getWeeklySalary();
   return temp * 4; // multiplied by 4 for each week

} // end function earnings

// return salaried worker's salary
double SalariedEmployee::getWeeklySalary() const
{
   return weeklySalary;

} // end function getWeeklySalary

// print salaried worker's name
void SalariedEmployee::print() const
{
   cout << "\nsalaried employee: ";
   Employee::print();

} // end function print
