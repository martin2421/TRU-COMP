#include <iostream>
#include <cstring> // for string functions
using namespace std;

void reverseString(char*);
bool compareStrings(char*, char*);
void concatenateStrings(char*, char*);

int main()
{
     char str1[100], str2[100]; // two arrays of characters to store strings
     int choice;                // user's choice

     do
     {
          // display menu
          cout << "\nMenu:\n";
          cout << "1. Input a string\n";
          cout << "2. Reverse a string\n";
          cout << "3. Compare two strings\n";
          cout << "4. Concatenate two strings\n";
          cout << "5. Quit\n";

          // get user choice
          cout << "Enter your choice: ";
          cin >> choice;

          switch (choice)
          {
          case 1: // input string
               cout << "Enter a string: ";
               cin.ignore(); // ignore previous \n character
               cin.getline(str1, 100); // input string
               break;

          case 2: // reverse string
               reverseString(str1);
               cout << "\nReversed string: " << str1 << endl;
               break;

          case 3: // compare strings
               cout << "Enter the second string: ";
               cin.ignore(); // ignore previous \n character
               cin.getline(str2, 100); // input string
               if (compareStrings(str1, str2)) // if strings are equal
                    cout << "\nThe strings are equal.\n";
               else
                    cout << "\nThe strings are different.\n";
               break;

          case 4: // concatenate strings
               cout << "Enter the second string: ";
               cin.ignore(); // ignore previous \n character
               cin.getline(str2, 100); // input string
               concatenateStrings(str1, str2);
               cout << "\nConcatenated string: " << str1 << endl;
               break;

          case 5:
               cout << "Goodbye!\n";
               break;

          default:
               cout << "Invalid choice.\n";
          }
     } while (choice != 5);

     return 0;
}

// function to reverse a string
void reverseString(char *str)
{
     int length = strlen(str); // get length of string

     // loop to swap characters from start and end
     for (int i = 0; i < length / 2; i++)
     {
          char temp = str[i];           // store character in temp
          str[i] = str[length - 1 - i]; // swap characters
          str[length - 1 - i] = temp;   // swap characters
     }
}

// function to compare two strings
bool compareStrings(char *str1, char *str2)
{
     // loop that checks each character of both strings
     while (*str1 && *str2 && *str1 == *str2)
     {
          str1++; // move to next character
          str2++; // move to next character
     }
     return *str1 == *str2;
}

// function to concatenate two strings
void concatenateStrings(char *str1, char *str2)
{
     // find the end of str1 by looping through it
     // (it will stop at the null character at the end of the string)
     while (*str1)
     {
          str1++;
     }

     // copy characters from str2 to the end of str1
     while (*str2)
     {
          *str1 = *str2; // copy character
          str1++;        // move to next character
          str2++;        // move to next character
     }

     // append null character to the end of the string
     *str1 = '\0';
}
