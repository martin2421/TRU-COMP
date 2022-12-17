/**
 * Child class. Contains a new attribute 'price' of type double. 
 */
public class Vegetable extends Plant {

     // Variable declaration
     private double price;

     // Default Constructor
     public Vegetable() {
          super();
          price = 0.00;
     }

     // Parameterized Constructor
     public Vegetable(String name, String type, String country, String howUsed,
               double price) {
          super(name, type, country, howUsed);
          this.price = price;
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
          return super.toString() + String.format("Price per Kg: $%.2f", price)
                    + "\n";
     }

    // equals() method
    public boolean equals(Object obj) {

     if (super.equals(obj)) { // null and self check

          if (!(obj instanceof Vegetable)) {
               return false;
          }

          Vegetable veg = (Vegetable) obj;
          return this.price == veg.getPrice();

     } else {
          return false;
     }

}

     // Getter
     public double getPrice() {
          return price;
     }

     // Setter
     public void setPrice(double price) {
          this.price = price;
     }

}
