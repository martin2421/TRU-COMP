#include <iostream>
#include <stdio.h>

using namespace std;

template <class type>
type add(type num1, type num2); // template definition
template <class type>
type subtract(type num1, type num2); // template definition

int main()
{
  float num1, num2, answer;
  // test by changing float to int or double. Do you have any other change in the program?
  cout << "Please enter integer 1 : ";
  cin >> num1;
  cout << "Please enter integer 2 : ";
  cin >> num2;
  answer = add(num1, num2);
  cout << "The sum of " << num1 << " & " << num2 << " = " << answer;
  answer = subtract(num1, num2);
  cout << "The difference of " << num1 << " & " << num2 << " = " << answer;
  return 0;
}

template <class type>
type add(type num1, type num2)
{

  type result = num1 + num2;
  return result;
}
template <class type>
type subtract(type num1, type num2)
{

  type result = num1 - num2;
  return result;
}

// Add two more functions for multiplication and division in the code and submit it as this modules assessment code to instructor
