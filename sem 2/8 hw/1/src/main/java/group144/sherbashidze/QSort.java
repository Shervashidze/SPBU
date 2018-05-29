package group144.sherbashidze;

/**
 * QSort - ordinary 1 thread quick sort.
 */
public class QSort implements Sorter {
    /* array to sort */
    private int[] array;

    /**
     * Sorts in increasing order.
     * @param newArray to sort.
     */
    @Override
    public void sort(int[] newArray) {
        array = newArray;
        qSort(0, array.length - 1);
    }

    /**
     * Recursive function to realize quick sort algorithm.
     * @param start - left bound of sortered part.
     * @param end - right bound of sortered part.
     */
    private void qSort(int start, int end) {
        if (end <= start) {
            return;
        }

        int middle = array[(start + end) / 2];
        int l = start;
        int r = end;
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

        qSort(start, l - 1);
        qSort(l, end);
    }

    /**
     * Swaps 2 elements of core array of sorter.
     * @param i - first element.
     * @param j - second element.
     */
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
