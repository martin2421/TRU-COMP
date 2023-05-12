#include <stdio.h>

// Function Prototypes
void displayMatrix(int matrix[][3]);
void displayFloatMatrix(float matrix[][3]);
void add(int matrix1[][3], int matrix2[][3], int result[][3]);
void subtract(int matrix1[][3], int matrix2[][3], int result[][3]);
void multiply(int matrix1[][3], int matrix2[][3], int result[][3]);
void divide(int matrix1[][3], int matrix2[][3], float result[][3]);

int main()
{
     int matrix1[3][3], matrix2[3][3], result[3][3];
     float resultFloat[3][3]; // used when dividing matrices

     int size = sizeof(matrix1) / sizeof(matrix1[0]);

     // user input for matrix1
     printf("Enter values for matrix1:\n");
     for (int i = 0; i < size; i++)
     {
          for (int j = 0; j < size; j++)
          {
               scanf("%d", &matrix1[i][j]);
          }
     }

     // user input for matrix2
     printf("Enter values for matrix2:\n");
     for (int i = 0; i < size; i++)
     {
          for (int j = 0; j < size; j++)
          {
               scanf("%d", &matrix2[i][j]);
          }
     }

     // display both matrices
     printf("****************************");
     printf("\nMatrix1:\n");
     displayMatrix(matrix1);
     printf("****************************");

     printf("\nMatrix2:\n");
     displayMatrix(matrix2);
     printf("****************************");

     // add two matrices and display result
     add(matrix1, matrix2, result);
     printf("\nMatrix1 + Matrix2 =\n");
     displayMatrix(result);

     // subtract matrix2 from matrix1 and display result
     subtract(matrix1, matrix2, result);
     printf("\nMatrix1 - Matrix2 =\n");
     displayMatrix(result);

     // multiply both matrices and display result
     multiply(matrix1, matrix2, result);
     printf("\nMatrix1 x Matrix2 =\n");
     displayMatrix(result);

     // divide matrix1 by matrix2 and display result
     divide(matrix1, matrix2, resultFloat);
     printf("\nMatrix1 / Matrix2 =\n");
     displayFloatMatrix(resultFloat);

     return 0;
}

// function to display matrix
void displayMatrix(int matrix[][3])
{
     for (int i = 0; i < 3; i++)
     {
          for (int j = 0; j < 3; j++)
          {
               printf("%d ", matrix[i][j]);
          }
          printf("\n");
     }
}

// function to display a float matrix
void displayFloatMatrix(float matrix[][3])
{
     for (int i = 0; i < 3; i++)
     {
          for (int j = 0; j < 3; j++)
          {
               printf("%.1f ", matrix[i][j]);
          }
          printf("\n");
     }
}

// function that adds two matrices
void add(int matrix1[][3], int matrix2[][3], int result[][3])
{
     for (int i = 0; i < 3; i++)
     {
          for (int j = 0; j < 3; j++)
          {
               result[i][j] = matrix1[i][j] + matrix2[i][j];
          }
     }
}

// function that subtracts two matrices (matrix 2 - matrix 1)
void subtract(int matrix1[][3], int matrix2[][3], int result[][3])
{
     for (int i = 0; i < 3; i++)
     {
          for (int j = 0; j < 3; j++)
          {
               result[i][j] = matrix1[i][j] - matrix2[i][j];
          }
     }
}

// function that multiplies two matrices
void multiply(int matrix1[][3], int matrix2[][3], int result[][3])
{
     for (int i = 0; i < 3; i++)
     {
          for (int j = 0; j < 3; j++)
          {
               result[i][j] = matrix1[i][j] * matrix2[i][j];
          }
     }
}

// function that divides two matrices (matrix 1 by matrix 2)
void divide(int matrix1[][3], int matrix2[][3], float result[][3])
{
     for (int i = 0; i < 3; i++)
     {
          for (int j = 0; j < 3; j++)
          {
               result[i][j] = (float)matrix1[i][j] / (float)matrix2[i][j];
          }
     }
}
