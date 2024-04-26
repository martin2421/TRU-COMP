// Fig. 11.9: fig11_09.cpp
// Testing class BasePlusCommissionEmployee.
#include <iostream>
#include <iomanip>
#include "BasePlusCommissionEmployee.cpp" // class definition
#include "CommissionEmployee.cpp" // class definition
using namespace std;

int main()
{
     // instantiate BasePlusCommissionEmployee object
     BasePlusCommissionEmployee
         employee("Bob", "Lewis", "333-33-3333", 5000, .04, 300);

     // set floating-point output formatting
     cout << fixed << setprecision(2);

     // get commission employee data
     cout << "Employee information obtained by get functions: \n"
          << "\nFirst name is " << employee.getFirstName()
          << "\nLast name is " << employee.getLastName()
          << "\nSocial security number is "
          << employee.getSocialSecurityNumber()
          << "\nGross sales is " << employee.getGrossSales()
          << "\nCommission rate is " << employee.getCommissionRate()
          << "\nBase salary is " << employee.getBaseSalary() << endl;

     employee.setBaseSalary(1000); // set base salary

     cout << "\nUpdated employee information output by print function: \n"
          << endl;
     employee.print(); // display the new employee information

     // display the employee's earnings
     cout << "\n\nEmployee's earnings: $" << employee.earnings() << endl;

     // Commission Employee
     CommissionEmployee employee2("Martin", "Atanacio", "123-45-6789", 10000, .06);

     // set floating-point output formatting
     cout << fixed << setprecision(2);

     // get commission employee data
     cout << "Employee information obtained by get functions: \n"
          << "\nFirst name is " << employee2.getFirstName()
          << "\nLast name is " << employee2.getLastName()
          << "\nSocial security number is "
          << employee2.getSocialSecurityNumber()
          << "\nGross sales is " << employee2.getGrossSales()
          << "\nCommission rate is " << employee2.getCommissionRate() << endl;

     cout << "\nUpdated employee information output by print function: \n"
          << endl;
     employee2.print(); // display the new employee information

     // display the employee's earnings
     cout << "\n\nEmployee's earnings: $" << employee2.earnings() << endl;

     return 0;

} // end main