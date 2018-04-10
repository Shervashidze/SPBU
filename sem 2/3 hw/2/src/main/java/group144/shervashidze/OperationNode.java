package group144.shervashidze;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OperationNode implements ExpressionTreeNode {
    private char operation;
    private ExpressionTreeNode left;
    private ExpressionTreeNode right;

    /**
     *
     * @param in stream to take from.
     * @throws WrongTreeException when expression tree has issues.
     *
     * Creates number node if left operand is number.
     * Creates operation node otherwise.
     *
     * Creates number node if right operand fulfill pattern(number and some ')' after).
     * Creates operation node otherwise.
     */
    OperationNode(Scanner in) throws WrongTreeException {
        String input = in.next();
        operation = input.charAt(1);
        if ((operation != '+' && operation != '-' && operation != '*' && operation != '/') || input.length() >= 3) {
            throw new WrongTreeException();
        }

        if (in.hasNextInt()) {
            left = new NumberNode(in);
        } else {
            left = new OperationNode(in);
        }

        if (in.hasNext(Pattern.compile("[-]?[0-9]+([)]+)?"))) {
            right = new NumberNode(in);
        } else {
            right = new OperationNode(in);
        }
    }

    /**
     *
     * @return int value = (calculate left node) operation (calculate right node).
     */
    @Override
    public int calculate() {
        switch (operation) {
            case '+':
                return left.calculate() + right.calculate();
            case '-':
                return left.calculate() - right.calculate();
            case '*':
                return left.calculate() * right.calculate();
            case '/':
                return left.calculate() / right.calculate();
            default:
                return 0;
        }
    }

    /**
     *
     * @param out - stream to print expression tree.
     */
    @Override
    public void print(PrintStream out) {
        out.print('(');
        out.print(operation);
        out.print(' ');
        left.print(out);
        out.print(' ');
        right.print(out);
        out.print(')');
    }
}
