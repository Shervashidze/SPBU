#include <stdio.h>
#include <iostream>
using namespace std;

float power(int number, int degree)
{
    bool negative = false;
    if (degree < 0)
    {
        negative = true;
        degree = -degree;
    }

    float answer = 1;

    while (degree > 0)
    {
        if ((degree % 2) == 1)
        {
            answer *= number;
            number *= number;
        }
        if ((degree % 2)  == 0)
        {
            number *= number;
        }

        degree /= 2;
    }

    if (negative)
    {
        answer = 1 / answer;
    }

    return answer;
}

int main()
{
        setlocale(LC_ALL, "Russian");

        int number = 0;
        printf("¬ведите число a: ");
        cin >> number;

        int degree = 0;
        printf("¬ведите степень n: ");
        cin >> degree;

        cout << "a^n = " << power(number, degree);

        return 0;
}
