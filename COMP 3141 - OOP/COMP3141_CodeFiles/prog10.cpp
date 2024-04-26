// This is a driver class that defines the object of the grades
//@ This will generate grades of n students till -9 is entered
#include <iostream>
#include <iomanip>     // parameterized stream manipulators
#include "GradeBook.h" // include definition of class GradeBook
#include <string>

using namespace std;

void GradeBook::determineClassAverage() const
{
   // initialization phase
   int total = 0;                 // sum of grades entered by user
   unsigned int gradeCounter = 0; // number of grades entered

   // processing phase
   // prompt for input and read grade from user
   cout << "Enter grade or -9 to quit: ";
   int grade = 0; // grade value
   cin >> grade;  // input grade or sentinel value

   // loop until sentinel value read from user
   //@ make a statement to run the loop till sentinel value is entered
   while(grade != -9) {
      total = total + grade;           // add grade to total
      gradeCounter = gradeCounter + 1; // increment counter

      // prompt for input and read next grade from user
      cout << "Enter grade or -9 to quit: ";
      cin >> grade; // input grade or sentinel value
   }                // end while

   // termination phase
   if (gradeCounter != 0) // if user entered at least one grade...
   {
      // calculate average of all grades entered
      double average = static_cast<double>(total) / gradeCounter;

      // display 7total and average (with two digits of precision)
      cout << "\nTotal of all " << gradeCounter << " grades entered is "
           << total << endl;
      cout << setprecision(2) << fixed;
      cout << "Class average is " << average << endl;
   }    // end if
   else // no grades were entered, so output appropriate message
      cout << "No grades were entered" << endl;
} // end function determineClassAverage

int main()
{
   // create two GradeBook objects
   GradeBook gradeBook1("CS101 Introduction to C++ Programming\n"); // instantiate the object

   // display initial value of courseName for each GradeBook
   cout << "gradeBook1 created for course: " << gradeBook1.getCourseName();
   // run the function to get the class average here
   gradeBook1.determineClassAverage();

   system("pause");
} // end main
