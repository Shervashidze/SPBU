package group144.shervashidze;

/**
 * Calculator to support javaFX application Calculator.
 * Stores last value and last operation.
 * When we are adding new operation, updating last value.
 */
public class Calculator {
    /* tail is buffer value - calculation of previous operations and values. */
    private double tail;
    /* true if calculator was not initialized */
    private boolean isClear = true;
    /* operation - last operation, current value of calculator is tail (operation) firstPriorityValue.
    Can be only add or sub. */
    private Commands operation;
    /* storing here last Value in case there would be mul or div as next operation */
    private double firstPriorityValue;
    /* storing here recent operation. Can be changed outside */
    private Commands firstPriorityOp;

    /**
     * is Clear.
     * @return true when there is a new calculator, false otherwise.
     */
    public boolean isClear() {
        return isClear;
    }

    /**
     * getValue
     * current value is tail (operation) firstPriorityValue.
     * @return current value of the Calculator.
     */
    public double getValue() {
        switch (operation) {
            case ADDITION:
                return tail + firstPriorityValue;
            case SUBTRACTION:
                return tail - firstPriorityValue;
        }

        return 42;
    }

    /**
     * SetOperation.
     * @param op to set first priority operation.
     */
    public void setOperation(Commands op) {
        firstPriorityOp = op;
    }

    /**
     * Set first - initializing calculator.
     * @param string - double value to initialize calculator with.
     * @param op - operation to initialize calculator with.
     */
    public void setFirst(String string, Commands op) {
        tail = 0;
        firstPriorityValue = Double.valueOf(string);
        operation = Commands.ADDITION;
        firstPriorityOp = op;
        isClear = false;
    }

    /**
     * calculate
     * updates tail if firstPriorityOp is add or sub, else only work with firstPriorityValue.
     * @param op - setting op as new firstPriorityOp;
     * @param string - if firstPriorityOp is add or sub set firstPriorityValue as string, otherwise do operation with
     *               firstPriorityValue.
     */
    public void calculate(Commands op, String string) {
        Double second = Double.valueOf(string);
        switch (firstPriorityOp) {
            case ADDITION:
                if (operation == Commands.ADDITION) {
                    tail = tail + firstPriorityValue;
                } else {
                    tail = tail - firstPriorityValue;
                }
                firstPriorityValue = second;
                operation = firstPriorityOp;
                break;
            case SUBTRACTION:
                if (operation == Commands.ADDITION) {
                    tail = tail + firstPriorityValue;
                } else {
                    tail = tail - firstPriorityValue;
                }
                firstPriorityValue = second;
                operation = firstPriorityOp;
                break;
            case DIVISION:
                firstPriorityValue = firstPriorityValue / second;
                break;
            case MULTIPLICATION:
                firstPriorityValue = firstPriorityValue * second;
                break;
        }

        firstPriorityOp = op;
    }
}