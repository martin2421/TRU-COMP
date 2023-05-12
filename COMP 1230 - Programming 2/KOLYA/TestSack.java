
public class TestSack {
     public static void main(String[] args) throws FullSackException,
               EmptySackException {

          System.out.println();

          // Sack object containing integers - tests' sack
          Sack<Integer> sackInt = new Sack<Integer>();

          // Sack object containing friend's names
          Sack<String> names = new Sack<String>();

          // Sack object containing studying hours
          Sack<Integer> hours = new Sack<Integer>();

          /**
           * First test - TESTS Sack
           */

          // Testing the toString() method before & after adding items to the
          // TEST'S sack
          System.out.println("\nisEmpty() before adding an item to the sack: " +
          sackInt.isEmpty());
          System.out.println("\ntoString() before adding an item to the sack:");
          System.out.println("\t" + sackInt.toString());
          System.out.println("drawItem() before adding an item to the sack:");

          // Trying drawItem() before adding an item to the TEST'S sack
          try {
               sackInt.drawItem();
          } catch (EmptySackException empty) {
               System.out.print("\t" + empty.getMessage());
          }

          // Check if TEST'S sack is empty before adding an item (currently
          // it should return false)
          System.out.println("\nIs the sack currently empty? " + sackInt.isEmpty());

          // Testing the addition of 11 integers into the TEST'S sack object in
          // order to get a FullSackException
          System.out.println("\nAdding 11 integers into the sack object in order " +
                    "to get a FullSackException:");

          try {
               for (int i = 0; i < 11; i++) {
                    sackInt.add(i);
               }
          } catch (FullSackException full) {
               System.out.println("\t" + full.getMessage());
          }

          // Displaying all integers within the TEST'S sack
          System.out.println("Elements in TEST'S sack:");
          System.out.println(sackInt.toString());

          // Testing to draw a random item from the TEST'S sack
          try {
               for (int i = 0; i < 11; i++) {
                    System.out.println("Random item #" + (i + 1) + ": " +
                              sackInt.drawItem());
               }
          } catch (EmptySackException empty) {
               System.out.println("\n" + empty.getMessage());
          }

          // Displaying remaining items in TEST'S sack
          System.out.println("Remaining items:");
          System.out.println(sackInt.toString());

          /**
           * Second Test - STRINGS Sack
           */

          // Testing the addition of name items in the STRING's sack
          names.add("Friend #1");
          names.add("Friend #2");
          names.add("Friend #3");
          names.add("Friend #4");
          names.add("Friend #5");
          names.add("Friend #6");
          names.add("Friend #7");
          names.add("Friend #8");

          // Displaying all strings within the STRING's sack
          System.out.println("Elements in STRING's sack:");
          System.out.println(names.toString());

          // Testing to draw a random item from the STRING's sack
          for (int i = 0; i < 3; i++) {
               System.out.println("Study Friend #" + (i + 1) + ": " +
                         names.drawItem());
          }

          // Displaying all strings within the STRING's sack after using the
          // drawItem() method
          System.out.println("\nRemaining items:");
          System.out.println(names.toString());

          /**
           * Third test - HOURS Sack
           */

          // Testing the addition of hour items in the HOURS' sack
          for (int i = 10; i < 20; i++) {
               hours.add(i);
          }

          // Displaying all hours within the HOURS' sack
          System.out.println("Elements in HOURS' sack:");
          System.out.println(hours.toString());

          // Testing to draw a random item from the HOURS' sack
          for (int i = 0; i < 3; i++) {
               System.out.println("Hours to study: " + hours.drawItem() + "hrs");
          }

     }
}
