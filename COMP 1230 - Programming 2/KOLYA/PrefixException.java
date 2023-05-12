package KOLYA;

public class PrefixException extends Exception {
     public PrefixException(int prefix) {
          super("'" + prefix + "' is not a valid prefix code or is not exactly 3 digits long.");
     }
}
