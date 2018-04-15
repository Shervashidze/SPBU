package group144.shervashidze;

public class Stack {
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
        int result = head.value;
        head = head.next;
        length -= 1;
        return result;
    }

    public int peek() {
        if (isEmpty()){
            return 42;
        }
        return head.value;
    }

    public int getLength() {
        return length;
    }

    private class Element {
        private int value;
        private Element next;

        Element(int value, Element next) {
            this.value = value;
            this.next = next;
        }
    }
}