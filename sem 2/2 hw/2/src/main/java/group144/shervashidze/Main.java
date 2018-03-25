package group144.shervashidze;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] array = takeMatrix(in);
        System.out.println("To print in spiral order in file press 1.");
        System.out.println("To print in spiral order on screen press 2.");
        String switcher = in.next();
        while (!switcher.equals("1") && !switcher.equals("2")) {
            System.out.println("Incorrect choice. Try again. ");
            switcher = in.next();
        }
        if (switcher.equals("1")) {
            System.out.println("Enter file name: ");
            String fileName = in.nextLine();
            fileName = in.nextLine();

            try {
                FileSpiralWriter writer = new FileSpiralWriter(fileName);
                writer.print(array);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }

        } else {
            ConsoleSpiralWriter writer = new ConsoleSpiralWriter();
            writer.print(array);
        }
    }

    private static int[][] takeMatrix(Scanner in) {
        System.out.print("Enter array length (odd number): ");
        int length = in.nextInt();
        if (length <= 0){
            return null;
        }

        System.out.println("Enter the array:");
        int[][] matrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        return matrix;
    }
}
