#include <iostream>
#include "bst.h"

struct BSTNode
{
    int value;
    unsigned char height;
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

unsigned char height(BSTNode* node)
{
    return node ? node->height : 0;
}

int bfactor(BSTNode* node)
{
    return height(node->right) - height(node->left);
}

void fixheight(BSTNode* node)
{
    unsigned char left = height(node->left);
    unsigned char right = height(node->right);
    node->height = (left > right ? left : right) + 1;
}

BSTNode* rotateright(BSTNode* node)
{
    BSTNode* temp = node->left;
    node->left = temp->right;
    temp->right = node;
    fixheight(node);
    fixheight(temp);
    return temp;
}

BSTNode* rotateleft(BSTNode* node)
{
    BSTNode* temp = node->right;
    node->right = temp->left;
    temp->left = node;
    fixheight(node);
    fixheight(temp);
    return temp;
}

BSTNode* balance(BSTNode* node)
{
    fixheight(node);
    if( bfactor(node)==2 )
    {
        if( bfactor(node->right) < 0 )
            node->right = rotateright(node->right);
        return rotateleft(node);
    }
    if( bfactor(node)==-2 )
    {
        if( bfactor(node->left) > 0  )
            node->left = rotateleft(node->left);
        return rotateright(node);
    }
    return node;
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
        node = balance(node);
        return true;
    }

    if (node->value == value)
        return false;

    if (value < node->value)
    {
        bool answer = addNode(node->left, value);
        node = balance(node);
        return answer;
    }
    else
    {
        bool answer = addNode(node->right, value);
        node = balance(node);
        return answer;
    }
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
            node = balance(node);
        }
        else if (node->right == nullptr)
        {
            BSTNode *temp = node->left;
            delete node;
            node = temp;
            node = balance(node);
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
    {
        bool answer = removeNode(node->left, value);
        node = balance(node);
        return answer;
    }
    else
    {
        bool answer = removeNode(node->right, value);
        node = balance(node);
        return answer;
    }
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
