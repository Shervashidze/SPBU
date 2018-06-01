package group144.shervashidze;

import java.util.function.Supplier;

/**
 * Class creating Lazy objects.
 */
public class LazyFactory {
    /**
     * Creates non-synchronized Lazy object.
     *
     * @param supplier to get first and only value.
     * @param <T> type of calculated value.
     * @return calculated value or calculates value and then return it if it wasn't calculated before.
     */
    public static <T> Lazy<T> createOneThreadLazy(Supplier<T> supplier) {
        return new Lazy<T>() {
            private boolean wasCalculated = false;
            private T value;

            @Override
            public T get() {
                if (wasCalculated) {
                    return value;
                } else {
                    value = supplier.get();
                    wasCalculated = true;
                    return value;
                }
            }
        };
    }

    /**
     * Creates synchronized Lazy object for using it with many threads.
     *
     * @param supplier to get first and only value.
     * @param <T> type of this value.
     * @return value if it was calculated or calculates it and then return it.
     */
    public static <T> Lazy<T> createManyThreadsLazy(Supplier<T> supplier) {
        return new Lazy<T>() {
            private  boolean wasCalculated = false;
            private T value;

            @Override
            public T get() {
                if (wasCalculated) {
                    return value;
                } else {
                    synchronized (this) {
                        if (!wasCalculated) {
                            value = supplier.get();
                            wasCalculated = true;
                        }
                    }

                    return value;
                }
            }
        };
    }
}
