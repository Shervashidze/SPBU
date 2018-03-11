package group144.shervashidze;

public class Stack {
    private class Element {
        private int value;
        private Element next;

        Element(int value, Element next) {
            this.value = value;
            this.next = next;
        }

        int getValue() {
            return value;
        }

        Element getNext() {
            return next;
        }
    }

    private Element head = null;
    private int length = 0;

    public boolean isEmpty() {
        return length == 0;
    }

    public void add(int value) {
        head = new Element(value, head);
        length += 1;
    }

    public int pop() {
        if (isEmpty()){
            return 42;
        }
        int result = head.getValue();
        head = head.getNext();
        length -= 1;
        return result;
    }

    public int peek() {
        if (isEmpty()){
            return 42;
        }
        return head.getValue();
    }

    public int getLength() {
        return length;
    }
}