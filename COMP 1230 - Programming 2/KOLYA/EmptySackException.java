/**
 * Exception Class - thrown when a Sack object is empty
 */
public class EmptySackException extends Exception {

     EmptySackException() {
          super("*** SACK IS EMPTY ***\nThere is no item available in the sack for you to remove...\n");
     }
     
     EmptySackException(String message) {
          super(message);
     }

}
