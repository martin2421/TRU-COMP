#include <iostream>
#include <iomanip>
#include <string>
#include <algorithm>
using namespace std;

void display(string name, int mark1, int mark2, int mark3, int mark4, int mark5);

int main()
{
    // array of names
    string names[10] = {"Jack", "Alice", "Charlie", "Bob", "David", "Grace", 
    "Emma", "Frank", "Henry", "Ivy"};

    sort(names, names + 10); // sort arrays alphabetically

    // array of marks, 5 marks for 10 students
    int marks[10][5] = {
        {88, 76, 90, 65, 78},
        {78, 85, 80, 91, 70},
        {92, 88, 94, 90, 94},
        {75, 68, 72, 81, 79},
        {82, 91, 89, 82, 83},
        {70, 74, 68, 77, 100},
        {85, 89, 92, 88, 61},
        {77, 80, 75, 88, 74},
        {90, 85, 88, 79, 79},
        {83, 79, 81, 100, 83}};

    // display marks for each student
    for (int i = 0; i < 10; i++)
    {
        // call the display function 10 times
        display(names[i], marks[i][0], marks[i][1], marks[i][2], marks[i][3], marks[i][4]);
    }

    return 0;
}

// function to display the name, marks, total, percentage and grade of a student
void display(string name, int mark1, int mark2, int mark3, int mark4, int mark5)
{
    // display the name
    cout << name << endl;

    int total = mark1 + mark2 + mark3 + mark4 + mark5; // add all marks
    double percentage = total / 5.0; // calculate percentage
    char grade; // variable to store grade

    // determine grade based on percentage
    if (percentage >= 90)
        grade = 'A';
    else if (percentage >= 80 && percentage < 90)
        grade = 'B';
    else if (percentage >= 70 && percentage < 80)
        grade = 'C';
    else if (percentage >= 60 && percentage < 70)
        grade = 'D';
    else
        grade = 'F';

    // display marks, total, percentage and grade
    cout << "Total: " << total << endl;
    cout << "Percentage: " << percentage << "%\t\t" << endl;
    cout << "Grade: " << grade << endl;
    cout << endl;
}
