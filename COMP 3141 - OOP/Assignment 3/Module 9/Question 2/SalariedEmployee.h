// Fig. 10.25: salaried.h
// SalariedEmployee class derived from Employee.
#ifndef SALARIED_H
#define SALARIED_H

#include "Employee.h" // Employee class definition

class SalariedEmployee : public Employee
{

public:
   SalariedEmployee(const string &, const string &, const string &, double = 0.0, Date birthday = Date(0, 0, 0));

   void setWeeklySalary(double);
   double getWeeklySalary() const;

   virtual double earnings() const;
   virtual void print() const; 

private:
   double weeklySalary;

}; // end class SalariedEmployee

#endif // SALARIED_H
