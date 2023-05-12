#include <stdio.h>
#include <stdlib.h>

/*
     NOTE:
          1. Use of '%b' does not work.
          2. int num = 0b10101.110 won't detect decimals.
*/

// converts an int to binary (requires an int and length)
char *toBinary(int n, int len)
{
     char *binary = (char *)malloc(sizeof(char) * len);
     int k = 0;
     for (unsigned i = (1 << len - 1); i > 0; i = i / 2)
     {
          binary[k++] = (n & i) ? '1' : '0';
     }
     binary[k] = '\0';
     return binary;
}

int main()
{

     // int > binary
     int x_int = 361;
     char *x_binary = toBinary(x_int, 9);
     printf("\nValue of x: %d", x_int);
     printf("\nValue of x in binary: %s\n", x_binary);


     return 0;
}
