#include <iostream>
#include <string.h>
using namespace std;

void sort(int marks[10]);
void display(int marks[10]);

int main()
{
    int marks[10];

    int i;
    for (i = 0; i < 10; i++)
    {
        cout << "Enter marks : ";
        cin >> marks[i];
    }
    sort(marks);

    display(marks);

    return 0;
}

void sort(int marks[10])
{
    int insert;
    for (int i = 1; i < 10; i++)
    {
        insert = marks[i];
        int moveItem = i;
        while ((moveItem > 0) && (marks[moveItem - 1] > insert))
        {
            marks[moveItem] = marks[moveItem - 1];
            moveItem--;
        }
        marks[moveItem] = insert;
    }
    
}
void display(int marks[10])
{

    cout << "Displaying marks in Ascending Order " << endl;

    for (int i = 0; i < 10; ++i)
    {
        cout << marks[i] << "   " << endl;
    }
}
