package group144.shervashidze;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Trie.
 * Stores strings.
 */
public class Trie {
    private Element root;

    /**
     * Places empty element in root.
     */
    public Trie() {
        root = new Element();
    }

    /**
     * Amount of the strings in trie.
     * @return amount of words.
     */
    public int size() {
        return root.startWith;
    }

    /**
     * Add.
     *
     * @param string to add in trie.
     * @return false when string already stored here, true otherwise.
     * Works O(2 * |string|).
     */
    public boolean add(String string) {
        Element temp = root;
        if (string == null || contains(string))
            return false;

        char[] chars = string.toCharArray();
        int counter = 0;

        temp.startWith++;
        while (counter < chars.length) {
            if (!temp.children.containsKey(chars[counter])) {
                insertChar(temp, chars[counter]);
            }

            temp = temp.children.get(chars[counter]);
            temp.startWith++;
            if (temp.text.equals(string) && !temp.isWord) {
                temp.isWord = true;
                return true;
            }

            counter++;
        }

        return false;
    }

    /**
     * Remove.
     *
     * @param string to remove from trie.
     * @return false if there is no this string in trie, true otherwise.
     * Works O(2 * |string|).
     */
    public boolean remove(String string) {
        if (!contains(string)) {
            return false;
        }

        Element temp = root;
        root.startWith--;
        for (int i = 0; i < string.length(); i++) {
            char value = string.charAt(i);
            Element parent = temp;
            temp = temp.children.get(value);
            temp.startWith--;
            if (temp.startWith == 0) {
                parent.children.remove(value);
                return true;
            }
        }

        return false;
    }

    /**
     * Contains.
     *
     * @param string to find in trie.
     * @return True if this string exists, false otherwise.
     * Works O(|string|).
     */
    public boolean contains(String string) {
        if (string == null) {
            return false;
        }

        char[] chars = string.toCharArray();
        Element temp = root;
        int counter = 0;
        while (counter < chars.length) {
            if (!temp.children.containsKey(chars[counter])) {
                return false;
            } else {
                temp = temp.children.get(chars[counter]);
                counter++;
            }
        }

        return temp.isWord;
    }

    /**
     * howManyStartWithPrefix.
     *
     * @param string - prefix to find.
     * @return amount of elements which stats with this prefix(just prefix, if it is word, counts).
     * Works O(|prefix|).
     */
    public int howManyStartWithPrefix(String string) {
        if (string == null) {
            return 0;
        }

        Element temp = root;
        for (int i = 0; i < string.length(); i++) {
            if (!temp.children.containsKey(string.charAt(i))) {
                return  0;
            } else {
                temp = temp.children.get(string.charAt(i));
            }
        }

        return temp.startWith;
    }

    /**
     * Serialize.
     *
     * @param out stream to write trie in.
     * @throws IOException when we cant write there.
     * Writes. Amount of elements: (int)\n + Elements: string1 string2 ...
     */
    public void serialize(OutputStream out) throws IOException {
        PrintStream printStream = new PrintStream(out);
        printStream.print("Amount of elements: " + root.startWith + "\n");
        printStream.print("Elements: ");
        print(root, printStream);
    }

    /**
     * Print
     * Private class to print elements of trie.
     *
     * @param element to print if it is word. Start print from all his children.
     * @param out - stream to print.
     */
    private void print(Element element, PrintStream out) {
        if (element.isWord) {
            out.print(element.text);
            out.print(" ");
        }
        for (Element element1 : element.children.values()) {
            print(element1, out);
        }
    }

    /**
     * Deserialize.
     * Destroying old tire and creates new one. Adding all words(separated by " ") from input stream.
     *
     * @param in - stream to take words.
     * @throws IOException when we cant do this.
     */
    public void deserialize(InputStream in) throws IOException {
        root = new Element();
        Scanner s = new Scanner(in).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        String[] words = result.split(" ");
        for (String word : words) {
            this.add(word);
        }
    }

    /**
     * insertChar.
     * Private method to create new child of element. Sets text of the child as text of the parent + char value
     * value also edge connecting child and parent.
     * @param element - element to which we are creating a new child.
     * @param value(type Character) - key to the hash map, value of the edge and etc.
     */
    private void insertChar(Element element, Character value) {
        if (element.children.containsKey(value)) {
            return;
        }

        element.children.put(value, new Element(element.text + value.toString()));
    }


    /**
     * Element
     *
     * text - values of all previous edges.
     * children - hashMap of existing edges.
     * isWord - true when values of all previous edges form a word, false otherwise.
     * startWith - amount of the words, which starts with text as prefix.
     */
    private class Element {
        private String text;
        private HashMap<Character, Element> children;
        boolean isWord;
        int startWith;

        Element() {
            children = new HashMap<>();
            text = "";
            isWord = false;
            startWith = 0;
        }

        Element(String text) {
            this();
            this.text = text;
        }
    }
}
