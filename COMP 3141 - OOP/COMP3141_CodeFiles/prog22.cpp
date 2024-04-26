#include <iostream>
#include <stdexcept> // for invalid_argument exception class
#include "Time.cpp"  // include definitionof class Time from Time.h
using namespace std;

int main()
{
   Time t; // instantiate object t of class Time

   // output Time object t's initial values
   cout << "The initial universal time is ";
   t.printUniversal(); // 00:00:00
   cout << "\nThe initial standard time is ";
   t.printStandard();    // 12:00:00 AM
   t.setTime(13, 27, 6); // change time

   // output Time object t's new values
   cout << "\n\nUniversal time after setTime is ";
   t.printUniversal(); // 13:27:06
   cout << "\nStandard time after setTime is ";
   t.printStandard();     // 1:27:06 PM
   t.setTime(99, 99, 99); // all values out of range

   cout << "\nAfter attempting invalid settings:" << "\nUniversal time: ";
   t.printUniversal(); // 13:27:06
   cout << "\nStandard time: ";
   t.printStandard(); // 1:27:06 PM
   cout << endl;
} // end main
