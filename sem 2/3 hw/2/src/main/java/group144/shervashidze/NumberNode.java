package group144.shervashidze;

import java.io.PrintStream;
import java.util.Scanner;

public class NumberNode implements ExpressionTreeNode {
    private int value;

    /**
     *
     * @param in - stream to take from.
     * @throws WrongTreeException when expression tree has issues.
     * Skipping ')' in the end if necessary.
     */
    NumberNode(Scanner in) throws WrongTreeException {
        try {
            if (in.hasNextInt()) {
                value = Integer.parseInt(in.next());
            } else {
                String input = in.next();
                value = Integer.parseInt(input.substring(0, input.indexOf(')')));
            }
        } catch (NumberFormatException exception) {
            throw new WrongTreeException();
        }
    }

    /**
     *
     * @return value of the number node.
     */
    @Override
    public int calculate() {
        return value;
    }

    /**
     *
     * @param out - stream to print value.
     */
    @Override
    public void print(PrintStream out) {
        out.print(value);
    }
}
