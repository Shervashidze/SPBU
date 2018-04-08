package group144.shervashidze.HashTable;

public class PolynomialHash implements HashFunction {
    private int power;
    private int mod;

    /**
     *
     * @param newPower power of the polynom(x in a*x^n + b*x^(n-1) + ...).
     * @param module mod of the hash function.
     * @throws IllegalArgumentException when power or mod is less than 1.
     */
    public PolynomialHash(int newPower, int module) throws IllegalArgumentException {
        if (module < 1 || newPower < 1)
            throw new IllegalArgumentException();
        power = newPower;
        mod = module;
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
     * i = 0, from right to left we are taking value of character from string and multiplies it by power^i, i++.
     */
    @Override
    public int hash(String string) {
        int answer = 0;
        for (int i = 0; i < string.length(); i++)
            answer = ((answer * power) % mod + (string.charAt(i)) % mod) % mod;
        return answer;
    }
}
