package group144.shervashidze;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class LazyFactoryTest {
    @Test
    public void oneThreadTest() {
        MyStringSupplier supplier = new MyStringSupplier();
        Lazy<String> lazy = LazyFactory.createOneThreadLazy(supplier);
        assertEquals("Hello", lazy.get());
        lazy.get();
        lazy.get();
        lazy.get();
        assertEquals(1, supplier.getCounter());
        assertEquals("Hello", lazy.get());
    }

    @Test
    public void manyThreadsTest() {
        final int AMOUNT_OF_THREADS = 10000;
        MyStringSupplier supplier = new MyStringSupplier();
        Lazy<String> lazy = LazyFactory.createManyThreadsLazy(supplier);

        Thread[] thread = new Thread[AMOUNT_OF_THREADS];
        List<Thread> list = Arrays.stream(thread)
                .map(thread1 -> new Thread(() -> assertEquals("Hello", lazy.get())))
                .collect(Collectors.toList());

        list.forEach(Thread::start);
        list.forEach(thread1 -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        assertEquals(1, supplier.getCounter());
    }

    @Test
    public void nullOneThreadTest() {
        MyIntSupplier supplier = new MyIntSupplier();
        Lazy<Integer> oneThreadLazy = LazyFactory.createOneThreadLazy(supplier);
        assertEquals(null, oneThreadLazy.get());
        assertEquals(null, oneThreadLazy.get());
        assertEquals(1, supplier.getCounter());
    }

    @Test
    public void nullManyThreadsTest() {
        MyIntSupplier supplier = new MyIntSupplier();
        Lazy<Integer> oneThreadLazy = LazyFactory.createManyThreadsLazy(supplier);
        assertEquals(null, oneThreadLazy.get());
        assertEquals(null, oneThreadLazy.get());
        assertEquals(1, supplier.getCounter());
    }


    private class MyIntSupplier implements Supplier<Integer> {
        private int counter = 0;

        @Override
        public Integer get() {
            counter++;
            return null;
        }

         public int getCounter() {
            return counter;
        }
    }

    private class MyStringSupplier implements Supplier<String> {
        private int counter = 0;

        @Override
        public String get() {
            counter++;
            if (counter == 1) {
                return "Hello";
            }
            return "smthing went wrong";
        }

        public int getCounter() {
            return counter;
        }
    }
}