package group144.shervashidze;

import java.util.EmptyStackException;

public interface Stack<T> {
    void push(T value);
    T pop() throws EmptyStackException, group144.shervashidze.EmptyStackException;

    int getSize();
    boolean isEmpty();
}