package group144.shervashidze;

public class ListStack<T> implements Stack<T> {
    private int length = 0;
    private Element<T> head = null;

    @Override
    public void push(T value) {
        if (isEmpty()) {
            head = new Element<T>(value, null);
            length++;
        } else {
            Element<T> temp = new Element<T>(value, head);
            head = temp;
            length++;
        }

    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            length--;
            T answer = head.value;
            head = head.next;
            return answer;
        }
    }

    @Override
    public int getSize() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    private class Element<T> {
        private T value;
        private Element<T> next;

        Element(T value, Element<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
