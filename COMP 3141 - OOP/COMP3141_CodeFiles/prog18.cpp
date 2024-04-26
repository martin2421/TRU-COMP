#include <iostream>
using namespace std;
void display(int marks[5]);

int main() {
    int marks[5] = {88, 76, 90, 61, 69};
    display(marks);
    return 0;
}

void display(int m[5]) {
    cout<<"Displaying marks: "<<endl;
    for (int i = 0; i <5; ++i) {
        cout<<"Student "<<i+1<<": "<<m[i]<<endl;
    }
}


// Make a character array to store the names of ten students
// make another int marks[10][5] array to store three marks for ten students
// extend the display function to display the name of every student, followed by 5 marks, total , percentage and Grade obtained by the students.
// display() should be called ten time from the loop in the main body of the program
