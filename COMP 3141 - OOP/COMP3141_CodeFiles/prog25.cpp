#include "distance.cpp"
#include <iostream>
#include <conio.h>
using namespace std;

int main()
{
  //  clrscr();
  Distance ob1;
  Distance ob2;
  Distance ob3; // Invoked constructor1

  cout << "\n=====Enter Data for OBJECT1=====\n";
  ob1.getdata();
  cout << "\n\n=====Enter Data for OBJECT2=====\n";
  ob2.getdata();
  int opt = 0;
  int choice = 1, data;

  while (choice != 0)
  {
    opt = 0;

    cout << "\nChose your choice\n";
    cout << "1)  Addition            ( + )\n";
    cout << "2)  Subtraction         ( - )\n";
    cout << "3)  Multiplication      ( * )\n";
    cout << "4)  Comparision         ( == )\n";
    cout << "5)  Comparision         ( < )\n";
    cout << "6)  Comparision         ( > )\n";
    cout << "0)  Quit                \n";
    cout << "Enter your choice:-";
    cin >> choice;
    cout << endl
         << endl;
    switch (choice)
    {
    case 1:
      ob3 = ob1 + ob2;
      opt = 1;
      break;

    case 2:
      ob3 = ob1 - ob2;
      opt = 1;
      break;

    case 3:
      cout << "\nEnter integer to be multiplied:-";
      cin >> data;
      ob3 = data * ob1;
      opt = 1;
      break;

    case 4:
      if (ob1 == ob2)
        cout << "\nBoth Objects are equal or same value\n";
      else
        cout << "\nThey are Unequal\n";
      getch();
      break;

    case 5:
      if (ob1 < ob2)
        cout << "\nObject1 is Less than Object2\n";
      else
        cout << "\nObject2 is Less than Object1\n";
      getch();
      break;

    case 6:
      if (ob1 > ob2)
        cout << "\nObject1 is Greater than Object2\n";
      else
        cout << "\nObject2 is Greater than Object1\n";
      getch();
      break;

    case 0:
      cout << "\n\nHave a nice day....\n";
      getch();
      break;

    default:
      cout << "Invalid option ";
    }

    if (opt == 1)
    {
      cout << "\n\nResult in OBJECT3 as under\n";
      ob3.display();
      getch();
    }
  }

  return 0;
}
