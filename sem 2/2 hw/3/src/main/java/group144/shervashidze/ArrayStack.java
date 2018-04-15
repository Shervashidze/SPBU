package group144.shervashidze;

import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {
    private int length = 0;
    private int arraySize = 10;
    private T[] values = (T[]) new Object[arraySize];

    @Override
    public void push(T value) {
        if (length == arraySize) {
            values = rewrite(values, length);
        }

        values[length] = value;
        length++;
    }

    private T[] rewrite(T[] values, int length) {
        T[] newArray = (T[]) new Object[arraySize * 2];
        for (int i = 0; i < arraySize; i++) {
            newArray[i] = values[i];
        }

        arraySize *= 2;
        return newArray;
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
