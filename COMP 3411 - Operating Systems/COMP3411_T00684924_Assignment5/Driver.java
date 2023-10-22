/*
 * Author: Martin Atanacio - T00684924
 * Date: Aug 2023
 */

public class Driver {
     public static void main(String[] args) {

          // new instance of a shared buffer set to size 5
          BoundedBuffer buffer = new BoundedBuffer(5);

          // new instance of producer and consumer threads
          // created using runnable objects
          Thread producerThread = new Thread(new Producer(buffer));
          Thread consumerThread = new Thread(new Consumer(buffer));

          // start the threads - run their run() methods
          producerThread.start(); // producer will produce forever
          consumerThread.start(); // consumer will consume forever

          // allows the threads to run for some time
          try {
               Thread.sleep(10000); // threads will run for 10 seconds only
          } catch (InterruptedException e) {
               e.printStackTrace();
          }

          // stop the threads gracefully
          producerThread.interrupt();
          consumerThread.interrupt();

     }
}
