#include <iostream>
using namespace std;

class B
{
public:
    virtual void display() = 0; /* Pure virtual function */
    
    // virtual void display() /* Virtual function */
    // {
    //     cout << "Content of base class.\n";
    // }
};

class D1 : public B
{
public:
    void display()
    {
        cout << "Content of first derived class.\n";
    }
};

class D2 : public B
{
public:
    void display()
    {
        cout << "Content of second derived class.\n";
    }
};

int main()
{
    B b;
    D1 d1;
    D2 d2;
    b.display();  // base class function
    d1.display(); /* calls display() of class derived D1 */
    d2.display(); /* calls display() of class derived D2 */
                  //    d1::B.display();
    return 0;
}

// TODO : remove the body of the display function of Class B
// Try to run the program now
// what do you see?
