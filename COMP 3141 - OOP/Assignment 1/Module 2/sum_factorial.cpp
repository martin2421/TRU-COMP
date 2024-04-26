#include <iostream>

using namespace std;

// calculates factorial using recursion
double factorial(int n)
{
     if (n == 0) {
          return 1;
     }
     
     double result = 1;

     for (int i = 1; i <= n; ++i)
     {
          result *= i;
     }
     return result;
}

int main()
{
     int n;
     cout << "Enter a positive number: ";
     cin >> n;

     double sum = 1; // sum = first term of the sequence
     for (int i = 1; i <= n; ++i)
     {
          sum += i / factorial(i);
     }

     cout << "Sum of sequence: " << sum << endl;

     return 0;
}
