package KOLYA;

public class TestBauxite {

     public static void main(String[] args) {

          Bauxite b1 = new Bauxite("Bauxite1", 80, 75);
          Bauxite b2 = new Bauxite("Bauxite2", 80, 75);
          Bauxite b3 = new Bauxite("Bauxite3", 80, 55);
          Bauxite b4 = new Bauxite("Bauxite4", 80, 95);

          // Printing all objects
          System.out.println("Bauxite objects: ");
          System.out.println("\t" + b1.toString());
          System.out.println("\t" + b2.toString());
          System.out.println("\t" + b3.toString());
          System.out.println("\t" + b4.toString());

          // Testing the compareTo() for all 3 outcomes
          System.out.print("\nBauxite1 compareTo Bauxite2:");
          System.out.println(" " + b1.compareTo(b2) + " (equal)");

          System.out.print("Bauxite1 compareTo Bauxite3:");
          System.out.println(" " + b1.compareTo(b3) + " (greater than)");

          System.out.print("Bauxite1 compareTo Bauxite4:");
          System.out.println(" " + b1.compareTo(b4) + " (less than)");
          System.out.println();

          // Creates an array of 10 Bauxites
          Bauxite[] bauxites = new Bauxite[10];

          // creates 10 bauxite objects with random percentage
          for (int i = 0; i < bauxites.length; i++) {
               bauxites[i] = new Bauxite("Bauxite" + (i + 1), 65, Math.random() * 100); // 10 objects with random percentage
          }

          // calls the toString() of every object
          System.out.println("Array of 10 Bauxite objects:");
          for (int i = 0; i < bauxites.length; i++) {
               System.out.println("\t" + bauxites[i].toString());
          }

          Bauxite maxBauxite = bauxites[0]; // assume max
          Bauxite minBauxite = bauxites[0]; // assume min

          for (int i = 1; i < bauxites.length; i++) {
               if (bauxites[i].compareTo(maxBauxite) > 0) {
                    maxBauxite = bauxites[i];
               }
               if (bauxites[i].compareTo(minBauxite) < 0) {
                    minBauxite = bauxites[i];
               }
          }

          System.out.println();
          System.out.println("Bauxite with the highest percentage:");
          System.out.println("\t" + maxBauxite.toString()); 
          
          System.out.println("Bauxite with the lowest percentage:");
          System.out.println("\t" + minBauxite.toString());          

     }

}
