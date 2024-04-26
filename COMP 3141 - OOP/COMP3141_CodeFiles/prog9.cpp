#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
   int total = 0;                 // sum of grades entered by user
   unsigned int gradeCounter = 0; // number of grades entered
   int i = 0;
   // processing phase
   // prompt for input and read grade from user
   cout << "Enter twenty grades or -9 to stop: ";
   int grade = 0; // grade value
   cin >> grade;  // input grade or sentinel value

   // loop until sentinel value read from user
   while (grade != -9 && i < 19) // while grade is not -9 and not 20 grades entered
   {
      total = total + grade;           // add grade to total
      gradeCounter = gradeCounter + 1; // increment counter

      // prompt for input and read next grade from user
      cout << "Enter grade or -9 to stop: ";
      cin >> grade; // input grade or sentinel value
      i++;
   } // end while

   // termination phase
   if (gradeCounter != 0) // if user entered at least one grade...
   {
      // calculate average of all grades entered
      double average = static_cast<double>(total) / gradeCounter;

      // display total and average (with two digits of precision)
      cout << "\nTotal of all " << gradeCounter << " grades entered is "
           << total << endl;
      cout << setprecision(2) << fixed;
      cout << "Class average is " << average << endl;
   }    // end if
   else // no grades were entered, so output appropriate message
      cout << "No grades were entered" << endl;

   system("pause");
}

//@ exercise 1 : change the same program to enter 20 grades
//@ exercide 2 : change the code to enter grades till user enter -9 (sentinel value)
