#include <iostream>
#include <iomanip>
#include <vector>
#include <typeinfo>
#include "Employee.h"
#include "HourlyEmployee.h"
#include "SalariedEmployee.h"
#include "CommissionEmployee.h"
#include "BasePlusCommissionEmployee.h"
using namespace std;

int main()
{
   vector<Employee *> employees(4);

   // initialize vector with various kinds of Employees
   employees[0] = new SalariedEmployee("John", "Smith", "111-11-1111", 800);
   employees[1] = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
   employees[2] = new HourlyEmployee("John", "Charles", "111-11-1111", 26, 80);
   employees[3] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);

   // polymorphically process each element in vector employees
   for (Employee *employeePtr : employees)
   {
      employeePtr->print(); // output employee information
      cout << endl;

      // get the basePlusCommission Employee to provide a raise
      BasePlusCommissionEmployee *derivedPtr = dynamic_cast<BasePlusCommissionEmployee *>(employeePtr);

      // determine whether element points to a BasePlusCommissionEmployee
      if (derivedPtr != nullptr) // true for "is a" relationship
      {
         double oldBaseSalary = derivedPtr->getBaseSalary();                                       // get the old salary
         cout << "old base salary: $" << oldBaseSalary << endl;                                    // print old salary
         derivedPtr->setBaseSalary(1.10 * oldBaseSalary);                                          // provide a 10% raise
         cout << "new base salary with 10% increase is: $" << derivedPtr->getBaseSalary() << endl; // get the new salary
      } // end if

      cout << "earned $" << employeePtr->earnings() << "\n";
      // downcast pointer

      //@ STUDENT TO DO : Change the Hourly rate of the Hourly Employee and give 10% increase in houlry rate
   }
}
