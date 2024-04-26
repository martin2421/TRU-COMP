#include <iostream>
#include "Date.cpp"
using namespace std;

int main()
{
    // input user's birthdate
    int birthDay, birthMonth, birthYear;

    cout << "Enter your birthdate (DD MM YYYY): ";
    cin >> birthDay >> birthMonth >> birthYear; // user input

    // create a Date object for the birthdate
    Date birthdate(birthDay, birthMonth, birthYear);

    // calculate age in days
    int age = calculateAgeInDays(birthdate);

    // output the age in days
    cout << "You are " << age << " days old." << endl;

    return 0;
}
