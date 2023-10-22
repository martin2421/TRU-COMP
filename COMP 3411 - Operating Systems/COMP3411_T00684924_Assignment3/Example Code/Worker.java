public class Worker extends Thread {

     // not shared among threads; instance variable for an object i.e. thread
     int id;

     Worker(int id) {
          this.id = id;
     }

     // need to be implemented; starting point of threads, whenstart() is called
     public void run() {
          System.out.println("I am " + id + "'th worker thread");
     }

}