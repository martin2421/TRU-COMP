/**
 * Exception Class - checks for the length of the input line. Main program will
 * throw this error if the line is not equal to the predetermined length
 */
public class InputTooShortException extends Exception {

     // Default Constructor
     public InputTooShortException() {
          super("Input is incorrect. It should consist of 20 characters:\n"
                    + "Name (15 characters), Student No. (4 characters), " +
                    "and Program Initial (1 character). If necessary, fill out " +
                    "the name with extra spaces to the right so that it reaches " +
                    "the number of characters required.");
     }

     // Paramaterized Constructor
     public InputTooShortException(String line, int index) {
          super("--> " + line + " <-- : Error on line " + index + " of text file."
                    + "\nInput is incorrect. It should consist of 20 characters:\n"
                    + "Name (15 characters), Student No. (4 characters), " +
                    "and Program Initial (1 character). If necessary, fill out " +
                    "the name with extra spaces to the right so that it reaches " +
                    "the number of characters required.");
     }

}
