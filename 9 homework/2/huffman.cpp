#include "huffman.h"
#include "priorityqueue.h"

using namespace std;

int const scatterOfSymbols = 256;

struct HuffmanTree
{
    HuffmanTree *left;
    HuffmanTree *right;
    bool isOnlyValue;
    char value;
    int frequency;
};

HuffmanTree *createLeaf(char symbol, int frequency)
{
    HuffmanTree *newLeaf = new HuffmanTree;
    newLeaf->value = symbol;
    newLeaf->frequency = frequency;
    newLeaf->left = nullptr;
    newLeaf->right = nullptr;
    newLeaf->isOnlyValue = true;
    return newLeaf;
}

HuffmanTree *mergeTrees(HuffmanTree *firstTree, HuffmanTree *secondTree)
{
    HuffmanTree *newTree = new HuffmanTree;
    newTree->left = firstTree;
    newTree->right = secondTree;
    newTree->isOnlyValue = false;
    newTree->frequency = firstTree->frequency + secondTree->frequency;
    return newTree;
}

void deleteHuffmanTree(HuffmanTree *huffmanTree)
{
    if (huffmanTree == nullptr)
        return;

    deleteHuffmanTree(huffmanTree->left);
    deleteHuffmanTree(huffmanTree->right);
    delete huffmanTree;
}

void getCodes(HuffmanTree *huffmanTree, String **codes, String *currentCode)
{
    if (huffmanTree->isOnlyValue)
    {
        if (stringLength(currentCode) == 0)
        {
            char leftSuffixLine[2] = {'0', '\0'};
            String *leftSuffix = createString(leftSuffixLine);
            concatenation(currentCode, leftSuffix);
            deleteString(leftSuffix);
        }
        codes[(int) huffmanTree->value] = currentCode;
        return;
    }

    String *leftCode = currentCode;
    String *rightCode = clone(currentCode);
    char leftSuffixLine[2] = "0";
    char rightSuffixLine[2] = "1";
    String *leftSuffix = createString(leftSuffixLine);
    String *rightSuffix = createString(rightSuffixLine);
    concatenation(leftCode, leftSuffix);
    concatenation(rightCode, rightSuffix);
    deleteString(leftSuffix);
    deleteString(rightSuffix);

    getCodes(huffmanTree->left, codes, leftCode);
    getCodes(huffmanTree->right, codes, rightCode);
}

String **codes(HuffmanTree *huffmanTree)
{
    String **codes = new String*[scatterOfSymbols];
    for (int i = 0; i < scatterOfSymbols; i++)
        codes[i] = nullptr;

    char emptyLine[1] = {'\0'};
    getCodes(huffmanTree, codes, createString(emptyLine));
    return codes;
}

int frequency(HuffmanTree *huffmanTree)
{
    return huffmanTree->frequency;
}

HuffmanCode *encode(String *string)
{
    int frequencies[scatterOfSymbols];
    for (int i = 0; i < scatterOfSymbols; i++)
        frequencies[i] = 0;

    for (int i = 0; i < stringLength(string); i++)
        frequencies[(int) getChar(string, i)]++;

    Queue *priorityQueue = createQueue();
    for (int i = 0; i < scatterOfSymbols; i++)
    {
        if (frequencies[i] > 0)
        {
            HuffmanTree *newLeaf = createLeaf((char) i, frequencies[i]);
            append(priorityQueue, newLeaf);
        }
    }

    while (!isAlone(priorityQueue))
    {
        HuffmanTree *firstTree = pop(priorityQueue);
        HuffmanTree *secondTree = pop(priorityQueue);
        HuffmanTree *merged = mergeTrees(firstTree, secondTree);
        append(priorityQueue, merged);
    }

    HuffmanTree *huffmanTree = pop(priorityQueue);
    deleteQueue(priorityQueue);

    String **code = codes(huffmanTree);

    HuffmanCode *huffmanCode = new HuffmanCode;
    huffmanCode->codes = code;
    huffmanCode->tree = huffmanTree;

    return huffmanCode;
}

String *getCode(HuffmanCode *huffmanCode, char symbol)
{
    return huffmanCode->codes[(int)symbol];
}

void printTree(HuffmanCode *huffmanCode, HuffmanTree *huffmanTree, ostream &stream)
{
    if (huffmanTree->isOnlyValue)
    {
        stream << "{" << huffmanTree->value << "}";
        return;
    }
    stream << "(";
    printTree(huffmanCode, huffmanTree->left, stream);
    printTree(huffmanCode, huffmanTree->right, stream);
    stream << ")";
}

void printHuffmanTree(HuffmanCode *huffmanCode, ofstream &stream)
{
    printTree(huffmanCode, huffmanCode->tree, stream);
}

void deleteHuffmanCode(HuffmanCode *huffmanCode)
{
    deleteHuffmanTree(huffmanCode->tree);
    for (int i = 0; i < scatterOfSymbols; i++)
    {
        if (huffmanCode->codes[i] != nullptr)
            deleteString(huffmanCode->codes[i]);
    }
    delete[] huffmanCode->codes;
    delete huffmanCode;
}
