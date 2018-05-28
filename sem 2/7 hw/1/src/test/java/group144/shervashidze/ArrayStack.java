package group144.shervashidze;

@SuppressWarnings("ALL")
public class ArrayStack<T> implements Stack<T> {
    private int length = 0;
    private int arraySize = 10;
    private T[] values = (T[]) new Object[arraySize];

    @Override
    public void push(T value) {
        if (length == arraySize) {
            rewrite(length);
        }

        values[length] = value;
        length++;
    }

    private void rewrite(int length) {
        T[] newArray = (T[]) new Object[arraySize * 2];
        for (int i = 0; i < arraySize; i++) {
            newArray[i] = values[i];
        }

        arraySize *= 2;
        values = newArray;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T answer = values[length - 1];
            length--;
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
}
