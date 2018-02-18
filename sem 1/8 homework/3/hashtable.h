#pragma once

#include "string.h"


struct HashTable;

HashTable *createTable();

void add(HashTable *hashTable, String *string);
int numberOfWords(HashTable *hashTable);
int numberOfElements(HashTable *hashTable);
int numberOfEmptyElements(HashTable *hashTable);
double averageLength(HashTable *hashTable);
double loadFactor(HashTable *hashTable);
void printMax(HashTable *hashTable);
void printAll(HashTable *hashTable);

void deleteTable(HashTable *hashTable);
