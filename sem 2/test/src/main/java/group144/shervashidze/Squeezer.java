package group144.shervashidze;

import java.util.LinkedList;

/**
 * public class to squeeze array of bytes.
 */
public class Squeezer {

    /**
     * Squeeze method: amount of repetitive elements in bytes, repetitive byte.
     * @param array to squeeze.
     * @return new squeezed array.
     */
    public static byte[] squeeze(byte[] array) {
        if (array == null) {
            return null;
        }

        LinkedList<Byte> answer = new LinkedList<>();
        for (int i = 0; i <= array.length - 1; i++) {
            int counter = 1;
            while (i < array.length - 1 && array[i] == array[i + 1]) {
                counter++;
                i++;
            }

            answer.add((byte) counter);
            answer.add(array[i]);
        }

        return toArray(answer);
    }

    /**
     * Private method to create array from list.
     * @param list<Byte> to get array of bytes from it.
     * @return this array of bytes.
     */
    private static byte[] toArray(LinkedList<Byte> list) {
        byte[] finalAnswer = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            finalAnswer[i] = list.get(i);
        }

        return finalAnswer;
    }

    /**
     * DeSqueeze method: there are x - amount of repeats and then element(for each element). Just add element x times.
     *
     * @param array to deSqueeze
     * @return deSqueezed array.
     */
    public static byte[] deSqueeze(byte[] array) {
        if (array == null) {
            return null;
        }

        LinkedList<Byte> answer = new LinkedList<>();
        for (int i = 0; i < array.length - 1; i = i + 2) {
            for (int j = 0; j < (int) array[i]; j++) {
                answer.add(array[i + 1]);
            }
        }
        return toArray(answer);
    }

}
