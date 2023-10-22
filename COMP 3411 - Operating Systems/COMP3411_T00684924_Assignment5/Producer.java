/*
 * Author: Martin Atanacio - T00684924
 * Date: Aug 2023
 */

// Producer Thread Class
public class Producer implements Runnable {

     // Variables
     private BoundedBuffer buffer; // producer will be instantiated with a shared buffer

     // Default Constructor
     public Producer(BoundedBuffer buffer) {
          this.buffer = buffer;
     }

     // Override run() method
     @Override
     public void run() {
          try {
               int value = 0; // data to be inserted into the buffer
               while (true) { // infinite loop, producer will produce forever (until interrupt)
                    buffer.produce(value); // inserts current value into the buffer
                    value++; // increment the value
                    Thread.sleep(500); // 0.5 second delay between item production
               }
          } catch (InterruptedException e) {
               Thread.currentThread().interrupt(); // gracefully terminate the thread
          }
     }
}