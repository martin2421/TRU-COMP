#include <stdio.h>
#include <time.h>

// Structure to store date information
struct date
{
     int day, month, year;
};

// Structure to store employee information
struct employee
{
     char name[30];
     int departmentNumber;
     char departmentName[30];
     char designation[30];
     struct date dateOfBirth;   // int day, month, year
     struct date dateOfJoining; // int day, month, year
};

int main()
{
     struct employee e; // creates an employee

     // Accept user input for employee details
     printf("Name: ");
     scanf("%s", &e.name);

     printf("Department #: ");
     scanf("%d", &e.departmentNumber);

     printf("Department Name: ");
     scanf("%s", &e.departmentName);

     printf("Designation: ");
     scanf("%s", &e.designation);

     printf("Date of Birth (dd/mm/yyyy): ");
     scanf("%d/%d/%d", &e.dateOfBirth.day, &e.dateOfBirth.month, &e.dateOfBirth.year);

     printf("Date of Joining (dd/mm/yyyy): ");
     scanf("%d/%d/%d", &e.dateOfJoining.day, &e.dateOfJoining.month, &e.dateOfJoining.year);

     // Calculates age from current time
     time_t t = time(NULL);                           // stores current date in the form of time_t
     struct tm *current_time = localtime(&t);         // pointer to a readable time
     int current_year = current_time->tm_year + 1900; // gets current year
     int age = current_year - e.dateOfBirth.year;     // calculates age

     // Calculates number of working days from joined date to current date
     time_t dateJoined = mktime(&((struct tm){0, 0, 0, e.dateOfJoining.day, e.dateOfJoining.month - 1, e.dateOfJoining.year - 1900}));
     time_t timeToday = time(NULL);                                         // stores current time in the form of time_t
     double workingDays = difftime(timeToday, dateJoined) / (60 * 60 * 24); // finds the difference between current date and date joined

     // Prints employee information
     printf("\n\nEmployee Information:\n");
     printf("Name: %s", e.name);
     printf("\nDepartment Number: %d", e.departmentNumber);
     printf("\nDepartment Name: %s", e.departmentName);
     printf("\nDesignation: %s", e.designation);
     printf("\nDate of Birth: %d/%d/%d ", e.dateOfBirth.day, e.dateOfBirth.month, e.dateOfBirth.year);
     printf("(%d years old)\n", age);
     printf("Date of Joining: %d/%d/%d ", e.dateOfJoining.day, e.dateOfJoining.month, e.dateOfJoining.year);
     printf("(%.0f working days)\n", workingDays);

     return 0;
}