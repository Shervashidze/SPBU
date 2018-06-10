import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class MainFieldTest {
    private MainField field;

    @Before
    public void before() {
        field = new MainField();
    }

    @Test
    public void getSize() {
        assertTrue(field.getSize() == 3);
    }

    @Test
    public void firstDesk() {
        field.makeTurn(0, 0);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(0, 1);
        field.makeTurn(1, 0);
        field.makeTurn(1, 1);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(2, 0);
        assertTrue(field.isEnd() == MainField.GameState.XWON);
    }

    @Test
    public void secondDesk() {
        field.makeTurn(0, 0);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(0, 1);
        field.makeTurn(1, 1);
        field.makeTurn(1, 0);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(2, 2);
        assertTrue(field.isEnd() == MainField.GameState.XWON);
    }

    @Test
    public void fullDesk() {
        field.makeTurn(0, 0);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(0, 1);
        field.makeTurn(1, 0);
        field.makeTurn(1, 1);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(2, 1);
        field.makeTurn(2, 0);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(0, 2);
        assertFalse(field.isEnd() == MainField.GameState.PLAYING);
        field.makeTurn(1, 2);
        field.makeTurn(2, 2);
        assertTrue(field.isEnd() == MainField.GameState.DRAW);
    }
}