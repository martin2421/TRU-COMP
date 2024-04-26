#include <array>
#include <iostream>
#include <iomanip>
using namespace std;

class GradeBook
{
public:
    static const int students = 10;
    static const int tests = 5;

    // constructor initializes courseName and grades array
    GradeBook(const string &name, array<array<int, tests>, students> &gradesArray)
        : courseName(name), grades(gradesArray) {}

    // set course name
    void setCourseName(const string &name)
    {
        courseName = name;
    }

    // get course name
    const string getCourseName()
    {
        return courseName;
    }

    // set student names
    void setNames(string studentNames[10])
    {
        for (int i = 0; i < 10; i++)
            names[i] = studentNames[i];
    }

    // display title
    void displayMessage()
    {
        cout << "\nGradeBook for " << getCourseName() << endl << endl;
    }

    // process 10 students grades
    void processGrades()
    {
        outputGrades();
        cout << "\nLowest grade is: " << getMinimum()
             << "\nHighest grade is: " << getMaximum() << endl;
    }

    // find minimum grade in the list of grades
    int getMinimum()
    {
        int min = 100;

        for (auto const &student : grades)
            for (auto const &grade : student)
                if (grade < min)
                    min = grade;

        return min;
    }

    // find maximum grade in the list of grades
    int getMaximum()
    {
        int max = 0;

        for (auto const &student : grades)
            for (auto const &grade : student)
                if (grade > max)
                    max = grade;

        return max;
    }

    // determine average grade for particular set of grades
    double getAverage(const array<int, tests> &setOfGrades)
    {
        int total = 0;

        for (int grade : setOfGrades)
            total += grade;

        return static_cast<double>(total) / setOfGrades.size();
    }

    // output table of grades
    void outputGrades()
    {
        cout << "            ";

        // output column heads
        for (int test = 0; test < tests; test++)
            cout << "Test " << test + 1 << setw(2) << " ";

        cout << "Average" << endl;

        // output grades in table format
        for (int student = 0; student < grades.size(); student++)
        {
            cout << setw(8) << names[student];

            // grades
            for (int test = 0; test < grades[student].size(); test++)
                cout << setw(8) << grades[student][test];

            double average = getAverage(grades[student]);

            cout << setw(9) << setprecision(2) << fixed << average << endl;
        }
    }

private:
    string courseName;
    array<array<int, tests>, students> grades;
    string names[10];
};
