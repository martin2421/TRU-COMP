#include <iostream>
#include <string>
using namespace std;

int main()
{
  int i, n;
  int *p; // define the pointer to the integer data
  cout << "How many numbers would you like to type? ";
  cin >> i;                 // taking the input for dynamically allocating the memory
  p = new (nothrow) int[i]; // new keyword is used to allocate the memory
  if (p == nullptr)
    cout << "Error: memory could not be allocated";
  else
  {
    for (n = 0; n < i; n++)
    {
      cout << "Enter number: ";
      cin >> p[n];
    }
    cout << "You have entered: ";
    for (n = 0; n < i; n++)
      cout << p[n] << ", ";

    // write a step to increase the size of the same array to i + 20
    // take more inputs from user and add to the same array

    delete[] p; // deallocating the memory with delete keyword
  }
  return 0;
}
