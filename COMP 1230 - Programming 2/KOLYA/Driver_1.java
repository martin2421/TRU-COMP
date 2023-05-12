package KOLYA;

public class Driver_1 {

     public static void main(String[] args) {
          
          // Creation of Rock, Diamond, and Bauxite objects
          Rock r1 = new Rock();
          Rock r2 = new Rock("Rock1", 69.420);
          
          Diamond d1 = new Diamond();
          Diamond d2 = new Diamond("Diamond1", 3.5, "FL", "Blue");

          Bauxite b1 = new Bauxite();
          Bauxite b2 = new Bauxite("Bauxite1", 7.8, 99.9);

          // Rock Output
          System.out.println("Default Rock");
          System.out.println("\tName: " + r1.getName());
          System.out.println("\tWeight: " + r1.getWeight() + "kg");
          
          System.out.println();
          System.out.println("Parameterized Rock");
          System.out.println("\tName: " + r2.getName());
          System.out.println("\tSetting name to NEW-ROCK...");
          r2.setName("NEW-ROCK");
          System.out.println("\tName: " + r2.getName());
          System.out.println("\tWeight: " + r2.getWeight() + "kg");
          System.out.println("Setting weight to 3kg...");
          r2.setWeight(3);
          System.out.println("\tWeight: " + r2.getWeight() + "kg");
          System.out.println("\tTesting toString() method...");
          System.out.println("\tRock 1:\t" + r1.toString());
          System.out.println("\tRock 2:\t" + r2.toString());
          System.out.println();

          // Diamond Output
          System.out.println("Default Diamond");
          System.out.println("\tName: " + d1.getName());
          System.out.println("\tWeight: " + d1.getWeight() + "kg");
          System.out.println("\tClarity: " + d1.getClarity());
          System.out.println("\tColor: " + d1.getColor());
          
          System.out.println();
          System.out.println("Parameterized Diamond");
          System.out.println("\tName: " + d2.getName());
          System.out.println("\tSetting name to NEW-DIAMOND...");
          d2.setName("NEW-DIAMOND");
          System.out.println("\tName: " + d2.getName());
          System.out.println("\tWeight: " + d2.getWeight() + "kg");
          System.out.println("\tSetting weight to 5kg...");
          d2.setWeight(5);
          System.out.println("\tWeight: " + d2.getWeight() + "kg");
          
          System.out.println("\tClarity: " + d2.getClarity());
          System.out.println("\tSetting clarity to IF...");
          d2.setClarity("IF");
          System.out.println("\tClarity: " + d2.getClarity());
          System.out.println("\tColor: " + d2.getColor());
          System.out.println("\tSetting color to White...");
          d2.setColor("White");
          System.out.println("\tColor: " + d2.getColor());
          System.out.println("\tTesting toString() method...");
          System.out.println("\tDiamond 1: " + d1.toString());
          System.out.println("\tDiamond 2: " + d2.toString());
          System.out.println();

          //Bauxite
          System.out.println("Default Bauxite");
          System.out.println("\tName: " + b1.getName());
          System.out.println("\tWeight: " + b1.getWeight() + "kg");
          System.out.println("\tPercentage: " + b1.getPercentage() + "%");
          
          System.out.println();
          System.out.println("Parameterized Bauxite");
          System.out.println("\tName: " + b2.getName());
          System.out.println("\tSetting name to NEW-BAUXITE...");
          b2.setName("NEW-BAUXITE");
          System.out.println("\tName: " + b2.getName());
          System.out.println("\tWeight: " + b2.getWeight() + "kg");
          System.out.println("\tSetting weight to 16kg...");
          b2.setWeight(16);
          System.out.println("\tWeight: " + b2.getWeight() + "kg");
          
          System.out.println("\tPercentage: " + b2.getPercentage() + "%");
          System.out.println("\tSetting % to 88.8...");
          b2.setPercentage(88.8);
          System.out.println("\tPercentage: " + b2.getPercentage() + "%");
          System.out.println("\tTesting toString() method...");
          System.out.println("\tBauxite 1: " + b1.toString());
          System.out.println("\tBauxite 2: " + d2.toString());
          System.out.println();


     }
     
}
