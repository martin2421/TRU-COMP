// the program to demonstrate the simple and nested if conditions

#include <iostream>
#include <iomanip>
using namespace std;
int main()
{
      // processing phase
      // prompt for input and read grade from user
      cout << "Enter marks for the student: ";
      int studentMarks;
      cin >> studentMarks;    // input grade
      if (studentMarks >= 90) // 90 and above gets "A"
            cout << " You have got A\n";
      //@ make the if statements to print grades as
      // 80-89 gets "B"
      else if (studentMarks >= 80 && studentMarks <= 89)
            cout << " You have got B\n";
      // 70-79 gets "C"
      else if (studentMarks >= 70 && studentMarks <= 79)
            cout << " You have got C\n";
      // 60-69 gets "D"
      else if (studentMarks >= 60 && studentMarks <= 69)
            cout << " You have got D\n";
      // less than 60 gets "F"
      else
         cout << " You have got F\n";

      system("pause");
}

// end main
