
public class Assignment4 {

     public static void main(String[] args) {

          // method to test a LinkedQueue
          System.out.println("\nTesting Linked Queue\n");
          testLinkedQueue();

          // method to test an CircularArrayQueue
          System.out.println("\nTesting Circular Array Queue\n");
          testCircularArrayQueue();

     }

     /*
      * User Defined Methods
      */

     public static void testLinkedQueue() {

          // creating new LinkedQueue object
          LinkedQueue<Character> linkedQ = new LinkedQueue<Character>();

          // Testing enqueue() and toString() methods
          System.out.println("Adding three elements to the Queue:");

          System.out.println("Enqueuing A...");
          linkedQ.enqueue('A');
          System.out.println(linkedQ.toString());

          System.out.println("Enqueuing B...");
          linkedQ.enqueue('B');
          System.out.println(linkedQ.toString());

          System.out.println("Enqueuing C...");
          linkedQ.enqueue('C');
          System.out.println(linkedQ.toString());

          // Testing isEmpty() and size() methods
          System.out.println("Is Queue empty?: " + linkedQ.isEmpty());

          System.out.println("Size of Queue: " + linkedQ.size() + " elements.");

          // Testing dequeue() and first() methods
          System.out.println("First element in Queue: " + linkedQ.first());

          System.out.println("Dequeuing first element...");
          System.out.println("Removing: " + linkedQ.dequeue());
          System.out.println(linkedQ.toString());

     }

     public static void testCircularArrayQueue() {

          // creating new CircularArrayQueue object
          CircularArrayQueue<Character> circularQ = new CircularArrayQueue<Character>(5);

          // Testing enqueue() and toString() methods
          System.out.println("Adding three elements to the Queue:");

          System.out.println("Enqueuing A...");
          circularQ.enqueue('A');
          System.out.println(circularQ.toString());

          System.out.println("Enqueuing B...");
          circularQ.enqueue('B');
          System.out.println(circularQ.toString());

          System.out.println("Enqueuing C...");
          circularQ.enqueue('C');
          System.out.println(circularQ.toString());
          
          // Testing isEmpty() and size() methods
          System.out.println("Is Queue empty?: " + circularQ.isEmpty());
          
          System.out.println("Size of Queue: " + circularQ.size() + " elements.");
          
          // Testing dequeue() and first() methods
          System.out.println("First element in Queue: " + circularQ.first());
          
          System.out.println("Dequeuing first element...");
          System.out.println("Removing: " + circularQ.dequeue());
          System.out.println(circularQ.toString());
          
          // Testing enquing more items and resizing queue
          System.out.println("Enqueuing D...");
          circularQ.enqueue('D');
          System.out.println(circularQ.toString());
          
          System.out.println("Enqueuing E...");
          circularQ.enqueue('E');
          System.out.println(circularQ.toString());
          
          System.out.println("Enqueuing F...");
          circularQ.enqueue('F');
          System.out.println(circularQ.toString());
          
          System.out.println("Enqueuing G...");
          circularQ.enqueue('G');
          System.out.println(circularQ.toString());
          
          System.out.println("Size of Queue: " + circularQ.size() + " elements.");
          
     }
     
}
