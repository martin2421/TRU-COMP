package Martin;

// Zichen Wang (T00643137), Junhan Wang (T00643130), Martin Atanacio (T00684924)
// User inputs 2 values and program will calculate basic math operations

import java.util.Scanner;

public class Calculations {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // Input from console

        float a = 0;
        float b = 0;

        System.out.println("Please enter two values.");

        System.out.print("Value A: ");
        a = scan.nextFloat(); // Get value of A

        System.out.print("Value B: ");
        b = scan.nextFloat(); // Get value of B

        System.out.println("\n The calculations for " + a + " and " + b + ":");

        // Display Results
        System.out.println("\t" + a + " + " + b + " = " + (a + b)); // Addition
        System.out.println("\t" + a + " - " + b + " = " + (a - b)); // Difference
        System.out.println("\t" + a + " * " + b + " = " + (a * b)); // Multiplication
        System.out.println("\t" + a + " / " + b + " = " + (a / b)); // Division
        System.out.println("\t" + a + " % " + b + " = " + (a % b)); // Modulus

        scan.close();

    }
    
}
