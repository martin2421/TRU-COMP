// This is the driver program to take class definition from an external file
#include <iostream>
#include "GradeBook.h" // include definition of class GradeBook
using namespace std;

// function main begins program execution
int main()
{
   // create two GradeBook objects
   GradeBook gradeBook1("CS101 Introduction to C++ Programming"); // instantiate the object
   GradeBook gradeBook2("CS102 Data Structures in C++");

   // display initial value of courseName for each GradeBook
   cout << "gradeBook1 created for course: " << gradeBook1.getCourseName()
        << "\ngradeBook2 created for course: " << gradeBook2.getCourseName()
        << endl;
   system("pause");
} // end main
