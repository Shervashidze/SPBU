package group144.shervashidze;

import java.io.PrintStream;

/**
 * Expression tree node.
 *
 * Element of expression tree.
 */
public interface ExpressionTreeNode {
    int calculate();
    void print(PrintStream out);
}
