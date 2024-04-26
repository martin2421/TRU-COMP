#include <iostream>
#include <cmath> // For math functions like pow and sqrt
using namespace std;

// function to calculate the area of a circle
void calculateCircle()
{
     float radius, area;
     cout << "Enter radius: ";
     cin >> radius;
     area = 3.14 * radius * radius;
     cout << "Area of circle: " << area << endl;
}

// function to calculate the area of a square
void calculateSquare()
{
     float side, area;
     cout << "Enter the side: ";
     cin >> side;
     area = side * side;
     cout << "Area of square: " << area << endl;
}

// function to calculate the area of a rectangle
void calculateRectangle()
{
     float length, width, area;
     cout << "Enter the length: ";
     cin >> length;
     cout << "Enter the width: ";
     cin >> width;
     area = length * width;
     cout << "Area of rectangle: " << area << endl;
}

// function to calculate the area of a triangle
void calculateTriangle()
{
     float height, base, area;
     cout << "Enter the height: ";
     cin >> height;
     cout << "Enter the base: ";
     cin >> base;
     area = 0.5 * height * base;
     cout << "Area of triangle: " << area << endl;
}

// function to calculate the area of a rhombus
void calculateRhombus()
{
     float diagonal1, diagonal2, area;
     cout << "Enter the first diagonal: ";
     cin >> diagonal1;
     cout << "Enter the second diagonal: ";
     cin >> diagonal2;
     area = 0.5 * diagonal1 * diagonal2;
     cout << "Area of rhombus: " << area << endl;
}

int main()
{
     char option;
     while (option != 'x')
     {

          // display the menu
          cout << "Enter c for circle." << endl;
          cout << "Enter s for square." << endl;
          cout << "Enter r for rectangle." << endl;
          cout << "Enter t for triangle." << endl;
          cout << "Enter h for rhombus." << endl;
          cout << "Enter x to exit." << endl;

          // input option
          cout << "\nEnter Option: ";
          cin >> option;

          switch (option)
          {
          case 'c':
               calculateCircle();
               cout << endl;
               break;
          case 's':
               calculateSquare();
               cout << endl;
               break;
          case 'r':
               calculateRectangle();
               cout << endl;
               break;
          case 't':
               calculateTriangle();
               cout << endl;
               break;
          case 'h':
               calculateRhombus();
               cout << endl;
               break;
          case 'x':
               break;
          default:
               cout << "Wrong input!" << endl;
          }
     }
     return 0;
}
