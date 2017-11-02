#include <stdio.h>
#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

void buildRandom(int number[])
{
    srand(time(NULL));
    int length = 10;
    int digits[length] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int digitNumber = 0;

    number[0] = digits[digitNumber];
    for (int i = 0; i < 4; i++)
    {
        digitNumber = (rand() % length) + 0;
        number[i] = digits[digitNumber];
        swap(digits[digitNumber], digits[length - 1]);
        length--;
    }

    if (number[0] == 0)
    {
        swap(number[0], number[1]);
    }
}

int main()
{
        int number[4];
        buildRandom(number);
        for (int i = 0; i < 4; i++)
        {
            cout << number[i] << " ";
        }
}
