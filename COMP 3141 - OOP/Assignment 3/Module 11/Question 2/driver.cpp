#include <iostream>
#include <iomanip>
#include <vector>
#include <typeinfo>
#include "Date.cpp"
using namespace std;

// exception class
class Birthday : public exception
{
public:
    const char *what()
    {
        return "You have a birthday this month, Happy Belated Birthday!";
    }
};

int main()
{
    // date object to compare against
    Date currentDate = Date(18, 4, 2024);

    // initialize the variables
    int day, month, year;

    // Driver Part of the class
    cout << "Enter your date of birthday using DD, MM, YYYY: \n";
    cin >> day >> month >> year;

    // try catch for the date entered
    try
    {
        Date birthDay = Date(day, month, year);

        // throw exception if the current month and birth month are the same
        if (currentDate.month == birthDay.month)
        {
            Birthday B;
            throw B;
        }
        else
        {
            cout << "You do not have a birthday this month." << endl;
        }
    }
    catch (Birthday B)
    {
        // call function from exception class
        cout << B.what() << endl;
    }
}
