package KOLYA;

import java.util.Scanner;

public class TestArrayStack {

     public static void main(String[] args) {

          final String flag = "STOP"; // value to stop program
          String input = ""; 
          char tempChar; // character to be processed
          char poppedChar; // character to be popped
          boolean balanced = false;

          ArrayStack<Character> stack;
          Scanner console = new Scanner(System.in);

          while (!(input.equals(flag))) {

               // clears result from previous stack
               stack = new ArrayStack<Character>();

               System.out.println("\nWrite \"STOP\" to stop the program.\n" +
                         "Input a string of brackets:");
               input = console.nextLine();

               for (int i = 0; i < input.length(); i++) {

                    // current character to be processed
                    tempChar = input.charAt(i);

                    // throws EmptyCollectionException if popping an element from an empty stack
                    try {

                         switch (tempChar) {

                              // opening brackets - push it to the stack
                              case '(':
                              case '[':
                              case '{':
                                   System.out.println("Pushing '" + tempChar + "'");
                                   stack.push(tempChar);
                                   break;

                              // closing brackets - pop the top element
                              case ')':
                                   poppedChar = stack.pop();
                                   System.out.println("Popped '" + poppedChar +
                                             "', after finding closing bracket '" +
                                             tempChar + "'");

                                   if (poppedChar != '(') {
                                        System.out.println(tempChar + " and " +
                                                  poppedChar + " do not match");
                                        balanced = false;
                                   } else {
                                        balanced = true;
                                   }
                                   break;

                              case ']':
                                   poppedChar = stack.pop();

                                   System.out.println("Popped '" + poppedChar +
                                             "', after finding closing bracket '" +
                                             tempChar + "'");
                                   if (poppedChar != '[') {
                                        System.out.println(tempChar + " and " +
                                                  poppedChar + " do not match");
                                        balanced = false;
                                   } else {
                                        balanced = true;
                                   }
                                   break;

                              case '}':
                                   poppedChar = stack.pop();

                                   System.out.println("Popped '" + poppedChar +
                                             "', after finding closing bracket '" +
                                             tempChar + "'");
                                   if (poppedChar != '{') {
                                        System.out.println(tempChar + " and " +
                                                  poppedChar + " do not match");
                                        balanced = false;
                                   } else {
                                        balanced = true;
                                   }
                                   break;

                              default:
                                   break;

                         }

                         // exception if attempting to pop() from an empty collection
                    } catch (EmptyCollectionException empty) {
                         System.out.println("\n" + empty + "\n");
                         balanced = false;
                         break;
                    }

               }

               // balanced stack == empty stack
               if (stack.isEmpty() && balanced) {
                    System.out.println("Parentheses are BALANCED.");
                    System.out.println("******************************");
               } else {
                    System.out.println("Parentheses are NOT balanced.");
                    System.out.println("******************************");
               }

          }

          console.close();

     }

}
