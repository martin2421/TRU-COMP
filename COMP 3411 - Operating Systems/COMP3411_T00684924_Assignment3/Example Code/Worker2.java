public class Worker2 implements Runnable {

     int id;

     Worker2(int id) {
          this.id = id;
     }

     public void run() {
          System.out.println("I am " + id + "'th worker thread.");
     }

}