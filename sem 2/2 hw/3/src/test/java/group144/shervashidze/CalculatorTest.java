package group144.shervashidze;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void calculateTest() throws WrongExpressionException {
        assertEquals(21, (new Calculator(new ListStack<>())).calculate("7 3 + 5 * 2 / 4 - "));
        assertEquals(21, (new Calculator(new ArrayStack<>())).calculate("7 3 + 5 * 2 / 4 - "));
    }

    @Test
    public void calculateNegativeTest() throws WrongExpressionException {
        assertEquals(3, (new Calculator(new ListStack<>())).calculate("-2 -1 + -2 - -3 * 1 / "));
        assertEquals(3, (new Calculator(new ArrayStack<>())).calculate("-2 -1 + -2 - -3 * 1 / "));
    }

    @Test
    public void calculateBigNumbersTest() throws WrongExpressionException {
        assertEquals(1, (new Calculator(new ListStack<>())).calculate("2500 25 / 99 -"));
        assertEquals(1, (new Calculator(new ArrayStack<>())).calculate("2500 25 / 99 -"));
    }

    @Test (expected = WrongExpressionException.class)
    public void calculateWrongExpressionListTest() throws WrongExpressionException {
        assertEquals(1, (new Calculator(new ListStack<>())).calculate("55 + "));
    }

    @Test (expected = WrongExpressionException.class)
    public void calculateWrongExpressionArrayTest() throws WrongExpressionException {
        assertEquals(1, (new Calculator(new ArrayStack<>())).calculate("55 + "));
    }
}