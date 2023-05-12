#include <stdio.h>

int main()
{

     int nums[] = {50, 14, 25, 68, 78, 3}; // array of ints
     int *ptr_num = nums; // stores the address of the array, nums[0]
     int large = *ptr_num; // large is given the first value of array
     int size_num = sizeof(nums) / sizeof(nums[0]); // size of array

     // loops through array
     for (int i = 1; i <= size_num; i++)
     {
          if (*(ptr_num + i) > large) // dereferences the next memory address
          {
               large = *(ptr_num + i);
          }
     }

     printf("Largest element in the array: %d", large);
     return 0;
}