package group144.shervashidze;

import org.junit.Test;

import static group144.shervashidze.MatrixToString.matrixToString;
import static org.junit.Assert.*;

public class MatrixToStringTest {

    @Test
    public void emptyArray() {
        int[][] array = null;
        String string = matrixToString(array);
        assertEquals("Wrong array length.", string);
    }

    @Test
    public void commonTest() {
        int[][] array = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25}};
        String string = matrixToString(array);
        assertEquals("13 14 9 8 7 12 17 18 19 20 15 10 5 4 3 2 1 6 11 16 21 22 23 24 25 ", string);
    }

    @Test
    public void wrongLengthTest() {
        int[][] array = {{1, 2},{3, 4}};
        String string = matrixToString(array);
        assertEquals("Wrong array length.", string);
    }
}
