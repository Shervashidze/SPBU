package group144.shervashidze;

import java.io.PrintStream;
import java.util.Scanner;

public class ExpressionTree {
    private ExpressionTreeNode head;

    /**
     *
     * @param in - stream to take from.
     * @throws WrongTreeException when expression tree has issues.
     *
     * Creates operation node and place it in the head.
     */
    ExpressionTree(Scanner in) throws WrongTreeException {
        head = new OperationNode(in);
    }


    /**
     *
     * @param out - stream to print expression tree.
     */
    public void print(PrintStream out) {
        head.print(out);
    }

    /**
     *
     * @return answer of the all calculations.
     */
    public int calculate() {
        return head.calculate();
    }
}
