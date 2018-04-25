package group144.shervashidze.HashTable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Hash table.
 *
 * Stores strings.
 */
public class HashTable {
    private HashFunction hashFunction;
    private ArrayList<LinkedList<String>> elements;

    /**
     * HashTable.
     *
     * @param hashFunction, mod of hashFunction is also mod of hashTable.
     */
    public HashTable (HashFunction hashFunction) {
        this.hashFunction = hashFunction;

        this.elements = new ArrayList<>(hashFunction.getMod());
        for (int i = 0; i < hashFunction.getMod(); i++) {
            this.elements.add(new LinkedList<>());
        }
    }

    /**
     * @param string to add in hashTable.
     * row to add: value of hashFunction with this string.
     */
    public void add(String string) {
        if (contains(string))
            return;
        int hash = hashFunction.hash(string);
        elements.get(hash).add(string);
    }

    /**
     * @param string to remove from hashTable.
     * If there is't this string does nothing.
     */
    public void remove(String string) {
        if (!contains(string))
            return;
        int hash = hashFunction.hash(string);
        elements.get(hash).remove(string);
    }

    /**
     *
     * @param string to find.
     * @return in which row string is.
     */
    public int find(String string) {
        int hash = hashFunction.hash(string);
        if (contains(string)) {
            return hash;
        } else {
            return -1;
        }
    }

    /**
     *
     * @return statistic: Load factor, amount of conflicts, elements and empty rows, max chain length.
     */
    public String getStatistics() {
        int amount = countElements();
        int empty = countEmptyRows();

        int maxChainLength = 0;
        for (LinkedList<String> row : elements)
            maxChainLength = Integer.max(maxChainLength, row.size());

        int notAlone = 0;
        for (LinkedList<String> row : elements)
            if (row.size() > 1)
                notAlone++;

        String loadFactorInfo = String.format("Load factor: %f", getLoadFactor());
        String conflicts = String.format("Conflicts: %d", notAlone);
        String elements = String.format("Amount of elements: %d", amount);
        String emptyRows = String.format("Amount of empty rows: %d", empty);
        String maxLength = String.format("Max chain length: %d", maxChainLength);
        return String.join("\n", elements, emptyRows, loadFactorInfo, conflicts, maxLength, "");
    }

    /**
     *
     * @return amount of empty rows.
     */
    private int countEmptyRows() {
        int result = 0;
        for (LinkedList<String> row : elements)
            if (row.size() == 0)
                result++;
        return result;
    }

    /**
     *
     * @param newFunction - new Hash function to switch in.
     */
    public void switchHash(HashFunction newFunction) {
        ArrayList<LinkedList<String>> oldElements = elements;
        elements = new ArrayList<>(newFunction.getMod());
        hashFunction = newFunction;
        for (int i = 0; i < newFunction.getMod(); i++) {
            elements.add(new LinkedList<>());
        }

        for (LinkedList<String> row : oldElements) {
            for (String value : row) {
                add(value);
            }
        }
    }

    /**
     *
     * @return size of hash table and not empty rows with elements.
     */
    public String getTable() {
        String result = "";
        result += (String.format("Hashtable.\nSize: %d.\n", hashFunction.getMod()));
        ArrayList<String> rows = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            LinkedList<String> row = elements.get(i);
            if (row.size() == 0)
                continue;
            String curRow = String.format("%d: %s", i + 1, String.join(", ", row));
            rows.add(curRow);
        }
        result = result + (String.join("\n", rows));
        return result + "\n";
    }

    /**
     *
     * @param string to find in hash table.
     * @return true if string in hash table. False otherwise.
     */
    public boolean contains(String string) {
        int hash = hashFunction.hash(string);
        return elements.get(hash).contains(string);
    }

    /**
     *
     * @return amount of elements in hash table.
     */
    public int countElements() {
        int result = 0;
        for (LinkedList<String> row : elements)
            result += row.size();
        return result;
    }

    /**
     *
     * @return load factor.
     * load factor is all elements in hashTable divided by mod.
     */
    public double getLoadFactor() {
        return ((double) countElements()) / (hashFunction.getMod());
    }
}
