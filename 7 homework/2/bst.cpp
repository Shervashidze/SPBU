#include <iostream>
#include "bst.h"

struct BSTNode
{
    int value;
    int height;
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

int height(BSTNode* node)
{
    return (node == nullptr ? 0 : node->height);
}

int bfactor(BSTNode* node)
{
    return height(node->right) - height(node->left);
}

void fixheight(BSTNode* node)
{
    int left = height(node->left);
    int right = height(node->right);
    node->height = (left > right ? left : right) + 1;
}

void rotateright(BSTNode *&node)
{
    BSTNode* temp = node->left;
    node->left = temp->right;
    temp->right = node;
    fixheight(node);
    fixheight(temp);
    node = temp;
}

void rotateleft(BSTNode *&node)
{
    BSTNode* temp = node->right;
    node->right = temp->left;
    temp->left = node;
    fixheight(node);
    fixheight(temp);
    node = temp;
}

void balance(BSTNode *&node)
{
    fixheight(node);
    int balanceFavtor = bfactor(node);
    if (balanceFavtor == 2)
    {
        if (bfactor(node->right) < 0)
            rotateright(node->right);
        rotateleft(node);
    }
    else if (balanceFavtor == -2)
    {
        if (bfactor(node->left) > 0)
            rotateleft(node->left);
        rotateright(node);
    }
}

void clearTree(BinarySearchTree *tree)
{
    clearNode(tree->root);
    delete tree;
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
        balance(node);
        return true;
    }

    if (node->value == value)
        return false;

    bool answer = false;
    if (value < node->value)
        answer = addNode(node->left, value);
    else
        answer = addNode(node->right, value);

    balance(node);
    return answer;
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

void removeNode(BSTNode *&node, int value)
{
    if (node == nullptr)
        return;

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
            removeNode(node->left, maxValue);
            node->value = maxValue;
            balance(node);
        }
        return;
    }

    if (value < node->value)
        removeNode(node->left, value);
    else
        removeNode(node->right, value);
    balance(node);
}

void remove(BinarySearchTree *tree, int value)
{
    if (contains(tree, value))
        removeNode(tree->root, value);
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
    std::cout << '\n';
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
    std::cout << '\n';
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
    std::cout << '\n';
}
