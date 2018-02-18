#include <iostream>
#include "list.h"

using namespace std;

int main()
{
    List *list = create();
    int length = 0;
    cout << "Enter length: ";
    cin >> length;

    if (length <= 0)
    {
        cout << "Wrong number of elements. ";
        return 0;
    }

    cout << "Enter values: ";
    for (int i = 0; i < length; i++)
    {
        int value = 0;
        cin >> value;
        add(list, value);
    }

    sort(list);

    cout << "Sorted list: ";
    print(list);
    emptyMemory(list);
    delete list;
}
