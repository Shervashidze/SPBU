#include <stdio.h>
#include <iostream>
using namespace std;

int iterative(int number)
{
    if (number == 0)
    {
        return 1;
    }

    if (number == 1)
    {
        return 1;
    }

    int prevNumber = 1;
    int answer = 1;
    for (int i = 1; i < number; i++)
    {
        answer += prevNumber;
        prevNumber = answer - prevNumber;
    }

    return answer;
}

int recursive(int number)
{
    if (number == 0)
    {
        return 1;
    }

    if (number == 1)
    {
        return 1;
    }

    return (recursive(number - 1) + recursive(number - 2));
}

int main()
{
        setlocale(0, "");

        int number = 0;
        printf("Введите номер числа Фибоначчи: ");
        cin >> number;

        printf("Итеративный подсчет: ");
        cout << iterative(number) << "\n";

        printf("Рекурсивный подсчет: ");
        cout << recursive(number);

        return 0;
}
