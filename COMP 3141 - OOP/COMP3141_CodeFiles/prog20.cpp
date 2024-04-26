#include <iostream>

using namespace std;

int main()
{
   int var = 20; // actual variable declaration.
   int *ip;      // pointer variable

   ip = &var; // store address of var in pointer variable

   int var2 = 40;
   cout << "Value of var variable: ";
   cout << var << endl;

   // print the address stored in ip pointer variable
   cout << "Address stored in ip variable: ";
   cout << ip << endl;

   // access the value at the address available in pointer
   cout << "Value of *ip variable: ";
   cout << *ip << endl;

   // write the statements to use pointer variable *ip to be able to point to var2
   // use newly pointing variable, print the value 40
   ip = &var2;

   cout << "Value of *ip variable pointing to var2: ";
   cout << *ip << endl;

   return 0;
}
