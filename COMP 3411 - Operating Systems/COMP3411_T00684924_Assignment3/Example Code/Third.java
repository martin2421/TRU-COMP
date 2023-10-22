public class Third {

     public static void main(String[] args) {
          
          Runnable worker = new Worker2(1);
          Thread runnerOne = new Thread(worker);
          
          worker = new Worker2(2);
          Thread runnerTwo = new Thread(worker);

          runnerOne.start();
          runnerTwo.start();

          System.out.println("I am the main thread.");

     }
     
}
