#include <iostream>
using namespace std;

void getArray(int numbers[], int length)
{
    cout << "Enter array: ";
    for (int i = 0; i < length; i++)
    {
        cin >> numbers[i];
    }
}

int centralize(int numbers[], int left, int right)
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

void quickSort(int numbers[], int left, int right)
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
    cout << "Enter length: ";
    int length = 0;
    cin >> length;

    int *numbers = new int[length];

    getArray(numbers, length);
    quickSort(numbers, 0, length - 1);

    bool exists = false;
    int maximum = -100000;

    for (int i = 0; i < length - 1; i++)
    {
        if (numbers[i] == numbers[i + 1])
        {
            maximum = numbers[i];
            exists = true;
        }
    }

    if (exists)
    {
       cout << "The largest repetitive element: " << maximum;
    }
    else
    {
        cout << "Doesn't exist.";
    }

    delete[] numbers;
    numbers = nullptr;

    return 0;
}
