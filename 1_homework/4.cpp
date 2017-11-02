#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
        setlocale(0, "");

        int length = 28;
        int numbers[length] = {};
        for (int m = 0; m < length; m++)
        {
            numbers[m] = 0;
        }

        int demension = 10;
        for (int i = 0; i < demension; i++)
        {
            for (int j = 0; j < demension; j++)
            {
                for (int k = 0; k < demension; k++)
                {
                    numbers[i + j + k]++;
                }
            }
        }

        int answer = 0;
        for (int f = 0; f < length; f++)
        {
            answer += numbers[f] * numbers[f];
        }

        cout << "Количество счастливых билетов: " << answer;
}
