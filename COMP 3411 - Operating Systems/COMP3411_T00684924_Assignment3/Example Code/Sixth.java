public class Sixth implements Runnable {

     int id;

     Sixth(int id) {
          this.id = id;
     }

     public void run() {

          while(true) {
               try {
                    Thread.sleep(1000); // sleep 1000 ms
               } catch (InterruptedException ie) { // if interrupted
                    System.out.println("I (" + id + "'th worker) am interrupted.");
                    break;
               }

               System.out.println("I am " + id + "'th worker thread.");

               // if interrupted
               if (Thread.currentThread().isInterrupted()) {
                    System.out.println("I (" + id + "'th worker) am interrupted.");
                    break;
               }
          }

     }

     public static void main(String[] args) {
          
          Runnable worker = new Sixth(1);
          Thread runnerOne = new Thread(worker);
          Thread runnerTwo = new Thread(new Sixth(2));

          runnerOne.start();
          runnerTwo.start();

          runnerOne.interrupt(); // stop() runnerOne; deprecated

          try {
               runnerOne.join();
          } catch (InterruptedException ie) { }

          System.out.println("The first worker done.");
          runnerTwo.interrupt(); // interrupt runnerTwo
          System.out.printf("I am the main thread.");

     }
     
}
