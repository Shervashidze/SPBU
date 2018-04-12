package group144.shervashidze;


/**
 * Single linked list.
 *
 * @param <T> type of stored elements.
 */
public class List<T> {
    private Element<T> head;
    private int length;

    public List() {
        length = 0;
    }

    /**
     * Returns length of the list.
     *
     * @return amount of stored elements.
     */
    public int getLength() {
        return length;
    }

    /**
     * Adds value in the end of the list.
     *
     * @param value(type T) to add in list.
     */
    public void add(T value) throws ListAppendException {
        Element<T> element = new Element<>(value, null);
        if (isEmpty()) {
            head = element;
        } else {
            Element<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = element;
        }

        length++;
    }

    /**
     * Adding value in the list after certain index.
     *
     * @param value(Type T) to add in list.
     * @param position after which new one will be added.
     * @throws IndexOutOfBoundsException if position out of range.
     */
    public void add(T value, int position) throws IndexOutOfBoundsException, ListAppendException {
        if (position < 0 || position > length) {
            throw new IndexOutOfBoundsException();
        }

        length++;
        if (position == 0) {
            head = new Element<>(value, head);
            return;
        }

        Element<T> temp = head;
        for (int i = 0; i < position - 1; i++) {
            temp = temp.next;
        }

        temp.next = new Element<>(value, temp.next);
    }

    /**
     * Checks whether list is empty.
     *
     * @return True if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * return head value of the list.
     *
     * @return value(type T) of the head element.
     * @throws NoSuchElementException when list is empty.
     */
    public T pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head = head.next;
        length--;
        return result;
    }

    /**
     * return value with the certain index.
     *
     * @param position index of popped element.
     * @return value(type T) of the popped element.
     * @throws NoSuchElementException when there is no element with this index.
     */
    public T pop(int position) throws NoSuchElementException {
        if (position == 0) {
            return pop();
        }

        if (position > length - 1 || position < 0) {
            throw new NoSuchElementException();
        }

        Element<T> temp = head;
        for (int i = 0; i < position - 1; i++) {
            temp = temp.next;
        }

        T result = temp.next.value;
        temp.next = temp.next.next;
        length--;
        return result;
    }

    /**
     *
     * @param value(type T) to find in list.
     * @return position(type int) - index of this element or (-1) when there isn't this element in list.
     */
    public int find(T value) {
        Element<T> temp = head;
        for (int i = 0; i < length; i++) {
            if (temp.value == value) {
                return i;
            }
            temp = temp.next;
        }

        return -1;
    }

    /**
     * Node of the list.
     *
     * @param <T> type of the stored elements.
     */
    private class Element<T> {
        private T value;
        private Element<T> next;

        /**
         * @param value stores value in this Element.
         * @param  next stores address of the next element.
         */
        Element(T value, Element<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
