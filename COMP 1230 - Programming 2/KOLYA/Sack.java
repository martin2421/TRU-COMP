import java.util.Random;

// Generic class
public class Sack<T> implements SackADT<T> {

     private int size;
     private int randomNum;
     private final int SACK_SIZE = 10;

     @SuppressWarnings("unchecked")
     T[] contents = (T[]) (new Object[SACK_SIZE]);
     Random random = new Random();

     // Default constructor
     public Sack() {
          System.out.println("Sack Object Created");
     }

     // add() method, throws FullSackException if sack is full
     public void add(T element) throws FullSackException {

          if (contents.length == size) {
               throw new FullSackException();
          }

          contents[size] = element;
          size++;

     }

     // isEmpty() method, checks if sack array is empty
     public boolean isEmpty() {

         return size == 0;

     }

     // drawItem() method, removes one element at a time and shifts contents to the
     // left, throws EmptySackException if sack is empty
     public T drawItem() throws EmptySackException {

          if (!(this.isEmpty())) {

               randomNum = random.nextInt(size);
               T result = contents[randomNum];

               // Shifts elements to the left
               for (int j = randomNum; j < contents.length - 1; j++) {
                    contents[j] = contents[j + 1];
               }

               // last element becomes null
               contents[contents.length - 1] = null;
               size--;

               return result;

          } else {
               throw new EmptySackException();
          }

     }

     // toString() method
     public String toString() {

          String result = "";

          if (isEmpty()) {

               System.out.println("The current sack is empty...");

          } else {

               for (int i = 0; i < size; i++) {
                    result = result + contents[i] + "\n";
               }

          }

          return result;

     }


}
