package Y1F.Other;

public class Mistakes {

    public static void main(String[] args) {

        // Excercise 1
        System.out.println("Martin and Tommy's playing around program.");
        System.out.println("2+2 equals " + (2 + 2));    // Prints out 4
        System.out.println("Whut iz in .printlyn duzznt haf 2 bee write");

        // Question 1
        System.out.println("Martin and Tommy's playing around program.");
        System.out.println("2+2 equals " + 2 + 2);      // Prints out 22
        System.out.println("Whut iz in .printlyn duzznt haf 2 bee write");

        /*
         * 
         * Question 2 
         *      changing 'main()' to 'first()' won't compile/run the program
         * 
         * Question 3 
         *      removing 'public' won't compile/run the program Compile-time
         *      errors are generally referred to the error corresponding to syntax or
         *      semantics. Runtime errors on the other hand refer to the error encountered
         *      during the execution of code at runtime.
         * 
         * Question 4 
         *      changing the 'S' to a lower case in 'String' won't compile/run the
         *      program
         * 
         * Question 5 
         *      changing the 'S' to a lower case in 'Syste.out.println()' won't
         *      compile/run the program Error message: system cannot be resolved
         * 
         * Question 6 
         *      removing a " won't compile/run the program Error message: String
         *      literal is not properly closed by a double-quote
         * 
         * Question 7 
         *      Java only cares about the spelling in the syntax
         * 
         */

        // Question 8
        System.out.println("Martin and Tommy's playing around program.");
        System.out.println("2+2 equals " + (2 + 2));
        System.out.println("Whut iz in .printlyn duzznt haf 2 bee write");

        /*
         * Using indentation and different lines in our code will make it easier to read
         * by other programmers, but Java doesn't care, as long as the usage of the ';'
         * is correct.
         * 
         * 
         * Excercise 2 
         * Works: 
         *      a, b, d, e, f, g, j, m, 
         * Won't Work: 
         *      c (a number can't be first), h (has spaces), i (has a '-'), k (has an '@'), 
         *      l ('public' is a reserved Java word), n (uses the '.' operator), o ('class' is a reserved Java word)
         * 
         */

    }

}
