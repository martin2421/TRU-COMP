package Y1F.Other;

import java.util.Arrays;

public class Array_2D {

    public static void main(String[] args) {
        
        char[][] charTable = new char[10][20]; // [Rows] [Columns]

        // Setting random characters to each cell
        for (int i = 0; i < charTable.length; i++) { // Rows
            for (int j = 0; j < charTable[i].length; j++) { // Columns
                charTable[i][j] = (char) ('A' + (int) (Math.random() * 50));
            }
        }

        // Printing content
        for (int i = 0; i < charTable.length; i++) { // Rows
            for (int j = 0; j < charTable[i].length; j++) { // Columns
                System.out.print(charTable[i][j] + ", ");
            }
            System.out.println();
        }

        System.out.println();

        // Arrays method to print a 2D array
        System.out.println(Arrays.deepToString(charTable));

    }
    
}
