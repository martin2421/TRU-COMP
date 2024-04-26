#include <iostream>
#include <conio.h>
using namespace std;
int main()
{
   int a, b, c;
   float d;
   // clrscr();
   cout << "Enter the value of a:";
   cin >> a;
   cout << "Enter the value of b same as a (for testing):";
   cin >> b;
   cout << "Enter the value of c:";
   cin >> c;

   try
   {
      if ((a - b) != 0)
      {
         d = c / (a - b);
         cout << "Result is:" << d;
      }
      else
      {
         throw(a - b);
      }
   }

   catch (int i)
   {
      cout << "Exception thrown! Answer is infinite as denominator is:" << i;
   }

   return 0;
}

// remove try and catch block and see what happens
