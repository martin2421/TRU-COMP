#include <iostream>
#include<string.h>
using namespace std;


void swapR(int *, int *);
void swapV( int, int );

int main() {

int a= 10, b=20;
cout << "a = " << a << " and b before function call are:" << b << endl;
swapV(a, b);
cout << "a = " << a << " and b after function call swapV are:" << b << endl;

cout << "a = " << a << " and b before function call are:" << b << endl;
swapV(a, b);
cout << "a = " << a << " and b after function call swapR are:" << b << endl;

    return 0;
}

void swapV(int a, int b)
{

    int c;
    c=a;
    a=b;
    b= c;
}
void swapR(int *a, int *b)
{
    int c;
    c= *a;
    *a= *b;
    *b = c;
}
