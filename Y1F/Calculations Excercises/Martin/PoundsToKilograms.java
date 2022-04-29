package Martin;

// Zichen Wang (T00643137), Junhan Wang (T00643130), Martin Atanacio (T00684924)
// User inputs a value to be converted from Pounds to Kilograms

import java.util.Scanner;

public class PoundsToKilograms {

    static final double P2KG = 0.453592; // Conversion factor: 1 lb ~= 0.453592 kg

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // Input from console

        double inputPounds = 0.0;
        double resultKg = 0.0; 

        System.out.println("Pounds to Kilograms Converter \n");
        System.out.print("How many pounds? ");
        
        inputPounds = scan.nextDouble(); // Get value of user
        resultKg = inputPounds * P2KG; // Conversion formula

        // Display Results
        System.out.print("Converted: " + inputPounds + " lb" + " = ");
        System.out.println(resultKg + " kg");

        scan.close();

    }

}