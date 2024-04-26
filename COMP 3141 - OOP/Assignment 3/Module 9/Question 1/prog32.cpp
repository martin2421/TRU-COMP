#include <iostream>
#include <iomanip>
#include <vector>
#include <typeinfo>
#include "Employee.cpp"
#include "HourlyEmployee.cpp"
#include "SalariedEmployee.cpp"
#include "CommissionEmployee.cpp"
#include "BasePlusCommissionEmployee.cpp"
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
      HourlyEmployee *derivedPtr2 = dynamic_cast<HourlyEmployee *>(employeePtr);

      // determine whether element points to a BasePlusCommissionEmployee
      if (derivedPtr != nullptr) // true for "is a" relationship
      {
         double oldBaseSalary = derivedPtr->getBaseSalary();                                       // get the old salary
         cout << "old base salary: $" << oldBaseSalary << endl;                                    // print old salary
         derivedPtr->setBaseSalary(1.10 * oldBaseSalary);                                          // provide a 10% raise
         cout << "new base salary with 10% increase is: $" << derivedPtr->getBaseSalary() << endl; // get the new salary
      } // end if

      // 10% raise for HourlyEmployee
      if (derivedPtr2 != nullptr)
      {
         double oldHourlyRate = derivedPtr2->getWage(); // get the old hourly rate
         cout << "old hourly rate: $" << oldHourlyRate << endl;
         derivedPtr2->setWage(1.10 * oldHourlyRate); // provide a 10% raise
         cout << "new hourly rate with 10% increase is: $" << derivedPtr2->getWage() << endl;
      }

      // downcast for earnings
      cout << "earned $" << employeePtr->earnings() << "\n";
   }
}
