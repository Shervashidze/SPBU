#include <fstream>
#include <iostream>
#include "string.h"
#include "huffman.h"

using namespace std;

const int maxSize = 100000;

int main()
{
    char fileName[maxSize];
    cout << "Enter input file name: ";
    cin >> fileName;

    ifstream file(fileName);
    if (!file.is_open())
    {
        cout << "File not found.";
        int c = 0;
        cin >> c;
        return 0;
    }

    char slashN[2] = {"\n"};
    String *separator = createString(slashN);
    char *buffer = new char[maxSize];
    file.getline(buffer, maxSize);
    String *line = createString(buffer);
    while (!file.eof())
    {
        concatenation(line, separator);
        file.getline(buffer, maxSize);
        String *newLine = createString(buffer);
        concatenation(line, newLine);
        deleteString(newLine);
    }
    deleteString(separator);
    delete[] buffer;
    file.close();

    HuffmanCode *huffmanCode = encode(line);
    cout << "Enter output file name: ";
    cin >> fileName;
    ofstream output(fileName);

    printHuffmanTree(huffmanCode, output);
    output << '\n';

    for (int i = 0; i < stringLength(line); i++)
        printString(getCode(huffmanCode, getChar(line, i)), output);

    deleteHuffmanCode(huffmanCode);
    deleteString(line);

    output.close();
    return 0;
}
