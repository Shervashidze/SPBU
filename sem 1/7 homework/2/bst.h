#pragma once

struct BSTNode;

struct BinarySearchTree
{
    BSTNode *root;
};

BinarySearchTree *createTree();
void clearTree(BinarySearchTree *tree);

bool contains(BinarySearchTree *tree, int value);
bool add(BinarySearchTree *tree, int value);
void remove(BinarySearchTree *tree, int value);

void print(BinarySearchTree *tree);
void descendingPrint(BinarySearchTree *tree);
void errorSearchPrint(BinarySearchTree *tree);
