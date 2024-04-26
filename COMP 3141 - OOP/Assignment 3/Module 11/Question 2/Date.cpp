#include <iostream>
#include <conio.h>
#include <stdexcept>
#include "Date.h"

using namespace std;

Date::Date() // constructor 1
    : day(0), month(0), year(0)
{
    cout << "This is the definition file for the methods of the Date Class.";
}

Date::Date(int d, int m, int y) // constructor 2
{
    if ((d >= 1 && d <= monthDays[m - 1]) && (m >= 1 && m <= 12) && (y > 0))
    {
        day = d;
        month = m;
        year = y;
    }
    else
    {
        cout << "Incorrect value for day, month, or year was given";
    }
}

void Date::getData()
{
    cout << "Enter your date of birth using DD, MM, YYYY: \n";
    cin >> day >> month >> year;
    Date(day, month, year);
}

void Date::getCurrentDate()
{
    cout << "Enter the current date using DD, MM, and YYYY: \n";
    cin >> day >> month >> year;
    Date(day, month, year);
}

void Date::getDate()
{
    cout << "Date: " << day << "/" << month << "/" << year << endl;
}

int Date::getLeapYearDays()
{
    return (year / 4 - year / 100 + year / 400);
}

int Date::getTotalDays()
{
    int totalDays = 0;

    // days in completed years
    totalDays += year * 365;

    // adds the number of days in each month that has finished
    for (int i = 0; i < month - 1; i++)
    {
        totalDays += monthDays[i];
    }

    // adds the number of days in the current month
    totalDays += day;

    // adds number of extra days due to leap years
    totalDays += getLeapYearDays();

    return (totalDays);
}
