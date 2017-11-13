#include <iostream>

using namespace std;

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

int intToArray(int arrayOfDigits[], int number)
{
    int length = 0;
    while (number > 0)
    {
        arrayOfDigits[length] = (number % 10);
        number /= 10;
        length++;
    }

    return length;
}

void printArray(int numbers[], int length)
{
    for (int i = 0; i < length; i++)
    {
        cout << numbers[i];
    }
}

int main()
{
    int number = 0;
    cout << "Enter number: ";
    cin >> number;

    int arrayOfDigits[1000];
    for (int i = 0; i < 1000; i++)
    {
        arrayOfDigits[i] = 0;
    }

    int length = intToArray(arrayOfDigits, number);


    quickSort(arrayOfDigits, 0, length - 1);

    int counter = 0;
    while (arrayOfDigits[counter] == 0)
    {
        counter++;
    }

    swap(arrayOfDigits[0], arrayOfDigits[counter]);

    cout << "Least number: ";

    printArray(arrayOfDigits, length);

    return 0;
}
