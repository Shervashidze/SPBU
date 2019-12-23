package group144.shervashidze;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class AVLTreeCollectionTest {

    @Test
    public void addSome() {
        AVLTreeCollection<Integer> set = new AVLTreeCollection<>();

        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        assertEquals("(2:2 (1:2 null null) (3:1 null null))", set.print());
        set.add(4);
        set.add(5);
        assertEquals("(2:2 (1:2 null null) (4:1 (3:1 null null) (5:1 null null)))", set.print());
    }

    @Test
    public void addRepetitive() {
        AVLTreeCollection<Integer> set = new AVLTreeCollection<>();

        set.add(1);
        set.add(2);
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals("(2:2 (1:2 null null) (3:1 null null))", set.print());
    }

    @Test
    public void removeSome() {
        AVLTreeCollection<Integer> set = new AVLTreeCollection<>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        set.remove(2);
        assertEquals("(4:1 (1:1 null (3:1 null null)) (5:1 null null))", set.print());
        set.remove(5);
        assertEquals("(3:1 (1:1 null null) (4:1 null null))", set.print());
        set.add(5);
        set.remove(3);
        assertEquals("(4:1 (1:1 null null) (5:1 null null))", set.print());
    }

    @Test
    public void removeRepetitive() {
        AVLTreeCollection<Integer> set = new AVLTreeCollection<>();

        set.add(1);
        set.add(2);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(3);

        set.remove(1);
        assertEquals("(2:2 (1:2 null null) (3:1 null null))", set.print());

        set.remove(2);
        set.remove(2);
        assertEquals("(1:2 null (3:1 null null))", set.print());
    }

    @Test
    public void sizeTest() {
        AVLTreeCollection<Integer> set = new AVLTreeCollection<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(2);
        set.add(2);
        set.add(2);
        assertEquals(7, set.size());
        set.remove(2);
        set.remove(3);
        set.remove(1);
        assertEquals(4, set.size());
    }

    @Test
    public void isEmptyTest() {
        AVLTreeCollection<Integer> set = new AVLTreeCollection<>();
        assertTrue(set.isEmpty());
        set.add(1);
        assertFalse(set.isEmpty());
        set.remove(1);
        assertTrue(set.isEmpty());
    }

    @Test
    public void containsTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        assertFalse(set.contains("roar"));
        set.add("roar");
        assertTrue(set.contains("roar"));
        set.add("alahu akbar");
        assertTrue(set.contains("alahu akbar"));
        assertFalse(set.contains("Roar"));
    }

    @Test
    public void iteratorTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.add("S");
        set.add("w");
        set.add("d");
        Iterator<String> avlTreeIterator = set.iterator();
        assertTrue(avlTreeIterator.hasNext());
        assertEquals(avlTreeIterator.next(), "S");
        assertEquals(avlTreeIterator.next(), "d");
        assertTrue(avlTreeIterator.hasNext());
        assertEquals(avlTreeIterator.next(), "w");
        assertFalse(avlTreeIterator.hasNext());
    }

    @Test
    public void multipleIteratorsTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.add("S");
        set.add("a");
        set.add("w");
        set.add("d");
        Iterator<String> firstIterator = set.iterator();
        Iterator<String> secondIterator = set.iterator();
        assertTrue(firstIterator.hasNext());
        assertEquals(firstIterator.next(), "S");
        firstIterator.remove();
        assertEquals(firstIterator.next(), "d");
        firstIterator.remove();
        assertFalse(firstIterator.hasNext());

        assertTrue(secondIterator.hasNext());
        assertEquals(secondIterator.next(), "S");
        assertEquals(secondIterator.next(), "d");
        assertFalse(secondIterator.hasNext());
    }

    @Test
    public void toArrayTest() {
        AVLTreeCollection<Integer> set = new AVLTreeCollection<>();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(5);
        set.add(3);
        assertArrayEquals(new Integer[]{1, 1, 2, 3, 5}, set.toArray());
    }

    @Test
    public void toArrayStringTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.add("a");
        set.add("b");
        set.add("a");
        set.add("d");
        set.add("c");
        assertArrayEquals(new String[]{"a", "a", "b", "c", "d"}, set.toArray());
    }

    @Test
    public void containsAllTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.add("a");
        set.add("b");
        set.add("c");
        assertTrue(set.containsAll(Arrays.asList("a", "c")));
        assertFalse(set.containsAll(Arrays.asList("a", "g")));
    }

    @Test
    public void addAllTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.addAll(Arrays.asList("abcde".split("")));
        assertTrue(set.contains("c"));
        assertTrue(set.containsAll(Arrays.asList("a", "c", "e")));
        assertFalse(set.containsAll(Arrays.asList("a", "g", "h")));
        assertTrue(set.containsAll(Arrays.asList("a", "b", "c", "d", "e")));
    }

    @Test
    public void removeAllTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.addAll(Arrays.asList("abcde".split("")));
        assertTrue(set.removeAll(Arrays.asList("a", "c", "e")));
        assertEquals(2, set.size());
    }

    @Test
    public void retainAllTest() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.addAll(Arrays.asList("abcde".split("")));
        assertTrue(set.retainAll(Arrays.asList("a", "e")));
        assertFalse(set.retainAll(Arrays.asList("a", "e", "h")));
        assertTrue(set.retainAll(Arrays.asList("c", "d")));
        assertEquals(1, set.size());
    }

    @Test
    public void clear() {
        AVLTreeCollection<String> set = new AVLTreeCollection<>();
        set.addAll(Arrays.asList("abcde".split("")));
        set.clear();
        assertEquals(0, set.size());
        assertFalse(set.contains("a"));
        assertFalse(set.contains("b"));
        assertFalse(set.contains("c"));
        assertFalse(set.contains("d"));
        assertFalse(set.contains("e"));
    }
}