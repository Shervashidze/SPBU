package group144.shervashidze;

import java.io.*;
import java.util.Scanner;

public class Main {
    static private Scanner in = new Scanner(System.in);
    static private PrintStream out = System.out;

    public static void main(String[] args) {
        while (true) {
            out.print("Add file name: ");
            String fileName = in.nextLine();
            try {
                FileReader fileReader = new FileReader(fileName);
                Scanner fileScanner = new Scanner(fileReader);
                ExpressionTree tree = new ExpressionTree(fileScanner);
                System.out.println("Expression tree:");
                tree.print(System.out);
                System.out.print("\nResult: ");
                System.out.println(tree.calculate());
                return;
            } catch (FileNotFoundException fileException) {
                System.out.println("File is not found");
            } catch (WrongTreeException wrongTreeException) {
                System.out.println("Wrong tree format");
            }
        }
    }
}
