package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsertionSortTest {
    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static SortMachine sorter = new InsertionSort();

    @Test
    public void sortSorted() {
        int[] array = {1, 2, 3, 6, 7};
        int[] sorted = {1, 2, 3, 6, 7};
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }

    @Test
    public void sortEmpty() {
        int[] array = {};
        int[] sorted = {};
        sorter.sort(array);
        assertArrayEquals(sorted, array);
    }

    @Test
    public void sortCommon() {
        int[] array = {7,1,1,14,2,15,23,44,109,-12,0,1,1,0,0,14,1444,250,199,-2001,42,42};
        sorter.sort(array);
        assertTrue(isSorted(array));
    }

    @Test
    public void sortEquals() {
        int[] array = {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        sorter.sort(array);
        assertTrue(isSorted(array));
    }
}