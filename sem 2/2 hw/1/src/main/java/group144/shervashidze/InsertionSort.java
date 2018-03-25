package group144.shervashidze;

public class InsertionSort implements SortMachine {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while ((j > 0) && (array[j] < array[j - 1])) {
                swap(array, j, j - 1);
                j--;
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
