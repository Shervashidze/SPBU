#include <stdio.h>
#include <iostream>
using namespace std;

void getMass(int numbers[1000], int length)
{
    printf("¬ведите элементы массива: ");
    for (int i = 0; i < length; i++)
    {
        cin >> numbers[i];
    }
}

void printMass(int numbers[1000], int length)
{
    printf("ќтсортированный массив: ");
    for (int i = 0; i < length; i++)
    {
        cout << numbers[i] << " ";
    }
}

int centralize(int numbers[1000], int left, int right)
{
    int middle = numbers[(left + right) / 2];

    int l = left;
    int r = right;
    while (l <= r)
    {
        while (numbers[l] < middle)
        {
            l++;
        }
        while (numbers[r] > middle)
        {
            r--;
        }

        if (l <= r)
        {
            swap(numbers[l], numbers[r]);
            l++;
            r--;
        }
    }

    return l;
}

void quickSort(int numbers[1000], int left, int right)
{
    int middle = centralize(numbers, left, right);

    if (left < middle - 1)
    {
        quickSort(numbers, left, middle - 1);
    }
    if (middle < right)
    {
        quickSort(numbers, middle, right);
    }
}

int main()
{
        setlocale(0, "");

        int numbers[1000] = {};

        int length = 0;
        cout << "¬ведите длину массива: ";
        cin >> length;

        getMass(numbers, length);
        quickSort(numbers, 0, length - 1);
        printMass(numbers, length);
}
