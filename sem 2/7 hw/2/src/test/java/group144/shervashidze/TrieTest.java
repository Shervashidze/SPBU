package group144.shervashidze;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class TrieTest {
    private Trie trie;

    @Before
    public void init() {
        trie = new Trie();
        trie.add("In");
        trie.add("brightest");
        trie.add("day");
        trie.add("in");
        trie.add("blackest");
        trie.add("night");
    }

    @Test
    public void size() {
        assertEquals(6, trie.size());
    }

    @Test
    public void add() {
        assertFalse(trie.add("in"));
        assertFalse(trie.add(null));
        assertTrue(trie.add("aloha"));
        assertEquals(7, trie.size());
    }

    @Test
    public void remove() {
        assertFalse(trie.remove(null));
        assertTrue(trie.remove("In"));
        assertTrue(trie.remove("in"));
        assertFalse(trie.remove("nothing"));
        assertEquals(4, trie.size());
        trie.add("bra");
        trie.add("bre");
        trie.remove("bra");
        assertEquals(5, trie.size());
    }

    @Test
    public void contains() {
        assertTrue(trie.contains("night"));
        assertTrue(trie.contains("day"));
        assertFalse(trie.contains("lol"));
    }

    @Test
    public void howManyStartWithPrefix() {
        assertEquals(2, trie.howManyStartWithPrefix("b"));
        trie.add("holyshirt");
        trie.add("holy");
        trie.add("hola");
        assertEquals(3, trie.howManyStartWithPrefix("hol"));
        assertEquals(2, trie.howManyStartWithPrefix("holy"));
        trie.add("hol");
        assertEquals(4, trie.howManyStartWithPrefix("hol"));
        trie.remove("hol");
        assertEquals(3, trie.howManyStartWithPrefix("hol"));
    }

    @Test
    public void serializeAndDeserialize() throws IOException {
        File file = new File("IHopeThereWontBeTHisName.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("a b c d aa aaaa aaaas and");
        writer.close();
        trie.deserialize(new FileInputStream("IHopeThereWontBeTHisName.txt"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        trie.serialize(out);
        file.delete();
        assertEquals("Amount of elements: 8\n" +
                "Elements: a aa aaaa aaaas and b c d ", out.toString());
    }
}