#include <stdio.h>

int main()
{
     char firstName[20], lastName[20]; // max length of fields
     char department[30];
     int courseNumber, semester, year;
     char courseName[20];
     float fee;

     printf("Enter your first & last name: ");
     scanf("%s %s", &firstName, &lastName);

     printf("Enter your department: ");
     scanf("%s", &department);

     printf("Enter your Course number & Course name: ");
     scanf("%d %s", &courseNumber, &courseName);

     printf("Enter your semester & year: ");
     scanf("%d %d", &semester, &year);

     printf("Enter the course fee: ");
     scanf("%f", &fee);

     // Output
     printf("=================================================\n");
     printf("                STUDENT DETAILS                  \n");
     printf("=================================================\n");

     printf("Student Name: %s %s\n", firstName, lastName);
     printf("Department: %s\n", department);
     printf("Registered Course: %s (Course Code: %d)\n", courseName, courseNumber);
     printf("Currently Studying in: %d semester (%d)\n", semester, year);
     printf("Fees: $%.2f\n", fee);

     return 0;
}
