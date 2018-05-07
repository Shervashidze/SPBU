package group144.shervashidze;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    private static PrintStream out = System.out;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        out.println("Enter an expression: ");
        String expression = in.nextLine();
        Calculator calculator = new Calculator(new ListStack<>());
        out.print("Answer: ");

        try {
            out.print(calculator.calculate(expression));
        } catch (WrongExpressionException e) {
            out.print("Wrong expression.");
        }
    }
}
