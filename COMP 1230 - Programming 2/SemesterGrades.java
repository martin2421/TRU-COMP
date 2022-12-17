/****
 * Name: Martin Atanacio
 * Student Number: T00684924
 * Assignment Number: 1
 * Due Date: January 24th, 2022
 * Program Description: Use one and two dimensional arrays to create a
 * table that stores and displays student's grades and calculates their final
 * grade for the semester & the average per exam.
 * 
 ****/

public class SemesterGrades {

     /**
      * Variable Declaration
      */
     static String[] names; // Array of names
     static double[] averages; // Array of average marks for each exam
     static double[] semester; // Array of semester grade for each student
     static double[][] marks; // 2D Array containing the marks for each student

     /**
      * Main Method
      */
     public static void main(String[] args) {

          // Initialize Arrays using initialization lists
          InitializeArrays();

          // Calculates the semester mark for each student
          CalculateSemester();

          // Calculates the average mark for each exam
          CalculateAverage();

          // Prints out the results
          PrintTable();

     }

     /**
      * Helper Methods
      */
     public static void InitializeArrays() {

          // Initializes 'names' array with values
          names = new String[] {
                    "Joe Smith", "Tommy Jones", "Sara Lee",
                    "Bob Fowler", "Jane Doe"
          };

          // Initializes 'marks' array with values
          marks = new double[][] {
                    { 100, 100, 100, 100 },
                    { 0, 0, 0, 0 },
                    { 70, 70, 70, 70 },
                    { 50, 60, 70, 80 },
                    { 63, 73, 71, 77 }
          };

     }

     public static void CalculateSemester() {

          // Calculate the size of 'names' array
          semester = new double[names.length];

          // Weight for each exam type (tests & final exam)
          double test_weight = 0.2;
          double final_weight = 0.4;
          double sum = 0.0; // Temporary variable with sum

          // Loop that calculates the semester mark for each student
          for (int ROW = 0; ROW < names.length; ROW++) {
               for (int COL = 0; COL < marks[0].length; COL++) {
                    if (COL == marks[ROW].length - 1) {
                         // Final weight
                         sum += marks[ROW][COL] * final_weight; 
                    } else {
                         // Test weight
                         sum += marks[ROW][COL] * test_weight;
                    }
               }
               semester[ROW] = sum;
               sum = 0.0; // Clear the sum
          }

     }

     public static void CalculateAverage() {

          // Calculate the size of 'names' and 'averages' arrays
          averages = new double[marks[0].length];

          double sum = 0.0; // Temporary variable with sum

          // Loop that calculates the average mark for each exam
          for (int COL = 0; COL < marks.length - 1; COL++) {
               for (int ROW = 0; ROW < names.length; ROW++) {
                    sum += marks[ROW][COL];
               }
               averages[COL] = sum / names.length;
               sum = 0.0; // Clear the sum
          }
          sum = 0.0; // Clear the sum

     }

     public static void PrintTable() {

          // Print 'Name' and 'Marks' Headers and Subheaders
          System.out.println("\nName\t\t\tMarks (out of 100)");
          System.out.printf("%-15s %6s %6s %6s %6s %7s\n", "", "Test 1",
                    "Test 2", "Test 3", "Final", "Semester");

          // Print Names and grades
          for (int i = 0; i < names.length; i++) {

               // Prints names (left alligned)
               System.out.printf("%-15s ", names[i]);

               // Prints grades (right alligned)
               for (int j = 0; j < marks[0].length; j++) {
                    System.out.printf("%6.0f ", marks[i][j]);
               }

               // Prints semester grades (right alligned)
               System.out.printf("%7.1f", semester[i]);
               System.out.printf("\n");

          }

          // Print 'Average' Header
          System.out.printf("%-15s ", "Average");

          // Print Averages underneath (right alligned)
          for (int i = 0; i < averages.length; i++) {
               System.out.printf("%6.1f ", averages[i]);
          }

          double sum = 0.0; // Temporary variable with final grade
          double semester_average = 0.0; // Average for all semester grades
          int semester_size = semester.length; // Size of 'semester' array

          // Loop that calculates the average semester mark
          for (int i = 0; i < semester_size; i++) {
               sum += semester[i];
               semester_average = sum / semester_size;
          }
          
          // Prints final average grade from all semester grades
          System.out.printf("%7.1f", semester_average);

     }

}
