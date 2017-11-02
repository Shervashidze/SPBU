#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
        setlocale(0, "");

        int length = 0;
        printf("Введите длину строки скобок: ");
        cin >> length;

        printf("Введите строку скобок: ");
        char bracket[length + 1] = "";
        cin >> bracket;

        int i = 0;
        int counter = 0;
        while (i < length)
        {
            if (bracket[i] == '(')
            {
                counter++;
            }
            if (bracket[i] == ')')
            {
                counter--;
            }
            if (counter < 0)
            {
                break;
            }
            i++;
        }

        if (counter == 0)
        {
            printf("Баланс скобок выполнен");
        }
        if (counter != 0)
        {
            printf("Баланс скобок не выполнен");
        }
}
