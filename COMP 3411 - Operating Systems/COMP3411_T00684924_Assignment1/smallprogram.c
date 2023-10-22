#include <stdio.h>
#define MAX 40 // maximum name length

// display function
void display (char n[], int t)
{
	int i = 0;
	for (i = 0; i < t; i++)
		printf("%s\n",n);
}

// main function
void main()
{
	char name[MAX]; // user's name
	int loop = 0; // number of loops

	printf("What is your name? ");
	fgets(name, MAX, stdin); // read name from terminal
	printf("How many times shall I print it? ");
	scanf("%d", &loop); // read no. of times
	display(name, loop);
} // end of main()

