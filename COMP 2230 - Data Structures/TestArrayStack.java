/****    
 * Name: Martin Atanacio
 * Student Number: T00684924
 * Assignment Number: 2
 ****/

import java.util.Scanner;

public class TestArrayStack {

     public static void main(String[] args) {

          final String flag = "STOP"; // flag to stop program
          String input = ""; // store input from user
          char tempChar; // character to be processed
          char poppedChar; // character to be popped
          boolean balanced = false; // keeps track if parentheses are balanced

          ArrayStack<Character> stack;
          Scanner console = new Scanner(System.in);

          while (!(input.equals(flag))) {

               // clears result from previous stack
               stack = new ArrayStack<Character>();

               System.out.println("\nType \"STOP\" to quit the program.\n" +
                         "Input a string of any type of brackets:");
               input = console.nextLine();

               for (int i = 0; i < input.length(); i++) {

                    // temporary stores current character to be processed
                    tempChar = input.charAt(i);

                    // throws EmptyCollectionException if attempting to pop an
                    // element in an empty stack
                    try {

                         switch (tempChar) {

                              // opening brackets - push it into stack
                              case '(':
                              case '[':
                              case '{':
                                   System.out.println("Pushing '" + tempChar + "'");
                                   stack.push(tempChar);
                                   break;

                              // closing brackets - pop top element
                              case ')':
                                   poppedChar = stack.pop();
                                   System.out.println("Popped '" + poppedChar +
                                             "', after finding closing bracket '" +
                                             tempChar + "'");

                                   // if poppedChar does not equal to its
                                   // corresponding opening bracket, balanced=false
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

                         // catches exception if attempting to pop() from an empty
                         // collection
                    } catch (EmptyCollectionException empty) {
                         System.out.println("\n" + empty + "\n");
                         balanced = false;
                         break;
                    }

               }

               // A balanced stack would imply that it is empty and it has been
               // calculated by the program to be balanced
               if (stack.isEmpty() && balanced) {
                    System.out.println("CORRECT! Parentheses are balanced.");
                    System.out.println("----------------------------------------");
               } else {
                    System.out.println("WRONG! Parentheses are NOT balanced.");
                    System.out.println("----------------------------------------");
               }

          }

          console.close();

     }

}
