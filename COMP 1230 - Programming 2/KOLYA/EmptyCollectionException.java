package KOLYA;

public class EmptyCollectionException extends RuntimeException {
     
     public EmptyCollectionException(String collection) {

          super("The " + collection + " is empty.");

     }

     public EmptyCollectionException() {

          super("The collection is empty.");

     }
}
