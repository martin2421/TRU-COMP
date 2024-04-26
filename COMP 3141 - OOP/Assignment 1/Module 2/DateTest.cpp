#include "Date.h"

int main()
{
     // create a date object with initial values
     Date date(1, 30, 2024); // February 21, 2024

     // display the date
     cout << "Initial date is: "; 
     date.displayDate();

     // change the date using set methods
     date.setMonth(12);
     date.setDay(14);
     date.setYear(2001);

     // display the new date
     cout << "New date: ";
     date.displayDate();

     return 0;
}
