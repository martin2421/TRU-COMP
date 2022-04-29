package Y1F.Other;

import java.io.*;
import java.util.*;

public class File {

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList students = new ArrayList<>();
        Scanner inFile = null;

        try {

            // Read a file
            inFile = new Scanner(new FileReader("Other/students.txt"));
            
            // Add to arraylist
            while (inFile.hasNextLine()) {
                String text = inFile.nextLine();
                students.add(text);
            }

            Random random = new Random(); // Random constructor
            int randomPos = random.nextInt(21); // Random number between 1 - 21

            // Random student
            System.out.println("Selected student is: " + students.get(randomPos) + " at postion " + randomPos);

            // Find the longest name from arraylist
            String longestName = "";
            for (Object name : students) {
                int length = name.toString().trim().length(); // .trim() to remove the space
                System.out.println(name + " length " + length);
                if (length > longestName.length()) {
                    longestName = name.toString(); // change value to the new longest name
                }
            }

            // Output longest name
            System.out.println("Longest name: " + longestName);

            inFile.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found.");

        }

    }

}
