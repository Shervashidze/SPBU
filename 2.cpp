#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
        setlocale(0, "");

        int a = 0;
        printf("Ââåäèòå öåëîå ÷èñëî a: ");
        scanf("%d", &a);

        int b = 0;
        printf("Ââåäèòå öåëîå ÷èñëî b: ");
        scanf("%d", &b);

        if (a < 0)
        {
            a = - a;
        }

        if (b < 0)
        {
            b = - b;
        }

        int answer = -1;
        while (a >= 0)
        {
            answer++;
            a -= b;
        }

        printf("Íåïîëíîå ÷àñòíîå: %d", answer);
}
