package group144.shervashidze;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        fail();
    }
}