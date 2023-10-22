public class Seventh implements Runnable {

     static int common; // shared among objects i.e. threads
     int id;

     Seventh(int id) {
          this.id = id;
          common = id;
     }

     public void run() {
          System.out.println("I am " + id + "'th worker thread - " + common);
     }

     public static void main(String[] args) {
          
          Thread runnerOne = new Thread(new Seventh(1));
          Thread runnerTwo = new Thread(new Seventh(2));

          runnerOne.start();
          runnerTwo.start();

          System.out.println("I am the main thread.");

     }
     
}
