#include <iostream>
#include <fstream>
#include "parseTree.h"

using namespace std;

const int maxSize = 1000;

int main()
{
    cout << "Enter file name: ";
    char *fileName = new char[maxSize];
    cin >> fileName;
    ifstream file(fileName);
    delete[] fileName;
    if (!file.is_open())
    {
        cout << "File not found.";
        return 0;
    }

    ParseTree *expression = createTree();
    takeTree(expression, file);
    file.close();

    printExpression(expression);
    cout << " = " << calculate(expression);

    deleteTree(expression);
}
