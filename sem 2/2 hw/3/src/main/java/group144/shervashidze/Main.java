package group144.shervashidze;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter an expression: ");
        Scanner in = new Scanner(System.in);
        String exp = in.nextLine();
        Calculator calculator = new Calculator();
        System.out.print("Answer: ");
        System.out.print(calculator.calculate(exp));
    }
}
