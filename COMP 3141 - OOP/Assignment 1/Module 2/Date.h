#include <iostream>
#include <iomanip> // For std::setfill and std::setw

using namespace std;

class Date
{
private:
     int month;
     int day;
     int year;

public:
     // Constructor that initializes the date
     Date(int m, int d, int y) {
          month = m;
          day = d;
          year = y;
     }

     // Set methods
     void setMonth(int m)
     {
          month = m;
     }

     void setDay(int d)
     {
          day = d;
     }

     void setYear(int y)
     {
          year = y;
     }

     // Get methods
     int getMonth() const
     {
          return month;
     }

     int getDay() const
     {
          return day;
     }

     int getYear() const
     {
          return year;
     }

     // display date in MM/DD/YYYY format
     void displayDate() const
     {
          cout << month << "/" << day << "/" << year << endl;
     }
};
