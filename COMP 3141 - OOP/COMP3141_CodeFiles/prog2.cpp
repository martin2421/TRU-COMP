// The sample program to show the structure of a C++ program

#include <iostream>
using std::cout; // program uses cout
using std::cin; // program uses cin
using std::endl; // program uses endl
int main()
{
  int length, width;
  int perimeter, area;              // declarations
  cout <<  "Length = ";             // prompt user
  cin >> length;                    // enter length
  cout << "Width = ";               // prompt user
  cin >> width;                     // input width
  perimeter = 2*(length+width);     // compute perimeter
  area = length*width;              // compute area
  cout << endl   << "Perimeter is " << perimeter;
  cout << endl   << "Area is " << area   << endl; // output results
} // end of main program
