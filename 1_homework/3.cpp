#include <stdio.h>
#include <iostream>
using namespace std;

void reversed(int numbers[1000], int i, int j)
{
    int k = 0;
    int middle = (i + j - 1) / 2;
    while (i + k <= middle)
    {
        swap(numbers[i + k], numbers[j - k]);
        k++;
    }
}

int main()
{
        setlocale(0, "");

        int n = 0;
        printf("Введите целое число n: ");
        cin >> n;

        int m = 0;
        printf("Введите целое число m: ");
        cin >> m;

        int numbers[1000] = {};
        printf("Введите элементы массива: ");
        for (int i = 0; i < m + n; i++)
        {
            cin >> numbers[i];
        }

        reversed(numbers, 0, n - 1);
        reversed(numbers, n, m + n - 1);
        reversed(numbers, 0, m + n - 1);

        cout << "Развернутый массив: ";
        for (int j = 0; j < m + n; j++)
        {
            cout << numbers[j] << " ";
        }
}
