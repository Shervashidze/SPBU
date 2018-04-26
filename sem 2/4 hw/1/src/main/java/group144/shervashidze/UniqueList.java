package group144.shervashidze;

/**
 * Class realizing Simple Linked List without repetitive elements.
 *
 * @param <T> Type parameter.
 */
public class UniqueList<T> extends List<T> {

    /**
     * Adds element to list
     *
     * @param value(type T) to add.
     * @throws ElementAlreadyAddedException if element is already added.
     */
    @Override
    public void add(T value) throws ListAppendException {
        if (super.find(value) != -1) {
            throw new ElementAlreadyAddedException();
        }

        super.add(value);
    }

    /**
     * Adding value in the list after certain index.
     *
     * @param value(Type T) to add.
     * @param position after which new one will be added.
     * @throws IndexOutOfBoundsException if position out of range.
     * @throws ElementAlreadyAddedException if element is already added.
     */
    @Override
    public void add(T value, int position) throws IndexOutOfBoundsException, ListAppendException{
        if (super.find(value) != -1) {
            throw new ElementAlreadyAddedException();
        }

        super.add(value, position);
    }
}
