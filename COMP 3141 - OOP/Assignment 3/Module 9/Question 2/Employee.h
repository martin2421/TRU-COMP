// Fig. 10.23: employee.h
// Employee abstract base class.
#ifndef EMPLOYEE_H
#define EMPLOYEE_H

#include <string> // C++ standard string class
#include "Date.h"

using std::string;

class Employee
{

public:
   Employee(const string &, const string &, const string &, const Date &);

   void setFirstName(const string &);
   string getFirstName() const;

   void setLastName(const string &);
   string getLastName() const;

   void setSocialSecurityNumber(const string &);
   string getSocialSecurityNumber() const;

   Date getDate() const;

   virtual double earnings() const = 0; // pure virtual
   virtual void print() const;          // virtual

private:
   string firstName;
   string lastName;
   string socialSecurityNumber;
   Date birthDate;

}; // end class Employee

#endif // EMPLOYEE_H
