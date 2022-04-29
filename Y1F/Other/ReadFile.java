package Y1F.Other;

import java.io.File;
import java.io.FileInputStream;

public class ReadFile {
    public static void main(String args[]) {

        final String fileName = "Other/students.txt";

        try {

            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File does not exist.");
                System.exit(0);
            }

            // FileOutputStream object
            FileInputStream fileIn = new FileInputStream(file);

            // Flag
            int val;

            // Read text from file
            System.out.println("Content of the file is: ");
            while ((val = fileIn.read()) != -1) {
                System.out.print((char) val);
            }

            System.out.println();

            fileIn.close();

        } catch (Exception e) {

            System.out.println("Whoops! There was an error");

        }
    }
}