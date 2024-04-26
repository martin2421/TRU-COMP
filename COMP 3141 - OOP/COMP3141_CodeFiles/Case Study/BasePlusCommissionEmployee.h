#ifndef BASEPLUS_H
#define BASEPLUS_H

#include <string>               // C++ standard string class
#include "CommissionEmployee.h" // CommissionEmployee class declaration

class BasePlusCommissionEmployee : public CommissionEmployee
{
private:
   double baseSalary; // base salary

public:
   BasePlusCommissionEmployee(const std::string &, const std::string &, const std::string &, double = 0.0, double = 0.0, double = 0.0);
   ~BasePlusCommissionEmployee();

   void setBaseSalary(double);   // set base salary
   double getBaseSalary() const; // return base salary

   double earnings() const; // calculate earnings
   void print() const;      // print BasePlusCommissionEmployee object
};

#endif
