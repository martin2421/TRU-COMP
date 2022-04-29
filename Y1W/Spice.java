/**
 * Child class. Contains a new attribute 'part' of type String.
 */
public class Spice extends Plant {

     // Variable Declaration
     private String part;

     // Default Constructor
     public Spice() {
          super();
          part = "EMPTY";
     }

     // Parameterized Constructor
     public Spice(String name, String type, String country, String howUsed,
               String part) {
          super(name, type, country, howUsed);
          this.part = part;
     }

     // Abstract Methods
     public String country() {
          return "Country of origin: " + getCountry() + "\n";
     }

     public String howUsed() {
          return "How its used: " + getHowUsed() + "\n";
     }

     // toString() method
     @Override
     public String toString() {
          return super.toString() + "Part of plant used: " + part + "\n";
     }

     // Getters
     public String getPart() {
          return part;
     }

     // Setters
     public void setPart(String part) {
          this.part = part;
     }

}
