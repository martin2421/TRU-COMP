package KOLYA;

public class LineException extends Exception {
     public LineException(String line) {
          super("'" + line + "' is not 4 digits long.");
     }
}
