public class Second extends Thread {

     int id;

     Second(int id) {
          this.id = id;
     }
     
     public void run() {
          System.out.println("I am " + "'th worker thread.");
     }

     public static void main(String[] args) {
          
          Second runnerOne = new Second(1);
          Second runnerTwo = new Second(2);

          runnerOne.start();
          runnerTwo.start();

          System.out.println("I am the main thread.");

     }
}
