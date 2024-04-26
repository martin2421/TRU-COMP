#include <iostream>
#include <algorithm>
#include "GradeBook.h"

int main()
{
    // array of names
    string names[10] = {"Martin", "Fernando", "Andy", "Luis", "Carlos", "Angelo", "Laura", "Casey", "Ian", "Gus"};

    // sort the names in alphabetical order
    sort(names, names + 10);

    // 2D array to be used in the gradebook class
    array<array<int, GradeBook::tests>, GradeBook::students> grades = {
        {{100, 90, 80, 90, 95},
         {77, 77, 77, 77, 77},
         {45, 76, 54, 61, 69},
         {98, 97, 85, 61, 76},
         {88, 72, 90, 61, 99},
         {48, 75, 99, 80, 65},
         {88, 92, 90, 61, 69},
         {82, 73, 65, 78, 91},
         {82, 73, 96, 64, 67},
         {68, 66, 70, 67, 65}}};

    // create a gradebook object using the names and grades
    GradeBook myGradeBook("Object Oriented Design", grades);
    myGradeBook.setNames(names);

    // display the results
    myGradeBook.displayMessage();
    myGradeBook.processGrades();

    return 0;
}
