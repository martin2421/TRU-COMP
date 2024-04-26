#include <iostream>

using namespace std;

// checks if a number is a palindrome
bool isPalindrome(int num)
{
     int original = num; // original number
     int reversed = 0;   // number reversed
     int digit;          // current digit to be worked on

     while (num != 0)
     {
          digit = num % 10;
          reversed = reversed * 10 + digit;
          num /= 10;
     }

     return original == reversed;
}

// adds all the digits of a number
int sumDigits(int num)
{
     int sum = 0;

     while (num > 0 || sum > 9)
     {
          if (num == 0)
          {
               num = sum;
               sum = 0;
          }
          sum += num % 10;
          num /= 10;
     }

     return sum;
}

int main()
{
     int num;
     cout << "Enter a number up to 5 digits: ";
     cin >> num;

     // input validity
     if (num < 0 || num > 99999)
     {
          cout << "Invalid number. Enter a number up to 5 digits." << endl;
     }

     // check if the number is a palindrome
     if (isPalindrome(num))
     {
          cout << num << " is a palindrome." << endl;
     }
     else
     {
          cout << num << " is not a palindrome." << endl;
     }

     // find the sum of all digits
     int singleDigitSum = sumDigits(num);
     cout << "Sum of all digits = " << singleDigitSum << endl;

     return 0;
}
