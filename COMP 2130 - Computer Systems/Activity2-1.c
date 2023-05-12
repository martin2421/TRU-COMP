#include <stdio.h>

int main()
{
     // variables
     int int_num;
     double double_num;
     char ch;

     printf("Enter three input values = ");
     scanf("%lf", &double_num); // double
     scanf("%d", &int_num); // int
     scanf(" %c", &ch); // char

     printf("The entered values are:\n");
     printf("%lf \n", double_num); // double
     printf("%d \n", int_num); // int
     printf("%c \n", ch); // char

     return 0;
}