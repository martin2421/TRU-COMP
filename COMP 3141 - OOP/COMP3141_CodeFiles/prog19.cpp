#include <iostream>
#include <cstring>
using namespace std;

int main()
{
    char data1[10], data2[10], data3[4];

    cout << "Enter first name :";
    cin >> data1;
    cout << "Enter last name :";
    cin >> data2;

    if (strcmp(data1, data2) == 0)
        cout << "You entered the same words" << endl;
    else
        cout << "You entered different words" << endl;

    strcat(data1, data2);
    cout << "Your full name is " << data1 << endl;

    // Test other string functions
    strcpy(data3, "abc");
    cout << "Copied string is: " << data3 << endl;

    cout << "Length of data1: " << strlen(data1) << endl;

    cout << "Comparing data1 and data2: ";

    int result = strcmp(data1, data2);
    if (result < 0)
        cout << "data1 comes before data2" << endl;
    else if (result > 0)
        cout << "data1 comes after data2" << endl;
    else
        cout << "data1 and data2 are equal" << endl;

    return 0;
}
