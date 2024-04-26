#include <iostream>
#include <stdio.h>

using namespace std;

// template definitions
template <class T>
T add(T num1, T num2); 
template <class T>
T subtract(T num1, T num2);
template <class T>
T multiply(T num1, T num2);
template <class T>
T divide(T num1, T num2);

int main()
{
     float num1, num2, answer;
     // test by changing float to int or double. Do you have any other change in the program?
     cout << "Please enter float 1 : ";
     cin >> num1;
     cout << "Please enter float 2 : ";
     cin >> num2;

     // testing out functions
     answer = add(num1, num2);
     cout << "The sum of " << num1 << " + " << num2 << " = " << answer << "\n";

     answer = subtract(num1, num2);
     cout << "The difference of " << num1 << " - " << num2 << " = " << answer << "\n";

     answer = multiply(num1, num2);
     cout << "The product of " << num1 << " x " << num2 << " = " << answer << "\n";

     answer = divide(num1, num2);
     cout << "The quotient of " << num1 << " / " << num2 << " = " << answer << "\n";

     return 0;
}

template <class T>
T add(T num1, T num2)
{
     T result = num1 + num2;
     return result;
}
template <class T>
T subtract(T num1, T num2)
{
     T result = num1 - num2;
     return result;
}
template <class T>
T multiply(T num1, T num2)
{
     T result = num1 * num2;
     return result;
}
template <class T>
T divide(T num1, T num2)
{
     T result = num1 / num2;
     return result;
}
