package group144.shervashidze;

import org.junit.Test;

import java.lang.reflect.AnnotatedArrayType;
import java.util.*;

import static org.junit.Assert.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        List<String> list = Arrays.asList("aaaa", "aaab", "aaac", "aaad", "sad", "sa", "bboring", "bbull", "bbwow");
        assertEquals(Arrays.asList("aaaa", "aaab", "aaac", "aaad"), SecondPartTasks.findQuotes(list, "aa"));
        assertEquals(Arrays.asList("sad", "sa"), SecondPartTasks.findQuotes(list, "s"));
        assertEquals(Arrays.asList("bboring", "bbull", "bbwow"), SecondPartTasks.findQuotes(list, "bb"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, SecondPartTasks.piDividedBy4(), 0.001);
    }

    @Test
    public void testFindPrinter() {
        List<String> belyaev = Arrays.asList("prodavec voszduha", "chelovek-amphibiya");
        List<String> water = Arrays.asList("Voina i Mir", "Tihiy Don");
        List<String> kathy = Arrays.asList("ROAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");

        Map<String, List<String>> things = new HashMap<>();
        things.put("belyaev", belyaev);
        things.put("water", water);
        assertEquals("belyaev", SecondPartTasks.findPrinter(things));

        things.put("kathy", kathy);
        assertEquals("kathy", SecondPartTasks.findPrinter(things));
    }

    @Test
    public void testCalculateGlobalOrder() {
        List<Map<String, Integer>> orders = new ArrayList<>();
        orders.add(new HashMap<>());
        orders.add(new HashMap<>());
        orders.add(new HashMap<>());
        orders.add(new HashMap<>());

        orders.get(0).put("Bananas", 10);
        orders.get(0).put("Apples", 10);
        orders.get(0).put("Chairs", 10);

        orders.get(1).put("Chairs", 42);
        orders.get(1).put("People", 10000);

        orders.get(2).put("Bananas", 42);
        orders.get(2).put("Apples", 42);

        orders.get(3).put("Something new", 1);

        HashMap<String, Integer> answer = new HashMap<>();
        answer.put("Apples", 52);
        answer.put("Bananas", 52);
        answer.put("Chairs", 52);
        answer.put("People", 10000);
        answer.put("Something new", 1);

        assertEquals(answer, SecondPartTasks.calculateGlobalOrder(orders));
    }
}