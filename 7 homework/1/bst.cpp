#include <iostream>
#include "bst.h"

struct BSTNode
{
    int value;
    BSTNode *left;
    BSTNode *right;
};

BinarySearchTree *createTree()
{
    BinarySearchTree *tree = new BinarySearchTree;
    tree->root = nullptr;
    return tree;
}

void clearNode(BSTNode *node)
{
    if (node == nullptr)
        return;

    clearNode(node->left);
    clearNode(node->right);
    delete node;
}

void clearTree(BinarySearchTree *tree)
{
    clearNode(tree->root);
    tree->root = nullptr;
}

bool containsNode(BSTNode *node, int value)
{
    if (node == nullptr)
        return false;

    if (node->value == value)
        return true;

    if (value < node->value)
        return containsNode(node->left, value);
    else
        return containsNode(node->right, value);
}

bool contains(BinarySearchTree *tree, int value)
{
    return containsNode(tree->root, value);
}

BSTNode *createNode(int value)
{
    BSTNode *node = new BSTNode;
    node->value = value;
    node->left = nullptr;
    node->right = nullptr;
    return node;
}

bool addNode(BSTNode *&node, int value)
{
    if (node == nullptr)
    {
        node = createNode(value);
        return true;
    }

    if (node->value == value)
        return false;

    if (value < node->value)
        return addNode(node->left, value);
    else
        return addNode(node->right, value);

}

bool add(BinarySearchTree *tree, int value)
{
    return addNode(tree->root, value);
}

BSTNode *findMax(BSTNode *node)
{
    while (node->right != nullptr)
        node = node->right;
    return node;
}

bool removeNode(BSTNode *&node, int value)
{
    if (node == nullptr)
        return false;

    if (node->value == value)
    {
        if (node->left == nullptr && node->right == nullptr)
        {
            delete node;
            node = nullptr;
        }
        else if (node->left == nullptr)
        {
            BSTNode *temp = node->right;
            delete node;
            node = temp;
        }
        else if (node->right == nullptr)
        {
            BSTNode *temp = node->left;
            delete node;
            node = temp;
        }
        else
        {
            BSTNode *maxNode = findMax(node->left);
            int maxValue = maxNode->value;
            removeNode(node, maxValue);
            node->value = maxValue;
        }
        return true;
    }

    if (value < node->value)
        return removeNode(node->left, value);
    else
        return removeNode(node->right, value);
}

bool remove(BinarySearchTree *tree, int value)
{
    return removeNode(tree->root, value);
}

void printNode(BSTNode *node)
{
    if (node == nullptr)
        return;

    printNode(node->left);
    std::cout << node->value << " ";
    printNode(node->right);
}

void print(BinarySearchTree *tree)
{
    printNode(tree->root);
}

void desPrintNode(BSTNode *node)
{
    if (node == nullptr)
        return;

    desPrintNode(node->right);
    std::cout << node->value << " ";
    desPrintNode(node->left);
}

void descendingPrint(BinarySearchTree *tree)
{
    desPrintNode(tree->root);
}

void errorPrintNode(BSTNode *node)
{
    if (node == nullptr)
    {
        std::cout << "null ";
        return;
    }

    std::cout << "( " << node->value << " ";
    errorPrintNode(node->left);
    errorPrintNode(node->right);
    std::cout << ") ";
}

void errorSearchPrint(BinarySearchTree *tree)
{
    errorPrintNode(tree->root);
}
