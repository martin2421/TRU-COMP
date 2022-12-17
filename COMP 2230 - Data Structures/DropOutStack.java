import java.util.Arrays;

public class DropOutStack<T> extends ArrayStack<T> {

     // global variables
     public int capacity;
     public T[] newStack;

     // default constructor
     public DropOutStack() {
          super();
     }

     // parameterized constructor
     public DropOutStack(int capacity) {
          super(capacity);
          this.capacity = capacity;
     }

     // overriding push method
     @Override
     public void push(T element) {

          int size = stack.length;

          if (size() == size) { // is size full?
               
               // copy array from [1] onwards
               newStack = Arrays.copyOfRange(stack, 1, size + 1); 
               newStack[top - 1] = element; // insert at last position
               stack = newStack; 

          } else {
               stack[top] = element; // insert at top position
               top++;
          }

     }

}