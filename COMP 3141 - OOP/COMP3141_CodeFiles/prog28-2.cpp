// Fig. 11.9: fig11_09.cpp
// Testing class BasePlusCommissionEmployee.
#include <iostream>
#include <iomanip>
#include "CommissionEmployee.cpp"         // class definition
#include "BasePlusCommissionEmployee.cpp" // class definition
using namespace std;

int main()
{
     // instantiate CommissionEmployee object
     CommissionEmployee employee("Bob", "Lewis", "333-33-3333", 5000, .04);

     // print CommissionEmployee object
     cout << "Commission Employee: " << endl;
     employee.print();

     // instantiate BasePlusCommissionEmployee object
     BasePlusCommissionEmployee basePlusEmployee("Alice", "Johnson", "444-44-4444", 6000, .05, 300);

     // print BasePlusCommissionEmployee object
     cout << "\nBase Plus Commission Employee: " << endl;
     basePlusEmployee.print();

     return 0;
}