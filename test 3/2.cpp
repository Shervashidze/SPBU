#include <iostream>

using namespace std;

bool isSaddlePoint(int n, int m, int **array, int line, int column)
{
    int min = 0;
    min = array[line][0];
    for (int j = 0; j < m; j++)
        if (array[line][j] < min)
            min = array[line][j];

    int max = 0;
    max = array[0][column];
    for (int i = 0; i < n; i++)
        if (array[i][column] > max)
            max = array[i][column];

    return (array[line][column] == min && array[line][column] == max);
}

void printSaddlePoints(int n, int m, int **array)
{
    bool exists = false;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (isSaddlePoint(n, m, array, i, j))
            {
                cout << array[i][j] << '[' << i + 1 << ", " << j + 1 << "] ";
                exists = true;
            }

    if (!exists)
        cout << "There is no saddle points.";
}

int main()
{
    int n = 0;
    int m = 0;
    cout << "Enter number of lines: ";
    cin >> n;
    cout << "Enter number of columns: ";
    cin >> m;

    int **array = new int*[n];
    for (int i = 0; i < n; i++)
        array[i] = new int[m];

    cout << "Enter array:\n";
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            cin >> array[i][j];

    printSaddlePoints(n, m, array);

    for (int i = 0; i < n; i++)
        delete[] array[i];
    delete[] array;
    array = nullptr;
}
