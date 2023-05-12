#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// union to hold employee information
union employee
{
     char name[20];
     int departmentNumber;
     char departmentName[20];
     char designation[20];
     float salary;

     struct date
     {
          int day;
          int month;
          int year;
     } dateOfBirth, dateJoined;

};

int main()
{
     int n; // number of employees
     float highestSalary = -999;
     int indexOfHighestSalary = -999;

     printf("Number of employees: ");
     scanf("%d", &n);

     // Allocates memory for the array of employee unions
     union employee *employees = malloc(n * sizeof(union employee));

     // Accept input from user
     for (int i = 0; i < n; i++)
     {
          printf("\nEnter details for employee #%d:\n", i + 1);

          printf("Name: ");
          scanf("%s", employees[i].name);

          printf("Department #: ");
          scanf("%d", &employees[i].departmentNumber);

          printf("Department Name: ");
          scanf("%s", employees[i].departmentName);

          printf("Designation: ");
          scanf("%s", employees[i].designation);

          printf("Date of birth (dd/mm/yyyy): ");
          scanf("%d/%d/%d", &employees[i].dateOfBirth.day, &employees[i].dateOfBirth.month, &employees[i].dateOfBirth.year);

          printf("Date joined (dd/mm/yyyy): ");
          scanf("%d/%d/%d", &employees[i].dateJoined.day, &employees[i].dateJoined.month, &employees[i].dateJoined.year);

          printf("Salary: $");
          scanf("%f", &employees[i].salary);
     }

     // loop to find employee with the highest salary
     for (int i = 0; i < n; i++)
     {
          if (employees[i].salary > highestSalary)
          {
               highestSalary = employees[i].salary;
               indexOfHighestSalary = i; // stores index to output employee info
          }
     }

     // Print details of employee with highest salary
     printf("\nInformation of employee with the highest salary:\n");
     printf("Name: %s\n", employees[indexOfHighestSalary].name);
     printf("Department #: %d\n", employees[indexOfHighestSalary].departmentNumber);
     printf("Department Name: %s\n", employees[indexOfHighestSalary].departmentName);
     printf("Designation: %s\n", employees[indexOfHighestSalary].designation);
     printf("Date of Birth: %d/%d/%d\n", employees[indexOfHighestSalary].dateOfBirth.day, employees[indexOfHighestSalary].dateOfBirth.month, employees[indexOfHighestSalary].dateOfBirth.year);
     printf("Date Joined: %d/%d/%d\n", employees[indexOfHighestSalary].dateJoined.day, employees[indexOfHighestSalary].dateJoined.month, employees[indexOfHighestSalary].dateJoined.year);
     printf("Salary: $%.2f\n", employees[indexOfHighestSalary].salary);

     return 0;
}
