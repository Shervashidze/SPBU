#include <iostream>
#include <fstream>

#include "hashtable.h"

using namespace std;

const int maxSize = 10000;

int main()
{
    char fileName[maxSize];
    cout << "Enter file name: ";
    cin >> fileName;

    ifstream file(fileName);
    if (!file.is_open())
    {
        cout << "File not found.";
        int c = 0;
        cin >> c;
        return 0;
    }


    HashTable *hashTable = createTable();

    char word[maxSize];
    while (!file.eof())
    {
        file >> word;
        String *newLine = createString(word);
        add(hashTable, newLine);
    }
    file.close();

    cout << "Hashtable Elements:" << '\n';
    printAll(hashTable);
    cout << "Load factor: " << loadFactor(hashTable) << '\n';
    cout << "Maximal Element: " << '\n';
    printMax(hashTable);
    cout << "Number of added words: " << numberOfWords(hashTable) << '\n';
    cout << "Number of empty elements: " << numberOfEmptyElements(hashTable) << '\n';
    cout << "Average chain length: " << averageLength(hashTable);

    deleteTable(hashTable);

    return 0;
}
