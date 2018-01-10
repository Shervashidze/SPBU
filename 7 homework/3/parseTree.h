#pragma once
#include <fstream>

using namespace std;

struct ParseTree;

ParseTree *createTree();
void takeTree(ParseTree *tree, ifstream &file);

int calculate(ParseTree *tree);
void printExpression(ParseTree *tree);

void deleteTree(ParseTree *tree);
