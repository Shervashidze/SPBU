package group144.shervashidze.HashTable;

public class SumHash implements HashFunction {
    private int mod;

    /**
     *
     * @param newMod mod of the hash function.
     * @throws IllegalArgumentException when newMod less than 1.
     */
    public SumHash(int newMod) throws IllegalArgumentException {
        if (newMod < 1) {
            throw new IllegalArgumentException();
        }

        mod = newMod;
    }

    /**
     *
     * @return mod of the hash function.
     */
    @Override
    public int getMod() {
        return mod;
    }

    /**
     *
     * @param string to find hash.
     * @return hash function from this string.
     * We are summing up all values of the characters in string.
     */
    @Override
    public int hash(String string) {
        int answer = 0;

        for (int i = 0; i < string.length(); i++) {
            answer += string.charAt(i);
            answer = answer % mod;
        }

        return answer;
    }
}
