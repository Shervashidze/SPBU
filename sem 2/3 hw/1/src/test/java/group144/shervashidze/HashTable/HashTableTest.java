package group144.shervashidze.HashTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {
    @Test
    public void add() {
        HashTable table = new HashTable(new SumHash(42));
        table.add("aloha");
        table.add("aloha");
        table.add("nah");
        assertEquals(2, table.countElements());
    }

    @Test
    public void removeNotExisting() {
        HashTable table = new HashTable(new SumHash(42));
        table.add("Salvation");
        table.remove("HaHa");
        assertEquals(1, table.countElements());
    }

    @Test
    public void removeExisting() {
        HashTable table = new HashTable(new SumHash(42));
        table.add("Smthing");
        table.add("Salvation");
        table.remove("Salvation");
        assertEquals(1, table.countElements());
    }

    @Test
    public void find() {
        HashTable table = new HashTable(new PolynomialHash(2, 42));
        table.add("((");
        assertEquals(36, table.find("(("));
    }

    @Test
    public void getStatistics() {
        HashTable table = new HashTable(new PolynomialHash(7, 11));
        table.add("hallelujah");
        table.add("aloha");
        table.add("hi");
        assertEquals("Amount of elements: 3\n" + "Amount of empty rows: 8\n" + "Load factor: 0,272727\n" +
                "Conflicts: 0\n" + "Max chain length: 1\n", table.getStatistics());
    }

    @Test
    public void switchHash() {
        HashTable table = new HashTable(new SumHash(42));
        table.add("lol");
        table.add("roar");
        table.add("super");
        int size = table.countElements();
        table.switchHash(new PolynomialHash(7,11));
        assertEquals(size, table.countElements());
        assertTrue(table.contains("roar"));
    }

    @Test
    public void getTable() {
        HashTable table = new HashTable(new SumHash(42));
        table.add("aloha");
        table.add("hi");
        assertEquals("Hashtable.\n" + "Size: 42.\n" + "14: aloha\n" + "42: hi\n", table.getTable());
    }

    @Test
    public void countElements() {
        HashTable table = new HashTable(new SumHash(42));
        table.add("Aloha");
        table.add("aloha");
        table.add("nya");
        table.add("in");
        table.add("brightest");
        assertEquals(5, table.countElements());
    }

    @Test
    public void getLoadFactor() {
        HashTable table = new HashTable(new SumHash(2));
        table.add("element");
        assertEquals(0.5, table.getLoadFactor(), 0.001);
    }
}