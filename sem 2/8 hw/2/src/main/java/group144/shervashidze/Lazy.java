package group144.shervashidze;

/**
 * Interface for lazy calculation(value calculated only once)
 * @param <T> - type of value to get.
 */
public interface Lazy<T> {
    /**
     * Value was calculated only once when we are using get() first time.
     * @return calculated value.
     */
    T get();
}