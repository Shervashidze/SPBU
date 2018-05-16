package group144.shervashidze;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().filter(string -> string.contains(sequence)).collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        int AMOUNT_OF_ATTEMPTS = 10000000;
        Random random = new Random();

        return (double) IntStream.range(1, AMOUNT_OF_ATTEMPTS).filter(nothing -> {
            double RADIUS = 0.5;
            Double x = random.nextDouble() % 1;
            x = (x >= 0 ? x : -x) - RADIUS;
            Double y = random.nextGaussian() % 1;
            y = (y >= 0 ? y : -y) - RADIUS;

            return x * x + y * y <= RADIUS * RADIUS;
        }).count() / AMOUNT_OF_ATTEMPTS;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream().
                max(Comparator.comparing(author -> author.getValue().stream().mapToInt(String::length).sum()))
                .get().getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(order -> order.entrySet().stream()).
                collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue + newValue));
    }
}
