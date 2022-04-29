/**
 * Exception Class - checks for the student number from the input line. Main
 * program will throw this error if the input integer is not within the
 * predetermined length or entered a String
 */
public class InvalidStudentNumberException extends Exception {

     // Default Constructor
     public InvalidStudentNumberException() {
          super("The entered STUDENT NUMBER is invalid. The STUDENT NUMBER " +
                    "should be a 4-digit number within the " +
                    "range of 3000 and 8000.");
     }

     // Parameterized Constructor
     public InvalidStudentNumberException(int number, int index) {
          super("--> " + number + " <-- : Error on line " + index +
                    " of text file.\nThe entered STUDENT NUMBER is invalid. " +
                    "The STUDENT NUMBER should be a 4-digit number within the " +
                    "range of 3000 and 8000.");
     }

}
