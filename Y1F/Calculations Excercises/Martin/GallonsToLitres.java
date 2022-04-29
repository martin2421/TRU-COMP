package Martin;

// Zichen Wang (T00643137), Junhan Wang (T00643130), Martin Atanacio (T00684924)
// User inputs a value to be converted from Gallons to Litres

import java.util.Scanner;

public class GallonsToLitres {

    static final double GAL2LT = 3.78541; // Conversion factor: 1 gal ~= 3.78541 litres

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // Input from console

        double inputGallons = 0.0;
        double resultLitres = 0.0; 

        System.out.println("Gallons to Litres Converter \n");
        System.out.print("How many gallons? ");
        
        inputGallons = scan.nextDouble(); // Get value of user
        resultLitres = inputGallons * GAL2LT; // Conversion formula

        // Display Results
        System.out.print("Converted: " + inputGallons + " gal" + " = ");
        System.out.println(resultLitres + " litres");

        scan.close();

    }

}