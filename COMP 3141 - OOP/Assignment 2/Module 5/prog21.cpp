#include <iostream>
#include <algorithm>
#include <array>
#include <iterator>
using namespace std;

int getLength(char *);
void toUpper(char *);
void toLower(char *);
void toProper(char *);
void toPattern(char *);

int main()
{
    char name[80]; // array of characters for name

    cout << "Enter a name: "; // prompt for name
    cin >> name;

    int r = getLength(name); // get the length of the string
    cout << "The length of string is: " << r << endl;

    toUpper(name); // convert the string to upper case
    cout << "The string in Capital Letters is: " << name << endl;

    toLower(name); // convert the string to lower case
    cout << "The string in Lower Letters is: " << name << endl;

    toProper(name); // convert the string to proper case
    cout << "The string in Proper Case is: " << name << endl;

    toPattern(name); // convert the string to pattern
    cout << "The string in Pattern is: " << name << endl;

    return 0;
    // Arbitrary element processing on the container.
}

// function to convert the string to upper case
void toUpper(char *p)
{
    while (*p) // check for end of string
    {
        // if the character is in lower case, convert to upper case
        *p = (*p >= 'a' && *p <= 'z') ? *p = *p - 32 : *p;
        p++; // move to next character
    }
}

// function to get the length of the string
int getLength(char *p)
{
    int ctr = 0; // counter to count the characters
    while (*p)   // check for end of string
    {
        cout << *p << " ";
        ctr++; // increment the counter
        p++;   // move to next character
    }
    cout << endl;
    return ctr;
}

// function to convert the string to lower case
void toLower(char *p)
{
    while (*p)
    {
        // same as toUpper, but opposite the formula
        *p = (*p >= 'A' && *p <= 'Z') ? *p = *p + 32 : *p;
        p++;
    }
}

// function to convert the string to proper case
// (first uppercase, rest lower)
void toProper(char *p)
{
    bool isFirst = true; // flag to check if the character is first
    while (*p)           // check for end of string
    {
        // if the character is first and in lower case, convert to upper case
        if (isFirst && *p >= 'a' && *p <= 'z')
        {
            *p = *p - 32;
            isFirst = false;
        }
        // if the character is not first and in upper case, convert to lower case
        else if (!isFirst && *p >= 'A' && *p <= 'Z')
        {
            *p = *p + 32;
        }

        // if the character is space, set the flag to true
        if (*p == ' ')
            isFirst = true;

        p++;
    }
}

// function to convert the string to pattern
// (one uppercase, then one lowercase, and so on)
void toPattern(char *p)
{
    bool isUpperCase = true; // flag to check if the character is upper case
    while (*p)
    {
        // toggle the case of the character
        *p = isUpperCase ? toupper(*p) : tolower(*p);

        // toggle the flag for the next character
        isUpperCase = !isUpperCase;
        p++;
    }
}
