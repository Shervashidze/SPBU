package group144.shervashidze;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void calculateTest() {
        assertEquals(21, (new Calculator()).calculate("7 3 + 5 * 2 / 4 - "));
    }

    @Test
    public void calculateNegativeTest() {
        assertEquals(3, (new Calculator()).calculate("-2 -1 + -2 - -3 * 1 / "));
    }

    @Test
    public void calculateBigNumbersTest() {
        assertEquals(1, (new Calculator()).calculate("2500 25 / 99 -"));
    }

    @Test (expected = EmptyStackException.class)
    public void calculateWrongExpressionTest() {
        assertEquals(1, (new Calculator()).calculate("55 + "));
    }
}