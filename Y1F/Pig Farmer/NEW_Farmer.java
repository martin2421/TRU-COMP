import java.util.Scanner;
import java.time.LocalDate;

public class NEW_Farmer {

    static Scanner console = new Scanner(System.in);
    static String id, name, sex, color, feedType, pigList;
    static int year, month, day, numBin;
    static double weight;
    static PigCollection pigCollection = new PigCollection();
    static FeedCollection feedCollection = new FeedCollection();

    public static void main(String[] args) {

        // Variables
        int action;

        do {

            displayMenu();

            // Input & Error Checking
            do {
                while (!console.hasNextInt()) {
                    System.out.println("Whoops! Try that again...");
                    System.out.print("Action: ");
                    console.nextLine();
                }
                action = console.nextInt();
                if (action < 0 || action > 5) {
                    do {
                        System.out.println("Action not found. Try again...");
                        System.out.print("Action: ");
                        action = console.nextInt();
                    } while (action < 0 || action > 5);
                }
            } while (action < 0);

            // Action to do
            switch (action) {
            case 1:
                // Create Pig and add it to PigCollection
                getPigInfo();
                Pig newPig = new Pig(id, name, sex.charAt(0), color, LocalDate.of(year, month, day));
                pigCollection.addPig(newPig);
                System.out.println("Pig has been added!");
                System.out.println("ID: " + newPig.getPigID() + "\tName: " + newPig.getName() + "\tSex: "
                        + newPig.getSex() + "\tColor: " + newPig.getColor() + "\tDate of Birth: " + newPig.getDate());
                break;

            case 2:
                // Ask feed info & add it to FeedCollection
                getFeedInfo();
                Feed newFeed = new Feed(feedType);
                feedCollection.setFeedNumber(numBin, newFeed);
                System.out.println("Feed has been added at " + numBin);
                break;

            case 3:
                // Iterate through all pigs and feed them with an individual bin
                if (pigCollection.isEmpty()) {
                    System.out.println("Sorry, there are no pigs! Go back and add one...");
                } else {
                    for (int i = 0; i < pigCollection.getPigCount(); i++) {
                        Feed tempfeed = feedCollection.getNextFullBin();
                        System.out.println(feedCollection.getNumberOfFullBins() + " bin(s) available.");
                        if (tempfeed != null) {
                            pigCollection.getPigArray()[i].feed(tempfeed);
                            System.out.println("Bin used!");
                        } else {
                            System.out.println("No more bins are left! Go back and add one...");
                            break;
                        }
                    }
                }
                break;

            case 4:
                // Report # of available bins & # of Pigs in PigCollection
                System.out.println("There are " + feedCollection.getSize() + " bins available");
                pigList = pigCollection.getPigList();
                System.out.println("Pigs: " + pigList);
                break;

            case 5:
                // Exit
                System.out.println("Thank you, goodbye!");
                break;
            }

            System.out.println();

        } while (action != 5);

    }

    // Display Menu
    public static void displayMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Add Pig\n2. Add Feed\n3. Feed Pigs\n4. Report\n5. Exit");
        System.out.print("Action: ");

    }

    // Gets input from user about the pig
    public static void getPigInfo() {
        System.out.println("ID: ");
        id = console.next();
        System.out.println("Name: ");
        name = console.next();
        System.out.println("Sex: ");
        sex = console.next();
        System.out.println("Color: ");
        color = console.next();
        System.out.println("Date of birth (YYYY): ");
        year = console.nextInt();
        System.out.println("Date of birth (MM): ");
        month = console.nextInt();
        System.out.println("Date of birth (DD): ");
        day = console.nextInt();
    }

    // Gets input from user about the feed
    public static void getFeedInfo() {
        System.out.println("Number of bin: ");
        numBin = console.nextInt();
        System.out.println("Feed type: ");
        feedType = console.next();
        System.out.println("Weight (in kg): ");
        weight = console.nextDouble();
    }

}
