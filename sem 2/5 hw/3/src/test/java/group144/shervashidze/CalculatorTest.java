package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void isClear() {
        Calculator calc = new Calculator();
        assertTrue(calc.isClear());
        calc.setFirst("3", Commands.ADDITION);
        assertFalse(calc.isClear());
    }

    @Test
    public void calculate() {
        Calculator calc = new Calculator();
        calc.setFirst("3", Commands.ADDITION);
        calc.calculate(Commands.SUBTRACTION, "4");
        assertEquals(7.0, calc.getValue(), 0.001);
        calc.calculate(Commands.ADDITION, "6");
        assertEquals(1.0, calc.getValue(), 0.001);
    }

    @Test
    public void setOperation() {
        Calculator calc = new Calculator();
        calc.setFirst("3", Commands.ADDITION);
        calc.setOperation(Commands.SUBTRACTION);
        calc.calculate(Commands.ADDITION, "3");
        assertEquals(0.0, calc.getValue(), 0.001);
    }
}