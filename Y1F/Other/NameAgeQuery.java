package Y1F.Other;

import java.util.Scanner;

public class NameAgeQuery {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // Declare program storage in variables
        String name = "";
        int age = 0;

        System.out.print("What is your name? ");
        name = scan.nextLine(); // Input

        System.out.print("How old are you (years)? ");
        age = scan.nextInt(); // Input

        age += 10; // Process/Calculation

        // Output
        System.out.println("Well, " + name + ", in 10 years you will be " + age + " years old");
        
        scan.close();

    }

}
