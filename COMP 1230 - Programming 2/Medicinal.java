/**
 * Child class. Contains a new attribute 'structure' of type String.
 */
public class Medicinal extends Plant {

     // Variable declaration
     private String structure;

     // Default Constructor
     public Medicinal() {
          super();
          structure = "EMPTY";
     }

     // Parameterized Constructor
     public Medicinal(String name, String type, String country, String howUsed,
               String structure) {
          super(name, type, country, howUsed);
          this.structure = structure;
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
          return super.toString() + "Structure: " + structure + "\n";
     }

     // equals() method
     public boolean equals(Object obj) {

          if (super.equals(obj)) { // null and self check

               if (!(obj instanceof Medicinal)) {
                    return false;
               }

               Medicinal med = (Medicinal) obj;
               return this.structure.equals(med.getStructure());

          } else {
               return false;
          }

     }

     // Getter
     public String getStructure() {
          return structure;
     }

     // Setter
     public void setStructure(String structure) {
          this.structure = structure;
     }

}
