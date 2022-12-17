/****
 * Name: Martin Atanacio
 * Student Number: T00684924
 * Assignment Number: 6
 * Due Date: March 28th, 2022
 * Program Description: Practice creating and using a generic Pouch class. Creation
 * of methods to add, remove (pop), draw a random item, check if the pouch is empty
 * and display the contents of the pouch. Program creates 3 different pouches of 2 
 * different types, integers & strings. 
 ****/
public class TestPouch {
     public static void main(String[] args) throws FullPouchException,
               EmptyPouchException {

          System.out.println();

          // Pouch object containing only INTEGERS
          Pouch<Integer> pouchInt = new Pouch<Integer>();

          // Pouch object containing only STRINGS
          Pouch<String> names = new Pouch<String>();

          // Pouch object containing studying hours
          Pouch<Integer> hours = new Pouch<Integer>();

          /**
           * First test - INTEGERS Pouch
           */

          // Testing the toString() method before & after adding items to the
          // INTEGERS' pouch
          System.out.println("\ntoString() before adding an item to the pouch:");
          System.out.println(pouchInt.toString());
          System.out.println("drawItem() before adding an item to the pouch:");

          // Trying drawItem() before adding an item to the INTEGERS' pouch
          try {
               pouchInt.drawItem();
          } catch (EmptyPouchException empty) {
               System.out.println(empty);
          }

          // Checking if INTEGERS' pouch is empty before adding an item (currently
          // it should return false)
          System.out.println("Is the pouch currently empty?");
          System.out.println(pouchInt.isEmpty() + "\n");

          // Testing the addition of 11 integers into the INTEGERS' pouch object in
          // order to get a FullPouchException
          System.out.println("Adding 11 integers into the pouch object in order " +
                    "to get a FullPouchException:");

          try {
               for (int i = 0; i < 11; i++) {
                    pouchInt.add(i);
               }
          } catch (FullPouchException full) {
               System.out.println(full);
          }

          // Displaying all integers within the INTEGERS' pouch
          System.out.println("Elements in INTEGERS' pouch:");
          System.out.println(pouchInt.toString());

          // Testing the peek() method
          System.out.println("Testing the peek() method (should return the last " +
                    "item in the pouch):");
          System.out.println(pouchInt.peek() + "\n");

          // Testing to draw a random item from the INTEGERS' pouch
          try {
               for (int i = 0; i < 11; i++) {
                    System.out.println("Random item #" + (i + 1) + ": " +
                              pouchInt.drawItem());
               }
          } catch (EmptyPouchException empty) {
               System.out.println("\n" + empty);
          }

          // Displaying remaining items in INTEGERS' pouch
          System.out.println("\nRemaining items:");
          System.out.println(pouchInt.toString());

          /**
           * Second Test - STRINGS Pouch
           */

          // Testing the addition of name items in the STRING's pouch
          names.add("Martin");
          names.add("Wayne");
          names.add("Sid");
          names.add("Saviz");
          names.add("Nikolay");
          names.add("Tapiwa");
          names.add("Jay");

          // Displaying all strings within the STRING's pouch
          System.out.println("Elements in STRING's pouch:");
          System.out.println(names.toString());

          // Testing to draw a random item from the STRING's pouch
          for (int i = 0; i < 5; i++) {
               System.out.println("Random item #" + (i + 1) + ": " +
                         names.drawItem());
          }

          // Displaying all strings within the STRING's pouch after using the
          // drawItem() method
          System.out.println("\nRemaining items:");
          System.out.println(names.toString());

          /**
           * Third test - HOURS Pouch
           */

          // Testing the addition of hour items in the HOURS' pouch
          for (int i = 3; i < 8; i++) {
               hours.add(i);
          }

          // Displaying all hours within the HOURS' pouch
          System.out.println("Elements in HOURS' pouch:");
          System.out.println(hours.toString());

          // Testing to draw a random item from the HOURS' pouch
          for (int i = 0; i < 4; i++) {
               System.out.println("Random item #" + (i + 1) + ": " +
                         hours.drawItem() + "hrs");
          }

          // Displaying all hours within the HOURS' pouch after using the
          // drawItem() method
          System.out.println("\nRemaining items:");
          System.out.println(hours.toString());

     }
}
