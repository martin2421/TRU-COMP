#include <iostream>
#include <ctime>
using namespace std;

class Date
{
private:
    int day;
    int month;
    int year;

public:
    // constructor
    Date(int d, int m, int y) : day(d), month(m), year(y) {}

    // overload the - operator to find the difference
    // between two date objects
    int operator-(const Date &other) const
    {
        // convert both dates to days
        int thisDays = day + 31 * month + 365 * year;
        int otherDays = other.day + 31 * other.month + 365 * other.year;

        // return the difference in days
        return thisDays - otherDays;
    }
};

// function to get the current date
Date getCurrentDate()
{
    // get the current time
    time_t now = time(0);

    // convert now to tm struct for local timezone
    tm *ltm = localtime(&now);

    // create a Date object for the current date
    Date currentDate(ltm->tm_mday, ltm->tm_mon + 1, ltm->tm_year + 1900);

    return currentDate;
}

// function to calculate age in days
int calculateAgeInDays(const Date &birthdate)
{
    // get the current date
    Date currentDate = getCurrentDate();

    // calculate the difference between current date and birthdate
    int ageInDays = currentDate - birthdate;

    return ageInDays;
}
