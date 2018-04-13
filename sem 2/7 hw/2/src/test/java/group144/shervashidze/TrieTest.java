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
    public void serialize() throws IOException {
        File file = new File("IHopeThereWontBeTHisName.txt");
        FileOutputStream out = new FileOutputStream(file);
        Serialize.serialize(trie, out);
    }

    @Test
    public void deserialize() throws IOException, ClassNotFoundException {
        Serialize.serialize(trie, new FileOutputStream(new File("TestDeserializeFile.txt")));
        FileInputStream in = new FileInputStream("TestDeserializeFile.txt");
        Trie newOne = (Trie) Serialize.deserialize(in);
        OutputStream trieStream = new ByteArrayOutputStream();
        OutputStream newOneStream = new ByteArrayOutputStream();
        trie.print(trieStream);
        newOne.print(newOneStream);
        assertEquals(trieStream.toString(), newOneStream.toString());
    }

    @Test
    public void print() {
        Trie test = new Trie();
        test.add("aaa");
        test.add("aaaa");
        test.add("aaab");
        test.add("a");
        test.add("b");
        test.add("d");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        test.print(out);
        assertEquals("a aaa aaaa aaab b d ", out.toString());
    }
}