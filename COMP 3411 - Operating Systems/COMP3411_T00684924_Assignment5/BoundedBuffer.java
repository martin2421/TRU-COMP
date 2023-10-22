/*
 * Author: Martin Atanacio - T00684924
 * Date: Aug 2023
 */
import java.util.concurrent.Semaphore;

// Shared Buffer Object Class
public class BoundedBuffer {

     // Variables
     private int[] buffer; // shared buffer array
     private int size; // size of buffer array
     private int in; // index from where producer will add an item
     private int out; // index from where consumer will remove an item
     private Semaphore mutex; // binary semaphore used to acquire/release lock
     private Semaphore empty; // counting semaphore, initial value is set to buffer size
     private Semaphore full; // counting semaphore, initial value is set to 0

     // Default Constructor
     public BoundedBuffer(int capacity) {
          size = capacity; // size of buffer = capacity given
          buffer = new int[size]; // sets the size of the buffer
          in = 0; // initially, first element will go in index 0
          out = 0; // initially, last element will be removed from index 0
          mutex = new Semaphore(1); // mutex (lock) to protect critical sections
          empty = new Semaphore(size); // semaphore to track # of empty slots
          full = new Semaphore(0); // semaphore to track # of full slots 
     }

     // Methods
     public void produce(int value) throws InterruptedException {
          empty.acquire(); // wait until empty > 0 and then decrement empty
          mutex.acquire(); // acquire the mutex lock to protect buffer from being modified

          // equivalent to an atomic operation - mutually exclusive event
          buffer[in] = value; // store value in its corresponding position
          in = (in + 1) % size; // point to next position, otherwise circle back to first position
          System.out.println("Item Produced: " + value);
          
          mutex.release(); // release the mutex lock
          full.release(); // signal that a new slot is now full
     }
     
     public int consume() throws InterruptedException {
          full.acquire(); // wait until full > 0 and then decrement full
          mutex.acquire(); // acquire the mutex lock to protect buffer from being modified
          
          // equivalent to an atomic operation - mutually exclusive event
          int value = buffer[out]; // store the value that has been consumed
          out = (out + 1) % size; // point to next position, otherwise circle back to first position
          System.out.println("Item Consumed: " + value);

          mutex.release(); // release the mutex lock
          empty.release(); // signal that a slot is now empty

          return value; // returns value in case of future usage
     }
}