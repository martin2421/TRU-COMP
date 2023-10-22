/*
 * Author: Martin Atanacio - T00684924
 * Date: Aug 2023
 */

// Consumer Thread Class
public class Consumer implements Runnable {

     // Variables
     private BoundedBuffer buffer; // consumer will be instantiated with a shared buffer

     // Default Constructor
     public Consumer(BoundedBuffer buffer) {
          this.buffer = buffer;
     }

     // Override run() method
     @Override
     public void run() {
          try {
               while (true) { // infinite loop, consumer will consume forever (until interrupt)
                    buffer.consume(); // consumer removes an item from the buffer
                    Thread.sleep(1000); // 1 second delay between item consumption
               }
          } catch (InterruptedException e) {
               Thread.currentThread().interrupt(); // gracefully terminate the thread
          }
     }
}