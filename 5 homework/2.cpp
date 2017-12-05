#include <iostream>

using namespace std;

const int numberOfBits = 32;

int *bits(int number)
{
    int mask = 1;
    int *bits = new int[numberOfBits];
    for (int i = 0; i < numberOfBits; i++)
    {
        if (mask & number)
            bits[i] = 1;
        else
            bits[i] = 0;
        mask = mask << 1;
    }

    return bits;
}

int *sum(int *firstBits, int *secondBits)
{
    int *sum = new int[32];
    int addition = 0;
    for (int i = 0; i < numberOfBits; i++)
    {
        int amount = firstBits[i] + secondBits[i] + addition;
        sum[i] = amount % 2;
        addition = amount / 2;
    }

    return sum;
}

void printAsNumber(int *bits)
{
    int answer = 0;
    int mask = 1;
    for (int i = 0; i < numberOfBits; i++)
    {
        answer = answer + bits[i] * mask;
        mask = mask << 1;
    }

    cout << answer << '\n';
}

void printBits(int *bits)
{
    for (int i = numberOfBits - 1; i >= 0; i--)
    {
        cout << bits[i] << ' ';
    }
    cout << '\n';
}

int main()
{
    int first = 0;
    cout << "Enter first number: ";
    cin >> first;
    int second = 0;
    cout << "Enter second number: ";
    cin >> second;

    int *firstBits = bits(first);
    printBits(firstBits);
    int *secondBits = bits(second);
    printBits(secondBits);

    int *amount = sum(firstBits, secondBits);
    delete[] firstBits;
    delete[] secondBits;

    printBits(amount);
    printAsNumber(amount);
    delete[] amount;
}
