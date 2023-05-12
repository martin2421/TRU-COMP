package KOLYA;

public class Pyrite extends Rock {

     // Variables
     private String aka;

     // Default Constructor
     public Pyrite() {
          super();
          aka = "No alias";
     }

     // Constructor
     public Pyrite(String name_p, double weight_p, String aka) {
          super(name_p, weight_p);
          this.aka = aka;
     }

     // Getter
     public String getAka() {
          return aka;
     }

     // Setter
     public void setAka(String aka) {
          this.aka = aka;
     }

     @Override
     public String toString() {
          String result = "";
          result += super.toString();
          result += ", (aka): " + aka;
          return result;
     }
 
}
