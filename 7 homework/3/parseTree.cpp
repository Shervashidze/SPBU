#include "parseTree.h"
#include <iostream>
#include <fstream>

using namespace std;


struct Element
{
    char operation;
    int value;
    bool isNumber;
    Element *first;
    Element *second;
};

struct ParseTree
{
    Element *head;
};

ParseTree *createTree()
{
    ParseTree *newTree = new ParseTree;
    newTree->head = nullptr;
    return newTree;
}

int calculate(Element *node)
{
    if (node->isNumber)
        return node->value;

    switch (node->operation)
    {
        case '+':
            return calculate(node->first) + calculate(node->second);

        case '-':
            return calculate(node->first) - calculate(node->second);

        case '*':
            return calculate(node->first) * calculate(node->second);

        case '/':
            return calculate(node->first) / calculate(node->second);

        default:
            cout << "Wrong expression\n";
            return 0;
    }
}

int calculate(ParseTree *tree)
{
    return calculate(tree->head);
}

void printElement(Element *node)
{
    if (node->isNumber)
    {
        cout << node->value;
        return;
    }

    cout << '(';
    printElement(node->first);
    cout << ' ' << node->operation << ' ';
    printElement(node->second);
    cout << ')';
}

void printExpression(ParseTree *tree)
{
    printElement(tree->head);
}

bool isDigit(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}

int toDigit(char symbol)
{
    return symbol - '0';
}

Element *takeElement(ifstream &file)
{
    char currentSymbol = file.get();

    Element *newElement = new Element;

    if (currentSymbol == '(')
    {
        currentSymbol = file.get();

        if (currentSymbol == ' ')
            currentSymbol = '*';
        else
            file.get();

        newElement->isNumber = false;
        newElement->operation = currentSymbol;
        newElement->first = takeElement(file);
        file.get();
        newElement->second = takeElement(file);
        file.get();
    }
    else
    {
        int number = toDigit(currentSymbol);
        currentSymbol = file.get();
        while (isDigit(currentSymbol))
        {
            number = number * 10 + toDigit(currentSymbol);
            currentSymbol = file.get();
        }
        file.unget();
        newElement->isNumber = true;
        newElement->first = nullptr;
        newElement->second = nullptr;
        newElement->value = number;
    }

    return newElement;
}

void takeTree(ParseTree *tree, ifstream &file)
{
    tree->head = takeElement(file);
}

void deleteElement(Element *element)
{
    if (element == nullptr)
        return;

    deleteElement(element->first);
    deleteElement(element->second);
    delete element;
}

void deleteTree(ParseTree *tree)
{
    deleteElement(tree->head);
    delete tree;
}
