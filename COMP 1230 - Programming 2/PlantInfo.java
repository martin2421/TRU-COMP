/**
 * Interface with abstract methods country and howUsed. All Plant objects will be 
 * required to fill the content for abstract methods. 
 */
public interface PlantInfo {

     /**
      * Abstract methods country and howUsed. Designed so each child class writes
      * their own code
      */
     abstract String country(); // returns country of origin

     abstract String howUsed(); // returns how the object is being used

}
