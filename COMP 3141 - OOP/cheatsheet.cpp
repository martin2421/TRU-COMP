/*
     Abstract class - defines interface (function prototypes) but not implementation. 
     Can't be instantiated. Must be inherited by a child class. The header file 
     should have #ifndef, #define, ..., #endif to prevent multiple inclusions. (.h)

     Concrete class - has implementation of all functions. Can be instantiated. (.cpp)
*/

#include <iostream>  // used for input/output stream
#include <iomanip>   // used for setprecision, setw, etc.
#include <cmath>     // used for math functions (e.g., sqrt())
#include <fstream>   // used for file handling
#include <string>    // used for string functions
#include <array>     // used for array
#include <vector>    // used for vector
#include <algorithm> // used for for_each, sort, etc.
#include "COMP3141_CodeFiles/Time.cpp"  // include external user file

#define PI 3.14 // define a global constant, no type needed

using namespace std; // used for standard namespace
// using std::cout; // use only cout, cin, endl, string, vector, etc.

// :: - binary scope resolution operator, 
// used to define the member of a class externally

// function overload - same name, different parameters
int add(int a, int b) { return a + b; }
float add(float a, float b) { return a + b; }

// global variable - use ::GLOBAL to access
// in case there's another variable with the same name in local scope
int GLOBAL = 10;

// variable declaration
int x = 10;
char c = 'A';
double d = 3.14;
float f = 3.14f;
bool b = true;

// % - modulus operator can only be used with integers

// enum - user defined data type
enum DayOfWeek
{
     Monday,
     Tuesday,
     Wednesday
};

// typedef - alias for data types
typedef int marks;
marks m = 95;

// ways of casting
int y = (int)d;              // cast double d to int
int z = static_cast<int>(d); // cast double d to int
int a = 10;
double b = a;   // implicit conversion
int c = (int)b; // explicit conversion

/*
     types of loops:
     * for loop
     * while loop
     * do-while loop
     * range-based for loop (C++11)
*/ 


// null types
string *nullpointer = nullptr; // null pointer of type string*
string nullobj = NULL;         // null object of type string

// struct - user defined data type, everything is public, cannot be used in inheritance
struct Point
{
     int x, y;    // declare members x and y
     void print() // declare member function print
     {
          cout << x << ", " << y;
     }
};

// how to create a class
class Human
{
protected: // access specifier
     int ssn; // protected member - can be accessed by child class

private:
     int age; // private member - can't be accessed by child class
     string name;

public:
     // default constructor
     Human() : age(0), name("noname") {} 
     // or Human() { age = 0; name = "noname"; }
     // or Human() = default

     /*
          explicit makes it so that the constructor must be called explicitly, 
          not implicitly, for example, Human h = 10; will not work, 
          but Human h(10) will, and so will Human h = Human(10)
     */
     explicit Human(int age) // parameterized constructor
     {
          // this is a pointer to the object that called the function
          this->age = age; // not this.age
          name = "noname";
     }

     Human(int age, string name) // parameterized constructor
     {
          this->age = age; // not this.age
          this->name = name;
     }

     void print() { cout << "Human Print()"; } // normal function

     // virtual function - can be overridden by child class
     virtual void display() { cout << age << " " << name; }
     // pure virtual function - child MUST implement
     virtual void display2() = 0;
     // function called using 'Human::display3()' without creating an object
     static void display3() { cout << "display3() called"; }

     /*
          destructor - called when object is destroyed, 
          no parameter, no overloading, automatically called
          when object goes out of scope, or delete is called
          objects are destroyed in reverse order of creation
     */
     ~Human() {} 

}; // remember the semicolon

// : shows inheritance
class Student : public Human
{
public:
     // call parent constructor
     Student() : Human() {}
     // call both constructors                                  
     Student(int a, string n) : Human(a, n) {} 
     // normal function              
     void create() { cout << "Student created"; } 
     // override function
     void print() { cout << "Student Print()"; }      
     // use override for overriding virtual functions
     void display() override { cout << "display() called"; } 
     void display2() override { cout << "display2() called"; }
};

// operator overloading
// can't create new operators, only overload existing ones
// can't override ?:, ::, ., .*, sizeof, typeid
class Vector2
{
public:
     int x, y;

     // default constructor
     Vector2(int a, int b) : x(a), y(b) {}

     // operator+ is a key word,  allows Vector2 + Vector2,
     // does not imply that += will work, must overload that separately
     Vector2 operator+(Vector2 &v)
     {
          return Vector2(x + v.x, y + v.y);
     }

     // overload insertion operator
     // allows cout << Vector2
     friend ostream& operator<<(ostream& stream, Vector2 &v)
     {
          stream << v.x << ", " << v.y;
          return stream;
     }

