#include <iostream>
#include <conio.h>
#include <stdexcept>
#include "Date.h"

using namespace std;

// constructor
Date::Date()
    : day(0), month(0), year(0)
{
    cout << "This is the definition file for the methods of the Time Class!";
}

Date::Date(int d, int m, int y)
{
    if ((d >= 1 && d <= monthDays[m - 1]) && (m >= 1 && m <= 12) && (y > 0))
    {
        day = d;
        month = m;
        year = y;
    }
    else
    {
        cout << "Incorrect value for day, month, or year were given";
    }
}

void Date::getData()
{
    std::cout << "Enter your date of birth DD MM YYYY: ";
    std::cin >> day >> month >> year;
    Date(day, month, year);
}

void Date::getCurrentDate()
{
    std::cout << "Enter the current date using day, month, and year respectively: \n";
    std::cin >> day >> month >> year;
    Date(day, month, year);
}

void Date::getDate()
{
std:
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
        totalDays += monthDays[i];

    // adds the number of days in the current month
    totalDays += day;

    // adds number of extra days due to leap years
    totalDays += getLeapYearDays();

    return totalDays;
}
