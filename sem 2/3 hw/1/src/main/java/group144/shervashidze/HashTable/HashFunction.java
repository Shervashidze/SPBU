package group144.shervashidze.HashTable;

public interface HashFunction {
    int hash(String string);
    int getMod();
}
