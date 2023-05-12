/**
 * Exception Class - thrown when a Sack object is full
 */
public class FullSackException extends Exception {

     FullSackException() {
          super("*** SACK IS FULL ***\nIn order to add an additional item, please " +
                    "remove one item...\n");
     }

     FullSackException(String message) {
          super(message);
     }

}
