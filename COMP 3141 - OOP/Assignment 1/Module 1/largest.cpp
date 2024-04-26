#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main()
{

     // variables
     int num1, num2;

     // input
     cout << "First integer: ";
     cin >> num1;
     cout << "Second integer: ";
     cin >> num2;

     // output
     if (num1 > num2)
     {
          cout << num1 << " is larger." << endl;
     }
     else if (num2 > num1)
     {
          cout << num2 << " is larger." << endl;
     }
     else
     {
          cout << "The numbers are equal." << endl;
     }

     return 0;
}