     // friend function - can access private members of a class
     friend void display(Vector2 &v)
     {
          cout << v.x << " " << v.y;
     } // need to declare as a friend so we can access private members
};

/*
     Baseptr to baseObj - calls made from baseptr will call base class functions
     Derivedptr to derivedObj - calls made from derivedptr will call derived class functions
     Baseptr to derivedObj - calls made from baseptr will call base class functions 
                         (use of virtual functions - dynamic binding)
     Derivedptr to baseObj - calls made from derivedptr will cause a compilation error
*/

// base class
class Base
{
public:
     virtual void vvfunc() {}
};

// derived class
class Derived : public Base
{
};

int main()
{
     // dynamic testing - check if a class is derived from another
     Derived *d = new Derived;
     Base *b = d;               // upcasting
     cout << typeid(b).name();  // prints "class Base *"
     cout << typeid(*b).name(); // prints "class Derived"
     cout << typeid(d).name();  // prints "class Derived *"
     cout << typeid(*d).name(); // prints "class Derived"

     // ways of making a string
     string s1 = "Hello";
     string s2("World");
     char *cstr = "Hello";
     const char *s3 = "Hello"; // always use 'const char*'
     char cwords[] = "C++";
     wchar_t unicode[] = L"Unicode";

     // length of a string (char *string)
     int strLength(char *p)
     {
          int count = 0;
          for (count = 0; *p != '\0'; p++)
               count++;
          return count;
     }

     // output to console
     cout << "Hello World" << endl; // << is the insertion operator
     cin >> x;                      // >> is the extraction operator
     cout.put('A').put('\n');       // used to output a single char
     cout << dec << x;              // prints x in decimal (def)
     cout << oct << x;              // prints x in octal
     cout << hex << x;              // prints x in hexadecimal

     // left align, width 10, fill with % - prints "10000%%%%%"
     cout << left << setw(10) << setfill('%') << 10000;

     // input from console
     cin >> x;                 // does not accept spaces or tabs
     getline(cin, s1);         // input a line and store it in s1, accepts spaces
     cin.getline(cwords, 100); // same as above, but for char[]

     // use get to read each character (EOF: end-of-file)
     while ((c = cin.get()) != EOF)
          cout << c;

     // string manipulation
     s1.empty();                // check if string is empty
     s1.size();                 // get the size of string, returns size_t
     s1.length();               // same as above
     sizeof(s1);                // get the size of string (same as above), 
                                // includes null character '\0' (size + 1)
     s1[0];                     // access char element at index 0
     s1 + "Hello";              // concatenate strings
     s1 += "Hello";             // same as above
     strcat(cwords, "Hello");   // same as above, returns new string, 
                                // must pass char array by reference
     s1 == "Hello";             // compare strings
     s1.compare("Hello");       // compare strings
     strcmp(cwords, "Hello");   // same as above, returns boolean
     s1.find("Hello");          // find substring in string
     s1.substr(1, 5);           // get substring from index 1 to 5
     s1.insert(1, "Hello");     // insert substring at index 1
     s1.append("Hello");        // append substring at end of string
     s1.erase(1, 5);            // erase substring from index 1 to 5
     s1.replace(1, 5, "Hello"); // replace subst from index 1 to 5
     s1.clear();                // clear string
     // with strings, you can use == and != to compare, .size(), = to assign, 
     // and [] to access elements, strings end with the character '\0' (null)

     // ways of making an array
     int arr[] = {1, 2, 3, 4, 5}; // initializer list
     int arr[5] = {1, 2, 3, 4, 5}; // array of size 5
     array<int, 5> arr = {1, 2, 3, 4, 5}; // array from STL library

     // 2D array - 2 rows, 3 columns
     int arr2[2][3] = {{1, 2, 3}, {4, 5, 6}};
     sizeof(arr2); // returns size of array in bytes, not number of elements

     // works only for arrays, not pointers
     // get the size of a 1D array
     int arr_size = sizeof(arr) / sizeof(arr[0]);
     // get the size of a 2D array
     int arr2_size = sizeof(arr2) / sizeof(arr2[0]) / sizeof(arr2[0][0]);

     // accessing elements of an array
     arr[0]; // access element at index 0
     // accessing elements of a 2D array
     arr2[0][1]; // access element at index 0, 1 (row 0, column 1)

     // ways of making a vector (dynamic array)
     vector<int> vect;        // empty vector, has size 0
     vector<int> vect(5);     // vector of size 5 with 0s
     vector<int> vect(5, 1);  // vector of size 5 with 1s
     vector<int> vect2(vect); // copy vector to another vector
     vect.at(0);              // access element at index 0
     vect.push_back(1);       // add element at end of vector
     vect.pop_back();         // remove element at end of vector
     vect.size();             // get size of vector
     // with vectors, you can use == and != to compare, .size(), = to assign, 
     // and [] to access elements

     /*
     Pointers: A pointer is a variable that holds the memory address of 
     another variable. A pointer needs to be dereferenced with the * operator to 
     access the memory location it points to.

     References: A reference variable is an alias, that is, another name for an 
     already existing variable. A reference, like a pointer, is also implemented 
     by storing the address of an object.
     */

     // ways of making a pointer
     void* void_ptr; // generic pointer (can point to any data type)
     int *ptr = &x;  // pointer to x
     ptr = NULL;     // null pointer
     ptr = nullptr;  // null pointer
     *ptr;           // dereference pointer (get value)
     ptr++;          // increment pointer (go to next memory location)
     ptr--;          // decrement pointer (previous memory location)

     // pointers to an array
     int arr3[5];
     int *arr3ptr = arr3; // pointer to array, euivalent to &arr3[0]
     *(arr3ptr + 3);      // access element at index 3, quivalent to arr3[3]
     // &arr3[3] is the same as (arr3ptr + 3)
     // arr3ptr[2] is the same as arr3[2]

     // array of pointers
     int *c[5]; // array of 5 pointers
     // each element is of type "pointer to char constant data"
     const char *const suit[4] = {"Hearts", "Diamonds", "Clubs", "Spades"};

     // const on the left of * - pointer to constant data (can't change data)
     const int *ptr2 = &x;
     // const on the right of * - constant pointer (can't change pointer)
     int *const ptr3 = &x;

     // swapping elements by value
     void swapByValue(int a, int b)
     {
          int c = a;
          a = b;
          b = c;
     }
     // swapping elements by reference
     void swapByReference(int *a, int *b)
     {
          int c = *a;
          *a = *b;
          *b = c;
     }

     // pass by value - pass a copy of the variable, call it using passbyvalue(x)
     void passbyvalue(int x) { x = 10; }
     // pass by reference - pass the actual variable, call it using passbyref(&x)
     void passbyref(int &x) { x = 10; }
     // pass by pointer - pass the address of the variable, call it as above
     void passbypointer(int *x) { *x = 10; }
     // pass by const reference - same as above but can't change it
     void passbyconstref(const int &x) { cout << x; }

     // pass an array to a function
     void passarray(int arr[], int sizeOfArray) { cout << arr[0]; }
     // pass an array as a pointer to a function
     void passarrayaspointer(int *arr, int sizeOfArray) { cout << arr[0]; }
     // pass a vector to a function
     void passvector(vector<int> & vect) { cout << vect[0]; }

     /*
          Ways of passing a pointer
          * pass a nonconstant pointer to noncostant data
          * pass a nonconstant pointer to constant data
          * pass a constant pointer to nonconstant data
          * pass a constant pointer to constant data
     */

     // a pointer can be dereferenced explicitly with *
     // or implicitly with -> and ->* operators
     Student student1(10, "Jane");
     Student student2(20, "John");
     student1.create();
     student1.Human::print(); // call parent class function
     student1.print();        // call child class function
     Student *studentptr = &student1;
     studentptr->create(); // same as (*studentptr).create();

     // downcasting - converting a base class pointer to a derived class pointer
     // upcasting - converting a derived class pointer to a base class pointer
     Human *h = new Student(); // upcasting
     h->display2();            // calls derived class function
     Student *s = dynamic_cast<Student *>(h); // downcasting
     s->display2();            // calls derived class function


     // range based for loop
     bool (*funcptr)(int, int) = nullptr;

     // Dynamic memory allocation
     void *z = malloc(sizeof(int));  // allocate memory for an int
     double *ptr = new double(3.14); // allocate memory for a double
     delete ptr;                     // deallocate memory for double
     int *arr = new int[5];          // allocate memory for an array
     delete[] arr;                   // deallocate memory for array

     /*
          File handling objects:
          basic_ifstream (input), basic_ofstream (output), basic_fstream (both)
          * removing basic_ will give you the char version *
     */

     /*
          File-open modes:
          * ios::app - append mode
          * ios::ate - open and move to end of file immediately 
                    (data can be written anywhere in the file)
          * ios::in - open for input, read content from file
          * ios::out - open for output, write content to file
          * ios::trunc - delete contents of file if it exists
          * ios::binary - open in binary mode
     */

     // open a file with a file stream object
     ofstream outData("data.txt", ios::out);         // open file for output
     ifstream inData("data.txt", ios::in);           // open file for input
     fstream ioData("data.txt", ios::in | ios::out); // both

     // another way to open a file
     fstream outData2;
     outData2.open("data.txt", ios::out);

     // open data to output in binary mode
     outData2.open("data.txt", ios::out | ios::binary);

     // check if file is open
     if (outData.is_open())
          ;
     // overloaded operator ! can be used to check if file is open
     if (!outData)
          ; // if file is not open or error when opening

     // close a file (automatically closed when destructor called)
     outData.close();

     // overloaded operator void* converts stream object to pointer
     // if file is open, it returns a non-null pointer

     // implicitly converts cin to pointer, loops until EOF
     while (cin >> x)
          ;

     // writing to a file
     outData << "Hello World" << endl;
     outData2.put('A'); // write a character to file

     // reading from a file
     fstream inData("data.txt", ios::in);
     if (!inData)
     {
          cerr << "File could not be opened" << endl;
          exit(1);
     }
     string line; // to store each line
     while (getline(inData, line)) // while there are lines to read
     {
          cout << line << endl;
     }

     // writing to a binary file
     outData.write("Hello World", 11); // write 11 bytes to file
     outData.write(reinterpret_cast<const char *>(&x), sizeof(x));
     /*
     reinterpret_cast<const char*>(&x) - all objs must be casted
     sizeof(x) - necessary in 2nd argument
     */

     // read from a binary file
     fstream inDataBinary("data.dat", ios::in | ios::binary);
     inDataBinary.read(reinterpret_cast<char *>(&x), sizeof(x));

     // read all records from file
     while (inDataBinary && !inDataBinary.eof()) // while there are records
          ;

     /*
          functions to reposition pointer in a file:
          * seekg(offset, direction) - seek get for istream class (input)
          * seekp(offset, direction) - seek put for ostream class (output)

          * offset is the number of bytes to move.
          * direction is ios::beg (beginning position),
          * ios::cur (current position), ios::end (end position)
     */
     inData.seekg(0);            // move to beginning of file
     inData.seekg(5);            // move to 5th byte of file
     inData.seekg(5, ios::beg);  // same as above
     inData.seekg(0, ios::end);  // move to end of file
     inData.seekg(-5, ios::cur); // move 5 bytes back from current

     // get current position of pointer
     inData.tellg(); // (or tellp() for output)

     // exception handling with user defined exception
     try
     {
          speed(120);
     }
     catch (overSpeed &e)
     {
          cout << e.what(); // prints "Over speed"
     }

     // how to use a template function
     print(10);          // prints "10"
     print<int>(10);     // prints "10"
     print(3.14);        // prints "3.14"
     print<float>(3.14); // prints "3.14"

     /*
          New features in C++11:
     */

     // smart pointers
     unique_ptr<int> uptr(new int(10)); // for dynamic alloc.
     shared_ptr<int> sptr(new int(10)); // for resource sharing
     weak_ptr<int> wptr(sptr);          // points to shared_ptr

     /*
          headers for multithreading:
          #include <thread>             // for thread creation
          #include <mutex>              // for mutex locking
          #include <condition_variable> // for thread synchronization
          #include <future>             // for async task
     */

     // noexcept - function that does not throw exceptions
     void func() noexcept { cout << "No exceptions"; }

     // vector initialization, before you could only push_back(value)
     vector<int> v = {1, 2, 3, 4, 5};

     // auto - automatically determines the data type
     auto i = 10;   // i is an int
     auto j = 3.14; // j is a double

     // default keyword for default constructor
     class myclass
     {
     public:
          myclass() = default;
     };

     return 0; // or system("pause") to wait for user input
}

