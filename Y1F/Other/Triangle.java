package Y1F.Other;

public class Triangle {
    
    public static void main(String[] args) {

        int size = 5;
        
        System.out.println();
        for (int i = 0; i < size; i++) {

            // Print spaces
            for (int j = size - i; j > 1; j--) {
                System.out.print(" ");
            }

            // Print *
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }

            System.out.println();
            
        }

    }
}
