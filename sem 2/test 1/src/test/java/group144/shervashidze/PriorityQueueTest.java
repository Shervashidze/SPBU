package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriorityQueueTest {

    @Test
    public void dequeueCommonTest() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(12, 3);
        queue.enqueue(4, 7);
        boolean answer = (4 == queue.dequeue());
        assertTrue(answer);
    }

    @Test(expected = EmptyQueueException.class)
    public void dequeueEmptyTest() {
        PriorityQueue queue = new PriorityQueue();
        queue.dequeue();
    }

    @Test
    public void dequeueMoreElementsTest() {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.enqueue("Hate", 9);
        queue.enqueue("I", 11);
        queue.enqueue("*******", 3);
        queue.enqueue("Java", -12);
        queue.enqueue("This", 5);

        assertEquals("I", queue.dequeue());
        assertEquals("Hate", queue.dequeue());
        assertEquals("This", queue.dequeue());
        queue.dequeue();
        assertEquals("Java", queue.dequeue());

    }

}
