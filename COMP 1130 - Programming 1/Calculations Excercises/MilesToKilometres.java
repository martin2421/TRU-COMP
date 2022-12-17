package Martin;

// Zichen Wang (T00643137), Junhan Wang (T00643130), Martin Atanacio (T00684924)
// User inputs a value to be converted from Miles to Kilometres

import java.util.Scanner;

public class MilesToKilometres {

    static final double M2KM = 1.60935; // Conversion factor: 1 mile ~= 1.60935 km

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // Input from console

        double inputMiles = 0.0;
        double resultKm = 0.0; 

        System.out.println("Miles to Kilometres Converter \n");
        System.out.print("How many miles? ");
        
        inputMiles = scan.nextDouble(); // Get value of user
        resultKm = inputMiles * M2KM; // Conversion formula

        // Display Results
        System.out.print("Converted: " + inputMiles + " miles" + " = ");
        System.out.println(resultKm + " km");

        scan.close();

    }

}