/**
 * Exception Class - thrown when a Pouch object is full
 */
public class FullPouchException extends Exception {

     public FullPouchException() {

          System.out.println("FULL POUCH!!!");
          System.out.println("In order to add an additional item, please " +
                    "remove one item...\n");

     }

}
