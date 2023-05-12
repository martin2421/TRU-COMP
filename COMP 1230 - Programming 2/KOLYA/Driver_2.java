package KOLYA;

public class Driver_2 {

     public static void main(String[] args) {

          // Creation of Pyrite object
          Pyrite p1 = new Pyrite();
          Pyrite p2 = new Pyrite("Pyrite1", 6.9, "Lil Uzi");

          // Pyrite Output
          System.out.println("Default Pyrite");
          // System.out.println("\tName: " + p1.getName());
          // System.out.println("\tWeight: " + p1.getWeight() + "kg");
          System.out.println("\t(aka): " + p1.getAka());

          System.out.println();
          System.out.println("Parameterized Pyrite");
          // System.out.println("\tName: " + p2.getName());
          // System.out.println("\tSetting name to NEW-PYRITE...");
          // p2.setName("NEW-PYRITE");
          // System.out.println("\tName: " + p2.getName());
          // System.out.println("\tWeight: " + p2.getWeight() + "kg");
          // System.out.println("\tSetting weight to 7kg...");
          // p2.setWeight(7);
          // System.out.println("\tWeight: " + p2.getWeight() + "kg");

          System.out.println("\t(aka): " + p2.getAka());
          System.out.println("\tSetting (aka) to Lil Pump...");
          p2.setAka("Lil Pump");
          System.out.println("\t(aka): " + p2.getAka());

          System.out.println("\tTesting toString() method...");
          System.out.println("\tPyrite 1:\t" + p1.toString());
          System.out.println("\tPyrite 2:\t" + p2.toString());
          System.out.println();

          /*
           * Testing Equals() method
          */
          // Rocks
          System.out.println("Testing equals() method for ROCKS");
          Rock r1 = new Rock("\tTestRock", 2);
          Rock r2 = new Rock("\tTestRock", 2);
          Rock r3 = new Rock("\tRock3", 2);

          System.out.println("\t" + r1.toString());
          System.out.println("\t" + r2.toString());
          System.out.println("\t" + r3.toString());

          System.out.println("\tIs Rock #1 equal to Rock #2? " + r1.equals(r2));
          System.out.println("\tIs Rock #1 equal to Rock #3? " + r1.equals(r3));
          System.out.println();

          // Pyrites - example for child classes
          System.out.println("Testing equals() method for PYRITES");
          Pyrite p1_test = new Pyrite("\tTestPyrite", 2, "youngPyrite");
          Pyrite p2_test = new Pyrite("\tTestPyrite", 2, "youngPyrite");
          Pyrite p3_test = new Pyrite("\tPyrite3", 2, "damnPyrite");

          System.out.println("\t" + p1_test.toString());
          System.out.println("\t" + p2_test.toString());
          System.out.println("\t" + p3_test.toString());

          System.out.println("\tIs Pyrite #1 equal to Pyrite #2? " + p1_test.equals(p2_test));
          System.out.println("\tIs Pyrite #1 equal to Pyrite #3? " + p1_test.equals(p3_test));
          System.out.println();

          /*
           * Testing array of Rocks
           */
          System.out.println("Testing array of Rocks");
          Rock rocks[] = new Rock[6]; // array size 6

          // Instantiate array with 6 objects (2 of each child)
          rocks[0] = new Bauxite("Bauxite1", 5, 45);
          rocks[1] = new Bauxite("Bauxite2", 10, 38);
          rocks[2] = new Diamond("Diamond1", 15, "FL", "Blue");
          rocks[3] = new Diamond("Diamond2", 20, "IF", "White");
          rocks[4] = new Pyrite("Pyrite1", 25, "p1");
          rocks[5] = new Pyrite("Pyrite2", 30, "p2");

          // variable to store largest weight
          double max_weight = -999;
          int rock_max_weight = 0;

          // Displaying the data of each object
          for (int i = 0; i < rocks.length; i++) {
               System.out.println("\tRock[" + i + "] : " + rocks[i].toString());

               // finding largest weight in array
               if (rocks[i].getWeight() >= max_weight) {
                    max_weight = rocks[i].getWeight();
                    rock_max_weight = i;
               }
          }

          System.out.println("\tLargest weight in the array: " + max_weight + "kg");
          System.out.println("\t" + rocks[rock_max_weight].toString());
          System.out.println();

          /*
           * Changing color of Diamond objects
           */

          int counter = 1; // keep track of only one diamond change, no more
          for (int i = 0; i < rocks.length; i++) {
               // checks if object is a diamond
               if (rocks[i] instanceof Diamond && counter == 1) {
                    Diamond d_temp = (Diamond) rocks[i]; // cast it to diamond
                    System.out.println("Changed color of one Diamond object");
                    System.out.println("\tPreviously: " + rocks[i].toString());
                    d_temp.setColor("Red"); // change color
                    System.out.println("\tAfter: " + d_temp.toString());
                    counter++;
               }
          }

     }

}
