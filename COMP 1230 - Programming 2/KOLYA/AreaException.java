package KOLYA;

public class AreaException extends Exception {
     public AreaException(int areaCode) {
          super("'" + areaCode + "' is not a valid area code or is not exactly 3 digits long.");
     }
}
