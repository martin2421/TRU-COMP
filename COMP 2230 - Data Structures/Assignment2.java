public class Assignment2 {

     public static void main(String[] args) {

          // method to test a DropOutStack
          System.out.println("\nTesting Drop Out Stack\n");
          testDropOutStack();
          
          // method to test an ArrayStack
          System.out.println("\nTesting Array Stack\n");
          testArrayStack();

     }

     /*
      * User Defined Methods
      */

     public static void testDropOutStack() {

          DropOutStack<Character> drop = new DropOutStack<Character>(4);

          System.out.println("Pushing Elements to a DropStack with max capacity of 4");

          System.out.println("Pushing A...");
          drop.push('A');
          System.out.println(drop.toString());

          System.out.println("Pushing B...");
          drop.push('B');
          System.out.println(drop.toString());

          System.out.println("Pushing C...");
          drop.push('C');
          System.out.println(drop.toString());

          System.out.println("Pushing D...");
          drop.push('D');
          System.out.println(drop.toString());

          System.out.println("Pushing E...");
          drop.push('E');
          System.out.println(drop.toString());

          System.out.println("Pushing F...");
          drop.push('F');
          System.out.println(drop.toString());

     }

     public static void testArrayStack() {

          ArrayStack<String> names = new ArrayStack<String>(5);

          System.out.println("Testing push() method to push 2 names:");

          System.out.println("Pushing Fernando...");
          names.push("Fernando");
          System.out.println("Pushing Martin...");
          names.push("Martin");

          System.out.println("Testing toString() method:");
          System.out.println(names.toString());

          System.out.println("Testing peek() method to preview top name:");
          System.out.println(names.peek());

          System.out.println("Testing pop() method to get top name: ");
          System.out.println("Popped last element: " + names.pop());

          System.out.println("ArrayStack empty? " + names.isEmpty());
          System.out.println("Size of ArrayStack: " + names.size());

     }

}
