#include <iostream>
#include "list.h"

using namespace std;

int main()
{
    int length = 0;
    cout << "Enter length: ";
    cin >> length;

    List *list = create();
    cout << "Enter elements: ";
    for (int i = 0; i < length; i++)
    {
        int value = 0;
        cin >> value;
        add(list, value);
    }

    int *sequenceByIndex = new int[length];
    int maxIndex = 0;
    int prevValue = 0;
    for (int i = 0; i < length; i++)
    {
        int value = getValue(list, i + 1);
        sequenceByIndex[i] = 1;

        if (i >= 1 && value > prevValue)
        {
            sequenceByIndex[i] = sequenceByIndex[i - 1] + 1;
            if (sequenceByIndex[i] > sequenceByIndex[maxIndex])
                maxIndex = i;
        }

        prevValue = value;
    }

    List *subsequence = create();
    for (int i = sequenceByIndex[maxIndex] - 1; i >= 0; i--)
        add(subsequence, getValue(list, maxIndex + 1 - i));

    print(subsequence);

    emptyMemory(list);
    emptyMemory(subsequence);
    delete[] sequenceByIndex;

    return 0;
}
