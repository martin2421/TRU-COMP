#include <iostream>
#include <conio.h>
using std::cin;  // program uses cin
using std::cout; // program uses cout
using std::endl; // program uses endl
using std::string;
// This program calculates the percentage and based on it defines the grade
// Use TRU Graing policy for calculations

int main()
{

     // declare variable type float and char
     int id, m1, m2, m3, m4, m5;
     float perc;
     string name, grade;

     char ch;
     while (ch != 'x')
     {
          // Input the choice.
          cout << endl
               << endl;
          cout << "Enter student ID : ";
          cin >> id;
          cout << "Enter student Name :";
          cin >> name;
          cout << "Enter Five marks [each out of 100] : ";
          cin >> m1 >> m2 >> m3 >> m4 >> m5;
          perc = (m1 + m2 + m3 + m4 + m5) / 5;
          if (perc >= 90)
               grade = "A+";
          else if (perc >= 85 && perc < 90)
               grade = "A";
          else if (perc >= 80 && perc < 85)
               grade = "A-";
          else if (perc >= 77 && perc < 80)
               grade = "B+";
          else if (perc >= 73 && perc < 77)
               grade = "B";
          else if (perc >= 70 && perc < 73)
               grade = "B-";
          else if (perc >= 65 && perc < 70)
               grade = "C+";
          else if (perc >= 60 && perc < 65)
               grade = "C";
          else if (perc >= 55 && perc < 60)
               grade = "C-";
          else if (perc >= 50 && perc < 55)
               grade = "D";
          else
               grade = "F";
          cout << "Grade is : " << grade << endl
               << endl;
          cout << "Enter 'Y' to continue and 'x' to exit" << endl;
          cin >> ch;

          if (!(ch == 'y' || ch == 'x'))
          {
               cout << "Syntax Error";
               ch == 'y';
               continue;
          }

          if (ch == 'x')
               break;
     }

     system("pause");
}
