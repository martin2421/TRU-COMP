#include <iostream>

using std::cin;
using std::cout;
using std::endl;

int main()
{

     // variables
     const double PI = 3.14;
     double radius, diameter, area, circumference;

     // input
     cout << "Enter the value for radius: ";
     cin >> radius;

     // calculations
     diameter = 2 * radius;
     area = PI * radius * radius;
     circumference = 2 * PI * radius;

     // output
     cout << endl << "Diameter is " << diameter << endl;
     cout << "Area is " << area << endl;
     cout << "Circumference is " << circumference << endl;

     return 0;
}