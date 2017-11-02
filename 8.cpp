#include <stdio.h>
#include <iostream>
using namespace std;

int iterativeFactorial(int n)
{
    if (n == 0)
    {
        return 1;
    }
    if (n > 0)
    {
        int answer = 1;
        for (int i = 1; i <= n; i++)
        {
            answer *= i;
        }
        return answer;
    }
}

int recursiveFactorial(int n)
{
    if (n == 0)
    {
        return 1;
    }
    if (n > 0)
    {
        return n * recursiveFactorial(n - 1);
    }
}

int main()
{
        setlocale(0, "");

        int n = 0;
        printf("Введите n: ");
        cin >> n;

        if (n < 0)
        {
            printf("Неверные данные!");
        }
        else
        {
            cout << "Итеративно: " << iterativeFactorial(n) << "\n";
            cout << "Рекурсивно: " << recursiveFactorial(n);
        }
}
