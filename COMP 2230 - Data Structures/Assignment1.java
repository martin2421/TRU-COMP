/*
 * Martin Atanacio
 * T00684924
 * Assignment 1 - Due 16 Sept 2022
 */

public class Assignment1 {

     /*
      * Test methods
      */

     public static void main(String[] args) {

          System.out.println("Logarithmic Algorithm");
          Logarithmic();
          System.out.println("\nLinear Algorithm");
          Linear();
          System.out.println("\nnLogn Algorithm");
          NLogN();
          System.out.println("\nQuadratic Algorithm");
          Quadratic();
          System.out.println("\nCubic Algorithm");
          Cubic();
          System.out.println("\nExponential Algorithm");
          Exponential();

     }

     /*
      * User defined methods
      */

     public static void Logarithmic() {

          /*
           * One loop with a running time growing in proportion to the logarithm of
           * the input, efficiency of O(log n)
           */

          System.out.println("counter variable increments by 1, while loop " +
                    "increments in proportion to the logarithm of the input.");

          int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
          int counter = 1;

          for (int i = counter; i <= nums.length; i *= 2) {
               System.out.println("Value of counter: " + counter);
               System.out.println("value in the array: " + nums[i - 1]);
               counter++;
          }

     }

     public static void Linear() {

          /*
           * One loop indicates an efficiency of O(n)
           */

          System.out.println("loop grows directly proportional to the size of" +
                    "its inputs.");

          String[] names = { "Martin", "Fernando", "Sid", "Tapiwa", "Kevin" };

          for (int i = 0; i < names.length; i++) {
               System.out.println("Counter: " + i + ", Member: " + names[i]);
          }

     }

     public static void NLogN() {

          /*
           * One linear loop followed by a logarithmic loop,
           * efficiency of O(n log n)
           */

          System.out.println("counter1 increments by 1, while counter2 " +
                    "duplicates each time");

          for (int i = 1; i <= 3; i++) {
               for (int j = 1; j < 8; j = j * 2) {
                    System.out.println("Value of counter1: " + i +
                              "\tValue of counter2: " + j);
               }
          }
     }

     public static void Quadratic() {

          /*
           * Two nested loops indicate an efficiency of O(n^2)
           */

          int rows = 3;
          int cols = 2;

          System.out.println("Two nested loops, one iterating through the " +
                    "number of rows and the other through the number of columns.");

          System.out.println("\nROWS = " + rows + "\tCOLUMNS = " + cols + "\n");

          System.out.println("*Beginning of outer loop*\n");

          for (int i = 0; i < rows; i++) {

               System.out.println("*Beginning of inner loop*");
               for (int j = 0; j < cols; j++) {
                    System.out.println("Pointer at:\tROW #" + (i + 1) +
                              "\tCOLUMN #" + (j + 1));
               }
               System.out.println("*Ending of inner loop*\n");
          }

          System.out.println("*Ending of outer loop*");

     }

     public static void Cubic() {

          /*
           * Three nested loops indicate an efficiency of O(n^3)
           */

          System.out.println("Three nested loops, each iterating through " +
                    "their own counters.");

          int loop1 = 4;
          int loop2 = 3;
          int loop3 = 2;

          for (int i = 1; i <= loop1; i++) {
               for (int j = 1; j <= loop2; j++) {
                    for (int k = 1; k <= loop3; k++) {
                         System.out.println("value of i = " + i + ", value of j = "
                                   + j + ", value of k = " + k);
                    }
               }
          }

     }

     public static void Exponential() {

          /*
           * loops that grows in proportion to some factor exponentiated
           * by the input size. Efficiency of O(2^n)
           */

          int n = 3;

          System.out.println("n = " + n + ", this loop will run 2^n (2^" +
                    n + " = " + ((int) Math.pow(2, n)) + ") times.");

          for (int i = 0; i < Math.pow(2, n); i++) {
               System.out.println("Iteration #: " + (i + 1));
          }

     }

}
