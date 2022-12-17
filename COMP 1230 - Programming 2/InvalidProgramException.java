/**
 * Exception Class - checks for the last digit of the input file, which should
 * correspond to the program initial the student is in. Main program will throw
 * this error if the character is not equal to one of the predetermined ones
 */
public class InvalidProgramException extends Exception {

     // Default Constructor
     public InvalidProgramException() {
          super("The entered PROGRAM is not correct. The PROGRAM should ONLY " +
                    "contain one of the following:\n" +
                    "Letters C for (Computing), E for (Engineering), L for (Law)" +
                    " or N for (Nursing).");
     }

     // Parameterized Constructor
     public InvalidProgramException(char program, int index) {
          super("--> " + program + " <-- : Error on line " + index +
                    " of text file.\nThe entered PROGRAM is not correct. The " +
                    "PROGRAM should ONLY contain one of the following:\n" +
                    "Letters C for (Computing), E for (Engineering), L for (Law)" +
                    " or N for (Nursing).");
     }

}
