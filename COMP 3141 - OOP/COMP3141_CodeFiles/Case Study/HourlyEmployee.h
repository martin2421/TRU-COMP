// Fig. 10.27: hourly.h
// HourlyEmployee class definition.
#ifndef HOURLY_H
#define HOURLY_H

#include "employee.h"  // Employee class definition

class HourlyEmployee : public Employee {

public:
   HourlyEmployee( const string &, const string &, const string &, double = 0.0, double = 0.0);

   void setWage( double );
   double getWage() const;

   void setHours( double );
   double getHours() const;

   virtual double earnings() const;
   virtual void print() const;

private:
   double wage;   // wage per hour
   double hours;  // hours worked for week

}; // end class HourlyEmployee

#endif // HOURLY_H
