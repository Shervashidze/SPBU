#include <iostream>

using namespace std;

void printSpiral(int n, int** array)
{
    int shift = 1;
    int line = n / 2;
    int column = n / 2;
    int changeDirection = 0;
    bool moveLine = false;
    bool moveLeftAndDown = false;

    cout << "Elements: ";
    while (column < n)
    {
        int counter = 0;
        if (moveLeftAndDown)
            counter = -1;
        else
            counter = 1;

        if (moveLine)
        {
            for (int i = 0; i < shift; i++)
            {
                cout << array[line][column] << ' ';
                line -= counter;
            }
            changeDirection++;
            moveLine = false;
        }
        else
        {
            for (int i = 0; i < shift; i++)
            {
                cout << array[line][column] << ' ';
                column += counter;
            }
            changeDirection++;
            moveLine = true;
        }

        if (changeDirection == 2)
        {
            shift++;
            changeDirection = 0;
            moveLeftAndDown = !moveLeftAndDown;
        }
    }
}

int main()
{
    int n = 0;
    cout << "Enter n: ";
    cin >> n;
    while (n % 2 != 1)
    {
        cout << "Enter correct n: ";
        cin >> n;
    }

    int **array = new int*[n];
    for (int i = 0; i < n; i++)
        array[i] = new int[n];

    cout << "Enter array:\n";
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> array[i][j];

    printSpiral(n, array);

    for (int i = 0; i < n; i++)
        delete[] array[i];
    delete[] array;
    array = nullptr;
}
