#include <stdio.h>
#include <stdlib.h>

// Student struct
struct student
{
     char name[30];
     char address[100];
     int semester;
     char course[20];
     char phone[20];
     float fees;
};

//  Function Prototype
void personalInfo(struct student *s);

// Function Definition
void personalInfo(struct student *s)
{
     printf("Name: ");
     scanf("%s", s->name); // char[] returns address, no dereference needed

     printf("Semester: ");
     scanf("%d", &s->semester); // returns value, address needed

     printf("Course: ");
     scanf("%s", s->course);

     printf("Address: ");
     scanf("%s", s->address);

     printf("Phone #: ");
     scanf("%s", s->phone);

     printf("Fees: ");
     scanf("%f", &s->fees);
}

int main()
{

     int num;
     printf("Number of students: ");
     scanf("%d", &num);

     // Allocates memory (number of students * size of struct student)
     struct student *students = (struct student *) malloc(num * sizeof(struct student));

     // loop to input info of each student
     for (int i = 0; i < num; i++)
     {
          printf("\nEnter details of student %d:\n", i + 1);
          personalInfo(&students[i]);
     }

     // loop to print each student
     for (int i = 0; i < num; i++)
     {
          printf("\nDetails of student #%d:\n", i + 1);
          printf("\tName: %s\n", students[i].name);
          printf("\tSemester: %d\n", students[i].semester);
          printf("\tCourse: %s\n", students[i].course);
          printf("\tAddress: %s\n", students[i].address);
          printf("\tContact number: %s\n", students[i].phone);
          printf("\tFees: $%.2f\n", students[i].fees);
     }

     return 0;
}
