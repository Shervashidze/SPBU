#include "hashtable.h"
#include <fstream>
#include <iostream>

const int hashSize = 1009;
const int power = 17;

struct Element
{
    Element *nextElement;
    int amount;
    String *string;
};

struct HashTable
{
    int size;
    int addedWords;
    int notEmtyElements;
    Element **elements;
};

HashTable *createTable()
{
    HashTable *newTable = new HashTable;
    newTable->size = hashSize;
    newTable->addedWords = 0;
    newTable->notEmtyElements = 0;

    newTable->elements = new Element*[newTable->size];
    for (int i = 0; i < newTable->size; i++)
        newTable->elements[i] = nullptr;

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

    Element *temp = hashTable->elements[index];
    if (temp == nullptr)
        hashTable->notEmtyElements++;

    while (temp != nullptr)
    {
        if (isEqual(temp->string, string))
        {
            temp->amount++;
            deleteString(string);
            return;
        }
        temp = temp->nextElement;
    }

    Element *newElement = new Element;
    newElement->amount = 1;
    newElement->string = string;
    newElement->nextElement = hashTable->elements[index];
    hashTable->elements[index] = newElement;
}

int numberOfElements(HashTable *hashTable)
{
    return hashTable->notEmtyElements;
}

int numberOfWords(HashTable *hashTable)
{
    return hashTable->addedWords;
}

void printElement(HashTable *hashTable, int number)
{
    Element *temp = hashTable->elements[number];
    if (temp != nullptr)
        std::cout << number << ": ";
    while (temp != nullptr)
    {
        char *line = toChar(temp->string);
        std::cout << line << " - " <<  hashTable->elements[number]->amount << "; ";
        delete[] line;
        temp = temp->nextElement;
    }
    if (hashTable->elements[number] != nullptr)
        std::cout << '\n';
}

void printAll(HashTable *hashTable)
{
    for (int i = 0; i < hashTable->size; i++)
        printElement(hashTable, i);
}

void printMax(HashTable *hashTable)
{
    int maxValue = 0;
    int maxElement = 0;
    for (int i = 0; i < hashTable->size; i++)
    {
        int current = 0;
        Element *temp = hashTable->elements[i];
        while (temp != nullptr)
        {
            current += temp->amount;
            temp = temp->nextElement;
        }
        if (current > maxValue)
        {
            maxValue = current;
            maxElement = i;
        }
    }
    printElement(hashTable, maxElement);
}

int numberOfEmptyElements(HashTable *hashTable)
{
    return hashTable->size - hashTable->notEmtyElements;
}

double loadFactor(HashTable *hashTable)
{
    return (double) numberOfElements(hashTable) / hashTable->size;
}

void deleteElements(Element *element)
{
    if (element == nullptr)
        return;

    deleteElements(element->nextElement);
    deleteString(element->string);
    delete element;
}

void deleteTable(HashTable *hashTable)
{
    for (int i = 0; i < hashTable->size; i++)
        deleteElements(hashTable->elements[i]);

    delete[] hashTable->elements;
    delete hashTable;
}

double averageLength(HashTable *hashTable)
{
    int answer = 0;
    for (int i = 0; i < hashTable->size; i++)
    {
        if (hashTable->elements[i] != nullptr)
        {
            int counter = 0;
            Element *temp = hashTable->elements[i];
            while (temp != nullptr)
            {
                counter++;
                temp = temp->nextElement;
            }

            answer += counter;
        }
    }

    return (double) answer / hashTable->notEmtyElements;
}
