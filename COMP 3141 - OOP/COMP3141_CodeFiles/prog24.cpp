#include <iostream>
using namespace std;

// Count class definition
class Count
{
   friend void setX(Count &, int); // friend declaration
   
private:
   int x; 

public:
   
   Count()
       : x(0) // initialize x to 0
   {
      // empty body
   } // end constructor Count

   // output x
   void print() const
   {
      cout << x << endl;
   } // end function print

}; // end class Count

// function setX can modify private data of Count
// because setX is declared as a friend of Count (line 9)
void setX(Count &c, int val)
{
   c.x = val; // allowed because setX is a friend of Count
} // end function setX

int main()
{
   Count counter; // create Count object

   cout << "counter.x after instantiation: ";
   counter.print();

   setX(counter, 8); // set x using a friend function
   cout << "counter.x after call to setX friend function: ";
   counter.print();
} // end main
