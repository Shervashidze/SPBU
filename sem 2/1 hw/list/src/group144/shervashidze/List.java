package group144.shervashidze;

public class List {
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

        void setNext(Element next) {
            this.next = next;
        }
    }

    private Element head;
    private int length;

    List() {
        head = null;
        length = 0;
    }

    private boolean isEmpty() {
        return length == 0;
    }

    public int getLength() {
        return length;
    }

    public void add(int value) {
        if (isEmpty()) {
            head = new Element(value, null);
            length++;
            return;
        }
        Element temp = this.head;
        while (temp.next != null)
            temp = temp.next;
        length++;
        temp.setNext(new Element(value, null));
    }

    public void add(int value, int position) {
        if (position > length || position <= 0){
            add(value);
            return;
        }
        length++;
        if (position == 1) {
            Element temp = this.head;
            this.head = new Element(value, temp);
            return;
        }
        Element temp = head;
        for (int i = 2; i < position; i++) {
            temp = temp.getNext();
        }
        temp.setNext(new Element(value, temp.getNext()));
    }

    public int pop() {
        if (isEmpty())
            return -1;
        int result = head.getValue();
        head = head.getNext();
        length--;
        return result;
    }

    public int find(int value) {
        Element temp = head;
        for (int i = 0; i < getLength(); i++) {
            if (temp.getValue() == value)
                return i + 1;
            temp = temp.getNext();
        }
        return -1;
    }
}
