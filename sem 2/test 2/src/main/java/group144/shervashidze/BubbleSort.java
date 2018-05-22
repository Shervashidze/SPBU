package group144.shervashidze;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * creates new list as sorted given list with any type and comparator for this type (Bubble sort).
 */
public class BubbleSort {
    /**
     *
     * @param list to sort
     * @param comparator to compare elements of the list.
     * @param <T> type of the stored elements.
     * @return new list with elements(of the given list) in descending order.
     */
    public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        LinkedList<T> answer = new LinkedList<>(list);
        for (int i = 0; i < answer.size(); i++) {
            for (int j = 0; j < answer.size() - 1; j++) {
                if (comparator.compare(answer.get(j), answer.get(j + 1)) < 0) {
                    T temp = answer.get(j);
                    answer.set(j, answer.get(j + 1));
                    answer.set(j + 1, temp);
                }
            }
        }


        return answer;
    }
}
