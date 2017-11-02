#include <stdio.h>
#include <iostream>
using namespace std;

void deleteWrong(int numbers[10000], int p, int n)
{
    int counter = p + p;
    while (counter <= n)
    {
        numbers[counter] = 0;
        counter += p;
    }
}

int main()
{
        setlocale(0, "");

        printf("Введите n: ");
        int n = 0;
        cin >> n;

        int numbers[10000] = {};
        for (int k = 2; k <= n; k++)
        {
            numbers[k] = 1;
        }

        printf("Простые числа: ");
        for (int i = 2; i <= n; i++)
        {
            if (numbers[i] == 1)
            {
                deleteWrong(numbers, i, n);
                cout << i << " ";
            }
        }
}
