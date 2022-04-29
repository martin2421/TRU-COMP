/**
 * Exception Class - thrown when a Pouch object is empty
 */
public class EmptyPouchException extends Exception {

     public EmptyPouchException() {

          System.out.println("EMPTY POUCH!!!");
          System.out.println("There is no item available in the pouch for you " +
                    " to remove...\n");

     }

}
