public class First {
     public static void main(String[] args) {
          
          Worker runnerOne = new Worker(1); // create a thread
          Worker runnerTwo = new Worker(2); 

          runnerOne.start(); // start the thread
          runnerTwo.start();

          System.out.println("I am the main thread");

     }
}