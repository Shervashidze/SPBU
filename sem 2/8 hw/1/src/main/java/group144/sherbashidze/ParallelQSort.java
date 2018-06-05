package group144.sherbashidze;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * ParallelQSort.
 * QSort - many threads QSort.
 */
public class ParallelQSort implements Sorter {
    /* array to sort */
    private int[] array;

    /**
     * sorting array in increasing order.
     * @param newArray - array to sort.
     */
    @Override
    public void sort(int[] newArray) {
        array = newArray;
        if (array.length == 0) {
            return;
        }
        ForkJoinPool.commonPool().invoke(new SortAction(0, array.length - 1));
    }

    /**
     * private class extend RecursiveAction to spread threads for each part of the array that we are sorting
     * in that moment.
     */
    private class SortAction extends RecursiveAction {
        /* left border of the array being sorted */
        private int left;
        /* right border of the array being sorted */
        private int right;

        /**
         * initializing of the sorting certain part of the array
         * @param l - left bound.
         * @param r - right bound.
         */
        SortAction(int l, int r) {
            left = l;
            right = r;
        }

        /**
         * Ordinary QSort algorithm, but we are splitting threads when the recursive part stars.
         */
        @Override
        protected void compute() {
            if (right <= left) {
                return;
            }

            int middle = array[(left + right) / 2];
            int l = left;
            int r = right;
            while (l <= r) {
                while (array[l] < middle) {
                    l++;
                }
                while (array[r] > middle) {
                    r--;
                }

                if (l <= r) {
                    swap(l, r);
                    l++;
                    r--;
                }
            }

            invokeAll(new SortAction(left, l - 1), new SortAction(l, right));
        }

        /**
         * Swaps 2 elements of core array of sorter.
         * @param i - first element.
         * @param j - second element.
         * */
        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
