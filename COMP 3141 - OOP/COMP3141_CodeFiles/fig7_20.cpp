// Fig. 7.20: fig07_20.cpp
// Initializing multidimensional arrays.
#include <iostream>
#include <array>
using namespace std;
using std::array;
const size_t rows = 2;
const size_t columns = 3;
void printArray( const array< array< int, columns >, rows> & );

int main()
{
   array< array< int, columns >, rows > array1 = { 1, 2, 3, 4, 5, 6 };
   array< array< int, columns >, rows > array2 = { 1, 2, 3, 4, 5 };

   cout << "Values in array1 by row are:" << endl;
   printArray( array1 );

   cout << "\nValues in array2 by row are:" << endl;
   printArray( array2 );
} // end main

// output array with two rows and three columns
void printArray( const array< array< int, columns >, rows> & a )
{
   // loop through array's rows
   for ( auto const &row : a )
   {    
      // loop through columns of current row
      for ( auto const &element : row )
         cout << element << ' ';

      cout << endl; // start new line of output
   } // end outer for
} // end function printArray

