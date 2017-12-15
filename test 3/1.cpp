#include <iostream>

using namespace std;

const int maxSize = 1000;

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
    int array[maxSize];
    for (int i = 0; i < maxSize; i++)
        array[i] = 0;

    cout << "Enter array: ";
    int length = 0;
    int element = 0;
    cin >> element;

    if (element == 0)
    {
        cout << "There is no elements.";
        return 0;
    }

    while (element != 0)
    {
        array[length] = element;
        length++;
        cin >> element;
    }

    quickSort(array, 0, length - 1);

    cout << "Elements: ";
    int counter = 0;
    while (counter < length)
    {
        int amount = 1;
        while (array[counter] == array[counter + 1])
        {
            amount++;
            counter++;
        }
        cout << array[counter] << " (" << amount << ") ";
        counter++;
    }
}
