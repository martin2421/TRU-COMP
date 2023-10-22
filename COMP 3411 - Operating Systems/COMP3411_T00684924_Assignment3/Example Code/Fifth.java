public class Fifth implements Runnable {

     int id;

     Fifth(int id) {
          this.id = id;
     }

     public void run() {

          try {
               Thread.sleep(1000); // sleep 1000 ms
          } catch (InterruptedException ie) { }

          System.out.println("I am " + id + "'th worker thread.");
     }

     public static void main(String[] args) {
          
          Runnable worker = new Fifth(1);
          Thread runnerOne = new Thread(worker);
          Thread runnerTwo = new Thread(new Fifth(2));

          runnerOne.start();
          runnerTwo.start();

          try {
               runnerOne.join(); // wait until runnerOne terminates
          } catch (InterruptedException ie) { }

          System.out.println("The first worker done.");
          System.out.println("I am the main thread.");

     }

}
