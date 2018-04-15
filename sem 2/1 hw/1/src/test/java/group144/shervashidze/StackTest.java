package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void pop() {
        Stack stack = new Stack();
        assertEquals(stack.pop(), 42);
    }

    @Test
    public void peek() {
        Stack stack = new Stack();
        stack.add(777);
        assertEquals(stack.pop(), 777);
    }

    @Test
    public void getLength() {
        Stack stack = new Stack();
        stack.add(777);
        stack.add(12);
        stack.add(1);
        stack.pop();
        assertEquals(stack.getLength(), 2);
    }
}