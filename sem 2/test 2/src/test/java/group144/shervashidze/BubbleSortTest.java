package group144.shervashidze;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BubbleSortTest {
    @Test
    public void integerSortTest() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 4; i > 0; i--) {
            list.add(i);
        }
        assertEquals(list, BubbleSort.sort(Arrays.asList(4, 2, 3, 1), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        }));
    }

    @Test
    public void stringSortTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("maxSizeDetected");
        list.add("asdasd");
        list.add("1");
        list.add("");

        assertEquals(list, BubbleSort.sort(Arrays.asList("", "maxSizeDetected", "1", "asdasd"), new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }));
    }

    private class TwoInt {
        private int first;
        private int second;

        private TwoInt(int a, int b) {
            first = a;
            second = b;
        }
    }

    @Test
    public void myClassSortTest() {
        LinkedList<TwoInt> list = new LinkedList<>();
        TwoInt first = new TwoInt(5, 2);
        TwoInt second = new TwoInt(4, 2);
        TwoInt third = new TwoInt(3, 2);
        TwoInt fourth = new TwoInt(2, 2);
        TwoInt fith = new TwoInt(1, 1);
        list.add(first);
        list.add(second);
        list.add(third);
        list.add(fourth);
        list.add(fith);

        assertEquals(list, BubbleSort.sort(Arrays.asList(third, second, fith, fourth, first), new Comparator<TwoInt>() {
            @Override
            public int compare(TwoInt o1, TwoInt o2) {
                return o1.first * o1.second - o2.first * o2.second;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        }));
    }
}