#include <iostream>
#include <fstream>
#include "stack.h"

using namespace std;
const int maxWordSize = 10000;

bool *initUsed(int n)
{
    bool *used = new bool[n];
    for (int i = 0; i < n; i++)
        used[i] = false;
    used[0] = true;

    return used;
}

void printBFS(int **matrix, int length)
{
    bool *used = initUsed(length);

    Stack *stack = createStack();
    push(stack, 0);
    cout << "1 ";

    while (!isEmpty(stack))
    {
        int vertex = pop(stack);
        for (int i = 0; i < length; i++)
        {
            if (!used[i] && matrix[vertex][i] > 0)
            {
                used[i] = true;
                push(stack, i);
                cout << i + 1 << ' ';
            }
        }
    }

    delete[] used;
    emptyMemory(stack);
}

int main()
{
    char fileName[maxWordSize];
    cout << "Enter file name: ";
    cin >> fileName;

    ifstream file(fileName);

    if (!file.is_open())
    {
        cout << "File not found.";
        int c = 0;
        cin >> c;
        return 0;
    }

    int length = 0;
    file >> length;

    int **matrix = new int*[length];
    for (int i = 0; i < length; i++)
        matrix[i] = new int[length];

    for (int i = 0; i < length; i++)
        for (int j = 0; j < length; j++)
           file >> matrix[i][j];

    printBFS(matrix, length);

    for (int i = 0; i < length; i++)
        delete[] matrix[i];
    delete[] matrix;
}
