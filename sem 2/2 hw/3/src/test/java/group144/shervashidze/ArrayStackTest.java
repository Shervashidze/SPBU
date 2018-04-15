package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

public class ArrayStackTest {

    @Test
    public void push() {
        Stack<Integer> stack = new ArrayStack<Integer>();
        stack.push(42);
        stack.push(33);

        int a = stack.pop();
        int b = stack.pop();
        assertEquals(33, a);
        assertEquals(42 ,b);
    }

    @Test(expected = EmptyStackException.class)
    public void popEmpty() {
        Stack<Integer> stack = new ArrayStack<Integer>();
        stack.push(42);
        stack.push(33);
        stack.pop();
        stack.pop();
        stack.pop();
    }

    @Test
    public void getSize() {
        Stack<Integer> stack = new ArrayStack<Integer>();
        for (int i = 0; i < 24; i++) {
            stack.push(i + 12);
        }

        assertEquals(24, stack.getSize());
    }

    @Test
    public void isEmpty() {
        Stack<Integer> stack = new ArrayStack<Integer>();
        stack.push(11);
        stack.push(12);
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}