#include "hashtable.h"
#include "list.h"
#include <iostream>

const int hashSize = 1009;
const int power = 17;

struct HashTable
{
    int size;
    int addedWords;
    List **elements;
};

HashTable *createTable()
{
    HashTable *newTable = new HashTable;
    newTable->size = hashSize;
    newTable->addedWords = 0;

    newTable->elements = new List*[newTable->size];
    for (int i = 0; i < newTable->size; i++)
        newTable->elements[i] = create();

    return newTable;
}

int hash(String *string)
{
    int mod = hashSize;
    int result = 0;
    int factor = 1;

    for (int i = 0; i < stringLength(string); i++)
    {
        result = (result + (factor * (int) getChar(string, i)) % mod) % mod;
        factor = (factor * power) % mod;
    }

    return result;
}

void add(HashTable *hashTable, String *string)
{
    int index = hash(string);
    hashTable->addedWords++;
    addInList(hashTable->elements[index], string);
}

int numberOfElements(HashTable *hashTable)
{
    int answer = 0;
    for (int i = 0; i < hashTable->size; i++)
        if (!isListEmpty(hashTable->elements[i]))
            answer++;
    return answer;
}

int numberOfWords(HashTable *hashTable)
{
    return hashTable->addedWords;
}

void printAll(HashTable *hashTable)
{
    for (int i = 0; i < hashTable->size; i++)
    {
        if (!isListEmpty(hashTable->elements[i]))
        {
            std::cout << i << ": ";
            print(hashTable->elements[i]);
        }
    }
}

void printMax(HashTable *hashTable)
{
    int maxValue = 0;
    int maxElement = 0;
    for (int i = 0; i < hashTable->size; i++)
    {
        if (getListSize(hashTable->elements[i]) > maxValue)
        {
            maxValue = getListSize(hashTable->elements[i]);
            maxElement = i;
        }
    }

    std::cout << maxElement << ": ";
    print(hashTable->elements[maxElement]);
}

int numberOfEmptyElements(HashTable *hashTable)
{
    return hashTable->size - numberOfElements(hashTable);
}

double loadFactor(HashTable *hashTable)
{
    return (double) numberOfElements(hashTable) / hashTable->size;
}


void deleteTable(HashTable *hashTable)
{
    for (int i = 0; i < hashTable->size; i++)
        emptyMemory(hashTable->elements[i]);

    delete[] hashTable->elements;
    delete hashTable;
}

double averageLength(HashTable *hashTable)
{
    int answer = 0;
    for (int i = 0; i < hashTable->size; i++)
        answer += getListSize(hashTable->elements[i]);

    return (double) answer / numberOfElements(hashTable);
}
