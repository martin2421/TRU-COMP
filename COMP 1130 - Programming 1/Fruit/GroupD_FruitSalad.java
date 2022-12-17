package Y1F.Fruit;

import java.util.Scanner;

public class GroupD_FruitSalad {

    // Enum 
    static enum TOPPINGS {
        WHIPPED_CREAM,
        CHOCOLATE,
        LEMON_JUICE
    }

    public static void main(String[] args) {

        // Variables
        Scanner console = new Scanner(System.in);
        double totalweight;
        String decisionStr;
        char decisionChar;
        int toppingNumber;
        int counter = 0;

        // Instantiation
        Kiwi kiwi = new Kiwi();
        Litchi litchi = new Litchi();
        Fig fig = new Fig();

        do {
            
        System.out.println("Welcome to Team D's Fruit Salad maker! Please enter how many grams of Fig you want in your salad:");
        fig.setWeight(console.nextDouble());            // Input weight for Fig

        System.out.println("How much Litchies would you like in your salad (in grams):");
        litchi.setWeight(console.nextDouble());         // Input weight for Litchi

        System.out.println("How much Kiwi would you like? (in grams):");
        kiwi.setWeight(console.nextDouble());           // Input weight for Kiwi

        // Total calculation
        totalweight = fig.getWeight() + litchi.getWeight() + kiwi.getWeight();

        // Error checking (Input Negative)
        if (kiwi.getWeight() < 0 || litchi.getWeight() < 0 || fig.getWeight() < 0) {
            System.out.println("Values can't be negative!");
        }

        // Conditional statement to output correct ingredients
        if (totalweight == 0) {
            System.out.println("You have no ingredients in your fruit salad :(");
        } else {
            System.out.print("Thank you for your order! Your fruit salad is ready. Total weight of your salad is " + totalweight + "g. \n Ingredients: ");

            if (kiwi.getWeight() > 0 && litchi.getWeight() > 0 && fig.getWeight() > 0) {            // All 3 fruits
                System.out.print("Kiwi, Litchi, Fig");                                              
            } else if (kiwi.getWeight() > 0 && litchi.getWeight() > 0 && fig.getWeight() == 0) {    // Only Kiwi and Litchi
                System.out.print("Kiwi, Litchi");
            } else if (kiwi.getWeight() > 0 && litchi.getWeight() == 0 && fig.getWeight() > 0) {    // Only Kiwi and Fig
                System.out.print("Kiwi, Fig");
            } else if (kiwi.getWeight() == 0 && litchi.getWeight() > 0 && fig.getWeight() > 0) {    // Only Litchi and Fig
                System.out.print("Litchi, Fig");
            } else if (kiwi.getWeight() > 0 && litchi.getWeight() == 0 && fig.getWeight() == 0) {   // Only Kiwi
                System.out.print("Kiwi");
            } else if (kiwi.getWeight() == 0 && litchi.getWeight() > 0 && fig.getWeight() == 0) {   // Only Litchi
                System.out.print("Litchi");
            } else if (kiwi.getWeight() == 0 && litchi.getWeight() == 0 && fig.getWeight() > 0) {   // Only Fig
                System.out.print("Fig");
            } 

            System.out.println("\nEnter the corresponding number for your topping:");
            System.out.println("1: Whipped Cream \t 2: Chocolate \t 3: Lemon Juice \t 4: No Topping");
            
            toppingNumber = console.nextInt(); // Input number for topping selection
            
            // Checks if topping selection is correct
            while (toppingNumber < 1 || toppingNumber > 4) {
                System.out.println("Wrong input. Please try again!");
                toppingNumber = console.nextInt();
            }
    
            // Outputs selected topping
            switch(toppingNumber) {
                case 1:
                  System.out.println("Selected topping is " + TOPPINGS.WHIPPED_CREAM);
                  break;
                case 2:
                  System.out.println("Selected topping is " + TOPPINGS.CHOCOLATE);
                  break;
                case 3:
                  System.out.println("Selected topping is " + TOPPINGS.LEMON_JUICE);
                  break;
                case 4:
                  System.out.println("No Selected topping");
                  break;
              }

        }

        System.out.println("Would you like another order? \t Type Y for yes, N for no");
        decisionStr = console.next();
        decisionStr = decisionStr.toUpperCase();
        decisionChar = decisionStr.charAt(0);
        counter++;

        } while (decisionChar == 'Y');

        System.out.println("You placed " + counter + " order(s). Thank you!");

        console.close();
    }
}
