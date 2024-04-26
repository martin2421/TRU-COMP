#include <iostream>
#include <iomanip>
#include <vector>
#include <typeinfo>
#include "Employee.cpp"
#include "HourlyEmployee.cpp"
#include "SalariedEmployee.cpp"
#include "CommissionEmployee.cpp"
#include "BasePlusCommissionEmployee.cpp"
#include "Date.cpp"
using namespace std;

int main()
{
     // current date for example
     Date currentDate = Date(10, 04, 2024);
     cout << "Current Date: ";
     currentDate.getDate();

     vector<Employee *> employees(4);

     employees[0] = new SalariedEmployee("John", "Smith", "111-11-1111", 800, Date(1, 1, 2001));
     employees[1] = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .2, Date(2, 2, 2002));
     employees[2] = new HourlyEmployee("John", "Charles", "111-11-1111", 26, 40, Date(3, 3, 2003));
     employees[3] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300, Date(4, 4, 2004));

     // process each element in vector employees
     for (Employee *employeePtr : employees)
     {
          employeePtr->print(); // output employee information
          cout << endl;

          Date temp = employeePtr->getDate();
          temp.getDate();

          // get the basePlusCommission Employee to provide a raise
          BasePlusCommissionEmployee *derivedPtr = dynamic_cast<BasePlusCommissionEmployee *>(employeePtr);
          HourlyEmployee *derivedPtr2 = dynamic_cast<HourlyEmployee *>(employeePtr);

          // down cast for earnings
          if (temp.month == currentDate.month)
          {
               cout << "It's your birthday this month! " << endl;
               cout << "Earned this month: $" << employeePtr->earnings() << "\n";
               cout << "+$100 bonus.\n Total: $" << employeePtr->earnings() + 100;
          }
          else
          {
               cout << "Earned this month: $" << employeePtr->earnings() << "\n";
          }
     }
}
