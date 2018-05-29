package group144.sherbashidze;

import java.util.Random;

public class Main {
    private static final int SIZE_OF_ARRAYS = 10000000;
    private static final int BOUNDS = 10000;
    private static final int AMOUNT_OF_TESTS = 10;
    private static Random random = new Random();

    public static void main(String[] args) {
        int PSTime = 0;
        int QSTime = 0;
        for (int i = 0; i < AMOUNT_OF_TESTS; i++) {
            int[] firstArray = getRandomArray();
            int[] secondArray = firstArray.clone();

            QSort qSort = new QSort();
            QSTime += getTime(firstArray, qSort);

            ParallelQSort parallelQSort = new ParallelQSort();
            PSTime += getTime(secondArray, parallelQSort);
        }

        System.out.print("Average common QSort time: " + QSTime / AMOUNT_OF_TESTS);
        System.out.print("\nAverage parallel sort time: " + PSTime / AMOUNT_OF_TESTS);
    }

    private static long getTime(int[] array, Sorter sorter) {
        long begin = System.currentTimeMillis();
        sorter.sort(array);
        return System.currentTimeMillis() - begin;
    }

    private static int[] getRandomArray() {
        int[] newArray = new int[SIZE_OF_ARRAYS];
        for (int i = 0; i < SIZE_OF_ARRAYS; i++) {
            newArray[i] = random.nextInt() % BOUNDS;
        }

        return newArray;
    }
}
