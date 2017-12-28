#pragma once
#include "string.h"

struct HuffmanTree;

struct HuffmanCode
{
    HuffmanTree *tree;
    String **codes;
};

int frequency(HuffmanTree *huffmanTree);
void deleteHuffmanTree(HuffmanTree *huffmanTree);
void deleteHuffmanCode(HuffmanCode *huffmanCode);
void printHuffmanTree(HuffmanCode *huffmanCode, std::ofstream &stream);
HuffmanCode *encode(String *string);
String *getCode(HuffmanCode *huffmanCode, char symbol);
void decode(std::istream &input, std::ostream &output);
