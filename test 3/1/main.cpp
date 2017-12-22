#include <iostream>
#include "bst.h"

using namespace std;

int findAmount(int number)
{
    if (number < 0)
        number = -number;
    int answer = 0;
    while (number > 0)
    {
       answer += number % 10;
       number = number / 10;
    }

    return answer;
}

BinarySearchTree *findElements(int array[], int length)
{
    BinarySearchTree *elements = createTree();
    int max = 0;
    for (int i = 0; i < length; i++)
    {
        int amount = findAmount(array[i]);
        if (max < amount)
        {
            clearTree(elements);
            elements = createTree();
            add(elements, array[i]);
            max = amount;
        }
        if (max == amount)
            add(elements, array[i]);
    }

    return elements;
}

int main()
{
   int length = 0;
   cout << "Enter length: ";
   cin >> length;

   int *array = new int[length];
   cout << "Enter elements: ";
   for (int i = 0; i < length; i ++)
       cin >> array[i];

   BinarySearchTree *elements = findElements(array, length);

   print(elements);
   clearTree(elements);
   delete[] array;
}
