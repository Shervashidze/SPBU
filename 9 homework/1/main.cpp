#include <iostream>
#include <fstream>

using namespace std;

const int maxSize = 1000;
const int inf = 1000000;

void findParent(int town, int *parent)
{
    if (town == 0)
        return;
    else
    {
        findParent(parent[town], parent);
        cout << town + 1 << ' ';
    }
}

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

    int n, m = 0;
    file >> n >> m;
    int **matrix = new int*[n];
    for (int i = 0; i < n; i++)
        matrix[i] = new int[n];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
           matrix[i][j] = inf;

    for (int k = 0; k < m; k++)
    {
        int i, j, len = 0;
        file >> i >> j >> len;
        matrix[i - 1][j - 1] = len;
        matrix[j - 1][i - 1] = len;
    }

    int *parent = new int[n];
    int *dist = new int[n];
    bool *used = new bool[n];
    for (int i = 0; i < n; i++)
    {
        dist[i] = inf;
        used[i] = false;
    }

    int *additionOrder = new int[n];
    int counter = 0;

    dist[0] = 0;
    for (int i = 0; i < n; i++)
    {
        int v = -1;
        for (int j = 0; j < n; j++)
            if (!used[j] && (v == -1 || dist[j] < dist[v]))
                    v = j;
        if (dist[v] == inf)
            break;
        used[v] = true;
        additionOrder[counter] = v + 1;
        counter++;

        for (int e = 0; e < n; e++)
            if (e != inf)
                if (dist[v] + matrix[v][e] < dist[e])
                {
                    dist[e] = dist[v] + matrix[v][e];
                    parent[e] = v;
                }
    }

    cout << "Order of addition: ";
    for (int i = 0; i < n; i++)
        cout << additionOrder[i] << ' ';
    cout << '\n';

    for (int i = 1; i < n; i++)
    {
        cout << i + 1 << ": Distance = " << dist[i] << "; Way: 1 ";
        findParent(i, parent);
        cout << '\n';
    }

    file.close();
    for (int i = 0; i < n; i++)
        delete[] matrix[i];
    delete[] matrix;
    delete[] additionOrder;
    delete[] parent;
    delete[] used;
    delete[] dist;
}
