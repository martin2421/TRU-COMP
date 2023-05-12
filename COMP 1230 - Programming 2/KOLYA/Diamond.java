package KOLYA;

public class Diamond extends Rock {
     
     // Variables
     private String clarity;
     private String color;
     
     // Default Constructor
     public Diamond() {
          super();
          clarity = "No clarity";
          color = "No color";
     }

     // Constructor
     public Diamond(String name_p, double weight_p, String clarity_p, String color_p) {
          super(name_p, weight_p);
          clarity = clarity_p;
          color = color_p;
     }

     // Getters
     public String getClarity() {
          return clarity;
     }
     public String getColor() {
          return color;
     }
    
     // Setters
     public void setClarity(String clarity) {
          this.clarity = clarity;
     }
     public void setColor(String color) {
          this.color = color;
     }

     @Override
     public String toString() {
          String result = "";
          result += super.toString();
          result += ", Clarity: " + clarity + ", Color: " + color;
          return result;
     }

}
