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

void printArray(int numbers[], int length)
{
    cout << "Sorted array: ";
    for (int i = 0; i < length; i++)
    {
        cout << numbers[i] << " ";
    }
}

void buildHeap(int numbers[], int root, int bottom)
{
    int maxChild = -1;
    bool exitCondition = true;
    int leftChild = (root * 2) + 1;
    int rightChild = (root * 2) + 2;

    while ((leftChild <= bottom) and (exitCondition))
    {
        if ((leftChild == bottom) or (numbers[leftChild] > numbers[rightChild]))
        {
            maxChild = leftChild;
        }
        else
        {
            maxChild = rightChild;
        }

        if (numbers[root] < numbers[maxChild])
        {
            swap(numbers[root], numbers[maxChild]);
            root = maxChild;
        }
        else
        {
            exitCondition = false;
        }
        leftChild = (root * 2);
        rightChild = (root * 2) + 1;
    }
}

void heapSort(int numbers[], int length)
{
    for (int i = (length / 2) - 1; i >= 0; i--)
    {
        buildHeap(numbers, i, length);
    }

    for (int i = length - 1; i >= 1; i--)
    {
        swap(numbers[0], numbers[i]);
        buildHeap(numbers, 0, i - 1);
    }
}

int main()
{
        int length = 0;
        cout << "Enter length: ";
        cin >> length;

        int *numbers = new int[length];
        for (int i = 0; i < length; i++)
        {
            numbers[i] = -1;
        }

        getArray(numbers, length);

        heapSort(numbers, length);

        printArray(numbers, length);

        delete[] numbers;
        numbers = nullptr;

        return 0;
}
