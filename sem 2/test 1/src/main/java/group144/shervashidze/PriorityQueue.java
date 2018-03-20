package group144.shervashidze;
/**
  * Priority queue.
  *
  * @param <T> Type of values stored in queue.
  */
public class PriorityQueue<T> {
    private Element<T> head;
    private int length = 0;

    public PriorityQueue() {
        head = null;
    }

    /**
     * Checks whether queue is empty.
     *
     * @reurn true when queue has no elements, false otherwise.
     */
    private boolean isEmpty() {
        return length == 0;
    }

    /**
     * Checks whether there is only 1 element in the queue.
     *
     * @return true when queue has 1 element, false otherwise.
     */
    private boolean isSingle() {
        return length == 1;
    }

    /**
     * Adds value in queue.
     * Queue sorted by elements priorities.
     *
     * @param value(type T) and it's priority.
     */
    public void enqueue(T value, int position) {
        if (isEmpty()) {
            length++;
            head = new Element<>(value, position, null);
            return;
        }

        length++;

        if (position > head.priority) {
            head = new Element<>(value, position, head);
            return;
        }

        if (isSingle()) {
            head.next = new Element<>(value, position, null);
            return;
        }


        Element<T> temp = head;
        while (temp.next != null && temp.next.priority > position) {
            temp = temp.next;
        }

        Element<T> missing = temp.next;
        temp.next = new Element<>(value, position, missing);
    }

    /**
     * Take value(type T) from Queue.
     * @return the most prioritised element(type T). Element's priority vanishes.
     * @throws EmptyQueueException when queue is empty.
     *
     */
    public T dequeue() throws EmptyQueueException{
        if (length == 0) {
            throw new EmptyQueueException();
        } else {
            length--;
            T answer = (T) head.value;
            head = head.next;
            return answer;
        }
    }

    /**
     * @param <T> Type of the Value stored in Element.
     */
    private class Element<T> {
        private T value;
        private int priority;
        private Element<T> next;

        /**
         * @param value stores value in this Element.
         * @param  priority stores priority(type int) in this element.
         * @param  next stores address of the next element.
         */
        Element(T value, int priority, Element<T> next) {
            this.value = value;
            this.next = next;
            this.priority = priority;
        }
    }
}
