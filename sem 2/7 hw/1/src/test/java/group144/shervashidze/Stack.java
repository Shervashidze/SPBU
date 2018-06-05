package group144.shervashidze;

public interface Stack<T> {
    void push(T value);
    T pop() throws EmptyStackException, EmptyStackException;

    int getSize();
    boolean isEmpty();
}
