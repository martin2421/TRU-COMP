
public class Assignment5 {

     public static void main(String[] args) {

          // method to test a LinkedOrderList
          System.out.println("\nTesting Linked Ordered List\n");
          testLinkedOrderedList();

     }

     /*
      * User Defined Methods
      */

     public static void testLinkedOrderedList() {

          // creating new CircularArrayList object
          LinkedOrderedList<Character> list = new LinkedOrderedList<Character>();

          // Testing add() and toString() methods
          System.out.println("Adding five elements to the list:");

          System.out.println("Adding A...");
          list.add('A');
          System.out.println(list.toString());

          System.out.println("Adding B...");
          list.add('B');
          System.out.println(list.toString());

          System.out.println("Adding C...");
          list.add('C');
          System.out.println(list.toString());
          
          System.out.println("Adding D...");
          list.add('D');
          System.out.println(list.toString());
          
          System.out.println("Adding E...");
          list.add('E');
          System.out.println(list.toString());

          // Testing isEmpty() and size() methods
          System.out.println("Is List empty?: " + list.isEmpty());

          System.out.println("Size of List: " + list.size() + " elements.");

          // Testing first(), last(), and contains() methods
          System.out.println("First element in List: " + list.first());
          
          System.out.println("Last element in List: " + list.last());

          System.out.println("Does list contains 'C'? " + list.contains('C'));
          System.out.println("Does list contains 'F'? " + list.contains('F'));

          // Testing removeFirst(), removeLast(), and remove() methods
          System.out.println("Removing first element...");
          System.out.println("Removing: " + list.removeFirst());
          System.out.println(list.toString());
          
          System.out.println("Removing last element...");
          System.out.println("Removing: " + list.removeLast());
          System.out.println(list.toString());
          
          System.out.println("Removing 'C' ...");
          System.out.println("Removing: " + list.remove('C'));
          System.out.println(list.toString());

     }

}