// template function to print any type of data
template <class T> // or template <typename T>
void print(T data)
{
     cout << data;
}

template <class Y, class U>
auto add(Y a, U b) // auto will determine the return type
{
     return a + b;
}

// user defined exception class
class overSpeed : public exception
{
public:
     const char *what() { return "Over speed"; }
};

// function to throw exception
void speed(int speed)
{
     if (speed > 100)
          throw overSpeed();
          // or throw overSpeed{};
          // or throw overSpeed("Over speed");
          // or throw "Over speed";
}

// if a class contains static variables, then every object of the class has 
// its copy of static variable - FALSE

// static member functions can only call other static functions - TRUE

// inline functions speed up execution despite increasing code size - TRUE

// a compiler provides a default constructor if you don't provide one - TRUE

// pointer to member declaratory (::*)

// function overloading is an example of function overriding

// polymorphism is the ability to take many forms. defining a function with 
// same prototype in bas class & derived class

// a reference is stored on the stack, 'new' is stored on the heap

// Argv[0] is the name of the program

/*
     In an assignment statement:
     * the lvalue must always be a variable
     * the rvalue might be a constant, a variable, an expression or any combination of these
     * the assignment always takes place from right to left and never the other way
*/

// if header is not included, error at link time

// inheritance - defining new classes from the existing classes

// when a pointer is defined, it reserves memory for the pointer, not the object

// it is not C++ statement but the directive for the preprocessor - TRUE

// it is possible to define a new class inside another class - TRUE

// overloading of the function templates is possible - TRUE

