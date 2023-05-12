package KOLYA;

public class Bauxite extends Rock implements Comparable<Bauxite> {

     // Variables
     private double percentage;

     // Default Constructor
     public Bauxite() {
          super();
          percentage = -1;
     }

     // Constructor
     public Bauxite(String name_p, double weight_p, double percentage_p) {
          super(name_p, weight_p);
          percentage = percentage_p;
     }

     // Getter
     public double getPercentage() {
          return percentage;
     }

     // Setter
     public void setPercentage(double percentage) {
          this.percentage = percentage;
     }

     @Override
     public String toString() {
          String result = "";
          result += super.toString();
          result += ", Percentage: " + String.format("%.2f", percentage) + "%";
          return result;
     }

     // overriding the compareTo() method to test percentage
     public int compareTo(Bauxite tempBauxite) {

          if(percentage < tempBauxite.percentage) {
               return -1; // less than
          } else if(percentage > tempBauxite.percentage) {
               return 1; // greater than
          } else {
               return 0; // equal
          }

     }

}
