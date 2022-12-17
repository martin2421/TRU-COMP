/****
 * Name: Martin Atanacio
 * Student Number: T00684924
 * Assignment Number: 4
 * Due Date: February 14th, 2022
 * Program Description: Testing the use of a interface so that the Plant
 * class implements from it and all Plant objects will requires these 
 * methods/attributes. Testing the use of Comparable<Plant> since we will be using 
 * the compareTo method to compare Plant objects. Testing how to use Polymorphism
 * and casting to an object when calling its toString method and getters/setters. 
 ****/

public class PlantDriver {

     static Plant[] plants;
     static Spice spice1, spice2;
     static Vegetable veg1, veg2;
     static Medicinal med1, med2;

     /**
      * Main Method
      */

     public static void main(String[] args) {

          // Creates 2 objects per class (2 Spices, 2 Vegetables, and 2 Medicinals)
          initializeSpices();
          initializeVegetables();
          initializeMedicinals();

          // Creates a Plant array containing 2 objects per class
          initializeArray();

          // Display info for each object on the array
          for (int i = 0; i < plants.length; i++) {

               // Print correct header per object
               if (plants[i].getType().equals("Spice")) {
                    System.out.println("Spice Object");
               } else if (plants[i].getType().equals("Vegetable")) {
                    System.out.println("Vegetable Object");
               } else {
                    System.out.println("Medicinal Object");
               }

               System.out.println("\n" + plants[i].toString());

          }

          // Testing the abstract methods
          System.out.println("Testing abstract methods from interface\n");

          for (int i = 0; i < plants.length; i++) {

               System.out.print(plants[i].getName() + "'s " +
                         plants[i].country());
               System.out.print(plants[i].getName() + "'s " +
                         plants[i].howUsed() + "\n");

          }

          /**
           * Testing Vegetable objects by casting them and calling their getters
           * and setters
           */
          System.out.println("Testing casting a Vegetable object from plants array"
                    + " so we can test its price accessor and mutator\n");

          // temporary counter for header
          int counter = 1;

          for (int i = 0; i < plants.length; i++) {

               if (plants[i].getType().equals("Vegetable")) {

                    // getting the price
                    System.out.println("Testing the price getter for Vegetable #" +
                              counter + " " + ((Vegetable) plants[i]).getPrice());

                    // changing the price
                    System.out.println("***changed the price to 0.99***");
                    ((Vegetable) plants[i]).setPrice(0.99);

                    // getting the price
                    System.out.println("Testing the price getter for Vegetable #" +
                              counter + " " + ((Vegetable) plants[i]).getPrice() +
                              "\n");

                    counter++;

               }

          }

          // Testing the compareTo() method (all 3 solutions)
          System.out.println("Testing the compareTo() method:\n");

          System.out.println("Testing for a positive value:");
          System.out.println("\t" + veg2.compareTo(veg1) + " (string is " +
                    "lexicographically greater than the other string)\n");

          System.out.println("Testing for a value of 0:");
          System.out.println("\t" + veg1.compareTo(veg1) + " (string is equal to "
                    + "the other string)\n");

          System.out.println("Testing for a negative value:");
          System.out.println("\t" + veg1.compareTo(veg2) + " (string is " +
                    "lexicographically less than the other string)\n");

     }

     /**
      * Helper Methods
      */

     public static void initializeArray() {

          plants = new Plant[] { spice1, spice2, veg1, veg2, med1, med2 };

     }

     public static void initializeSpices() {

          /**
           * Initializing an empty and two parameterized (including one duplicated)
           * Spice objects
           */
          spice1 = new Spice("Cinnamon", "Spice", "China",
                    "Added to Desserts", "Bark of Cinnamomum Tree");

          spice2 = new Spice("Ginger", "Spice", "Nepal",
                    "Added to Desserts", "Rhizome of Zingiber Officinale plant");

     }

     public static void initializeVegetables() {

          /**
           * Initializing an empty and two parameterized (including one duplicated)
           * Vegetable objects
           */
          veg1 = new Vegetable("Corn", "Vegetable", "Mexico",
                    "Many uses (salads, cornmeal, syrup)", 0.25);

          veg2 = new Vegetable("Potato", "Vegetable", "Peru",
                    "Baked or fried to create potato chips", 0.15);

     }

     public static void initializeMedicinals() {

          /**
           * Initializing an empty and two parameterized (including one duplicated)
           * Medicinal objects
           */
          med1 = new Medicinal("Aspirin", "Medicinal", "Germany",
                    "Pain relief", "Tablets");

          med2 = new Medicinal("Digoxin", "Medicinal", "United Kingdom",
                    "Treat heart failure", "Liquid");

     }

}
