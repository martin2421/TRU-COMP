import java.util.Arrays;

public class Assignment6 {

     public static void main(String[] args) {

          // define the range of array
          int min = 1;
          int max = 999;
          int range = max - min + 1;

          // array containing values
          int[] array = new int[40];

          // generate 40 random numbers from min to max
          for (int i = 0; i < array.length; i++) {
               array[i] = (int) (Math.random() * range) + min;
          }

          // print unsorted array
          System.out.println("Unsorted Array:");
          System.out.println(Arrays.toString(array));

          // sorts array and prints its contents
          Arrays.sort(array);
          System.out.println("\nSorted Array:");
          System.out.println(Arrays.toString(array));

          // Testing binary search method
          System.out.println("\nTesting binarySearch() method to search for a number:\n");

          System.out.println("Searching for 22:");
          int index1 = Arrays.binarySearch(array, 22);
          if (index1 >= 0) {
               System.out.println("Found target at index array[" + index1 + "]");
          } else {
               System.out.println("Target not found. Target should be inserted at " +
                         "array[" + index1 + "]");
          }

          System.out.println("Searching for 444:");
          int index2 = Arrays.binarySearch(array, 444);
          if (index2 >= 0) {
               System.out.println("Found target at index array[" + index2 + "]");
          } else {
               System.out.println("Target not found. Target should be inserted at " +
               "array[" + index2 + "]");
          }

          System.out.println("Searching for 1000:");
          int index3 = Arrays.binarySearch(array, 1000);
          if (index3 >= 0) {
               System.out.println("Found target at index array[" + index3 + "]");
          } else {
               System.out.println("Target not found. Target should be inserted at " +
                         "array[" + index3 + "]");
          }

     }

}
