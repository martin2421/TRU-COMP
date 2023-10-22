public class MultiplyMatrices extends Thread {

     // Variables
     private int[][] matrixA, matrixB, matrixResult;
     private int row, col;

     // Constructor
     // Accepts 3 matrices (1 being the result matrix), and the # of rows and cols 
     public MultiplyMatrices(int[][] matrixA, int[][] matrixB, int[][] matrixResult, int row, int col) {

          this.matrixA = matrixA;
          this.matrixB = matrixB;
          this.matrixResult = matrixResult;
          this.row = row;
          this.col = col;

     }

     @Override
     public void run() {

          int sum = 0;

          // finds the sum of the multiplications
          for (int i = 0; i < matrixA[0].length; i++) {
               sum += matrixA[row][i] * matrixB[i][col];
          }

          // store sum in its corresponding position in the matrix
          matrixResult[row][col] = sum;

     }
}