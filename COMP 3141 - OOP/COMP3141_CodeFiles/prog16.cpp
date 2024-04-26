// Implement recursion on a sequential code
#include <iostream>
using namespace std;

int fib(int n)
{
  if (n == 0 || n == 1)
    return 1;
  else
    return fib(n - 1) + fib(n - 2);
}
// Convert the fib function to generate fibonacci series recursively
int main()
{
  int n;

  cout << "Enter a non-negative integer: ";
  cin >> n;

  cout << "Fibonacci Series up to " << n << " terms: ";
  for (int i = 0; i <= n; i++)
  {
    cout << fib(i) << " ";
  }
  cout << endl;
  return 0;
}
