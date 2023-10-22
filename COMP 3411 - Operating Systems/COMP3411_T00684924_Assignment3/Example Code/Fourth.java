public class Fourth implements Runnable { 

     int id;

     Fourth(int id) {
          this.id = id;
     }

     public void run() {
          System.out.println("I am " + id + "'th worker thread.");
     }

     public static void main(String[] args) {
          
          Runnable worker = new Fourth(1);
          Thread runnerOne = new Thread(worker);
          Thread runnerTwo = new Thread(new Fourth(2));

          runnerOne.start();
          runnerTwo.start();

          System.out.println("I am the main thread.");

     }
     
}
