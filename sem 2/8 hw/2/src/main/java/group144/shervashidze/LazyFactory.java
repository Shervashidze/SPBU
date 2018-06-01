package group144.shervashidze;

import java.util.function.Supplier;

public class LazyFactory {
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
