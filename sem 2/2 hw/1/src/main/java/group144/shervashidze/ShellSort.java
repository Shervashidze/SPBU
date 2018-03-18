package group144.shervashidze;

public class ShellSort implements SortMachine {
    @Override
    public void sort(int[] array) {
        for (int step = array.length / 2; step > 0; step /= 2) {
            for (int i = step; i < array.length; i++) {
                int j;
                int temp = array[i];
                for (j = i; j >= step; j -= step) {
                    if (temp < array[j - step])
                        array[j] = array[j - step];
                    else
                        break;
                }
                array[j] = temp;
            }
        }

    }
}
