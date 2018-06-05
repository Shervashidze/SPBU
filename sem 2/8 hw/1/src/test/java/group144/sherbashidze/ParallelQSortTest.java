package group144.sherbashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelQSortTest {
    private static Sorter sorter = new ParallelQSort();

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
        int[] second ={-2001,-12,0,0,0,1,1,1,1,2,7,14,14,15,23,42,42,44,109,199,250,1444};
        sorter.sort(array);
        assertArrayEquals(second, array);
    }

    @Test
    public void sortEquals() {
        int[] array = {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] second = {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        sorter.sort(array);
        assertArrayEquals(second, array);
    }

}