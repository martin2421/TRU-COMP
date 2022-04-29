import java.util.Random;

// Generic class
public class Pouch<Thing> {

     private int size;
     private int randomNum;
     private final int POUCH_SIZE = 10;

     @SuppressWarnings("unchecked")
     Thing[] contents = (Thing[]) (new Object[POUCH_SIZE]);
     Random random = new Random();

     // Default constructor
     public Pouch() {

          System.out.println("-->Pouch Object Created<--");

     }

     // add() method, throws FullPouchException if pouch is full
     public void add(Thing element) throws FullPouchException {

          if (contents.length == size) {
               throw new FullPouchException();
          }

          contents[size] = element;
          size++;

     }

     // isEmpty() method, checks if pouch array is empty
     public boolean isEmpty() {

          if (size == 0) {
               return true;
          } else {
               return false;
          }

     }

     // drawItem() method, removes one element at a time and shifts contents to the
     // left, throws EmptyPouchException if pouch is empty
     public Thing drawItem() throws EmptyPouchException {

          if (!(this.isEmpty())) {

               randomNum = random.nextInt(size);
               Thing result = contents[randomNum];

               // Shifts elements to the left
               for (int j = randomNum; j < contents.length - 1; j++) {
                    contents[j] = contents[j + 1];
               }

               // last element becomes null
               contents[contents.length - 1] = null;
               size--;

               return result;

          } else {
               throw new EmptyPouchException();
          }

     }

     // Returns the element at the top of the pouch
     public Thing peek() {

          try {

               if (this.isEmpty()) {
                    throw new EmptyPouchException();
               }

          } catch (EmptyPouchException empty) {
               System.out.println(empty);
          }

          return contents[size - 1];

     }

     // toString() method
     public String toString() {

          String result = "";

          if (isEmpty()) {

               System.out.println("The current pouch is empty...");

          } else {

               for (int i = 0; i < size; i++) {
                    result = result + contents[i] + "\n";
               }

          }

          return result;

     }

}
