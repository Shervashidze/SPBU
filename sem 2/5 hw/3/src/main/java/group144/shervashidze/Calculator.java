package group144.shervashidze;

/**
 * Calculator to support javaFX application Calculator.
 * Stores last value and last operation.
 * When we are adding new operation, updating last value.
 */
public class Calculator {
    private double current;
    private boolean isClear = true;
    private Commands operation;

    /**
     * is Clear.
     * @return true when there is a new calculator, false otherwise.
     */
    public boolean isClear() {
        return isClear;
    }

    /**
     * getValue
     * @return current value of the Calculator.
     */
    public double getValue() {
        return current;
    }

    /**
     * SetOperation.
     * @param op to set current operation.
     */
    public void setOperation(Commands op) {
        operation = op;
    }

    /**
     * Set first - initializing calculator.
     * @param string - double value to initialize calculator with.
     * @param op - operation to initialize calculator with.
     */
    public void setFirst(String string, Commands op) {
        current = Double.valueOf(string);
        operation = op;
        isClear = false;
    }

    /**
     * calculate
     * updates current.
     * @param op - setting op as new operation
     * @param string - to set current = current (old)operation string(double value).
     */
    public void calculate(Commands op, String string) {
        Double second = Double.valueOf(string);
        switch (operation) {
            case ADDITION:
                current = current + second;
                break;
            case SUBTRACTION:
                current = current - second;
                break;
            case DIVISION:
                current = current / second;
                break;
            case MULTIPLICATION:
                current = current * second;
                break;
        }

        operation = op;
    }
}
