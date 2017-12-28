#include "hashtable.h"
#include <fstream>
#include <iostream>

const int hashSize = 10009;
const int power = 17;

struct Element
{
    Element *nextElement;
    int amount;
    String *string;
};

struct HashTable
{
    int size = hashSize;
    Element **elements;
};

HashTable *createTable()
{
    HashTable *newTable = new HashTable;

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

    Element *temp = hashTable->elements[index];
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

bool contains(HashTable *hashTable, String *string)
{
    int index = hash(string);

    Element *temp = hashTable->elements[index];
    while (temp != nullptr)
    {
        if (isEqual(temp->string, string))
            return true;
        temp = temp->nextElement;
    }
    return false;
}


int numberOfElements(HashTable *hashTable)
{
    int result = 0;
    for (int i = 0; i < hashTable->size; i++)
    {
        Element *temp = hashTable->elements[i];
        while (temp != nullptr)
        {
            result++;
            temp = temp->nextElement;
        }
    }
    return result;
}

int numberOfWords(HashTable *hashTable)
{
    int result = 0;
    for (int i = 0; i < hashTable->size; i++)
    {
        Element *temp = hashTable->elements[i];
        while (temp != nullptr)
        {
            result += temp->amount;
            temp = temp->nextElement;
        }
    }

    return result;
}

void printElement(HashTable *hashTable, int number)
{
    Element *temp = hashTable->elements[number];
    while (temp != nullptr)
    {
        char *line = toChar(temp->string);
        std::cout << number << ": " << line << " - " <<  hashTable->elements[number]->amount << ' ';
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
            current++;
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
    int result = 0;
    for (int i = 0; i < hashTable->size; i++)
        if(hashTable->elements[i] == nullptr)
            result++;

    return result;
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
