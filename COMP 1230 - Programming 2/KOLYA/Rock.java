package KOLYA;

public class Rock {

     // Variables
     private String name;
     private double weight;

     // Default Constructor
     public Rock() {
          name = "No name";
          weight = -1;
     }

     // Constructor
     public Rock(String name_p, double weight_p) {
          name = name_p;
          weight = weight_p;
     }

     // Getters
     public String getName() {
          return name;
     }

     public double getWeight() {
          return weight;
     }

     // Setters
     public void setName(String name) {
          this.name = name;
     }

     public void setWeight(double weight) {
          this.weight = weight;
     }

     @Override
     public String toString() {
          return "Name: " + name + ", Weight: " + weight + "kg";
     }

     // Equals method
     public boolean equals(Object o) {
          if (this == o) // self check
               return true;
          if (o == null) // null check
               return false;
          if (!(o instanceof Rock)) // type check and cast
               return false;

          Rock r = (Rock) o;

          return this.name.equals(r.name) && this.weight == r.weight;

     }

}