package Y1F.Other;

import java.util.Scanner;

public class ArrayPractice {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String[] array = new String[5];

        for (int i = 0; i < array.length; i++) {
            System.out.println("Enter your name");
            array[i] = console.nextLine();
        }

        System.out.println("Names in reverse");
        for (int j = array.length - 1; j >= 0; j--) {
            System.out.println(array[j]);
        }

        console.close();

    }

}
