package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void add() throws NoSuchElementException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(42);
        list.add(7);
        list.add(4);
        assertEquals(Integer.valueOf(42), list.pop());
        assertEquals(Integer.valueOf(7), list.pop());
        assertEquals(Integer.valueOf(4), list.pop());
    }

    @Test
    public void addToPosition() throws NoSuchElementException, IndexOutOfBoundsException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(42, 0);
        list.add(7, 0);
        list.add(4, 2);
        assertEquals(Integer.valueOf(7), list.pop());
        assertEquals(Integer.valueOf(42), list.pop());
        assertEquals(Integer.valueOf(4), list.pop());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failAddToPosition() throws IndexOutOfBoundsException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(42, 1);
    }

    @Test
    public void pop() throws NoSuchElementException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(15);
        list.add(420);
        list.add(-22);
        assertEquals(Integer.valueOf(15), list.pop());
    }

    @Test
    public void popFromPosition() throws NoSuchElementException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(15);
        list.add(420);
        list.add(-22);
        list.add(0);
        list.add(11);
        assertEquals(Integer.valueOf(11), list.pop(4));
        assertEquals(Integer.valueOf(-22), list.pop(2));
        assertEquals(Integer.valueOf(420), list.pop(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void popEmpty() throws NoSuchElementException {
        List<Integer> list = new List<>();
        int oops = list.pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void failPopFromPosition() throws NoSuchElementException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int oops = list.pop(3);
    }

    @Test
    public void isEmpty() throws NoSuchElementException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(1);
        assertFalse(list.isEmpty());
        list.pop();
        assertTrue(list.isEmpty());
    }

    @Test
    public void getLength() throws NoSuchElementException, ListAppendException {
        List<Integer> list = new List<>();
        list.add(42);
        assertEquals(1, list.getLength());
        list.pop();
        assertEquals(0, list.getLength());
        list.add(42);
        list.add(42);
        assertEquals(2, list.getLength());
    }

    @Test
    public void find() throws ListAppendException {
        List<String> list = new List<>();
        list.add("rest");
        list.add("in");
        list.add("peace");
        assertEquals(2, list.find("peace"));
        assertEquals(0, list.find("rest"));
        assertEquals(1, list.find("in"));
        assertEquals(-1, list.find("aloha"));
    }
}