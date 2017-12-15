#include <iostream>
#include "list.h"
#include <fstream>

using namespace std;

const int maxSize = 1000;

int main()
{
    char fileName[maxSize];
    cout << "Enter file name: ";
    cin >> fileName;

    ifstream file(fileName);

    if (!file.is_open())
    {
        cout << "File not found.";
        int c = 0;
        cin >> c;
        return 0;
    }

    List *list = create();
    int value = 0;
    int length = 0;
    for (file >> value; !file.eof(); file >> value)
    {
        length++;
        add(list, value);
    }

    bool answer = true;
    int counter = 1;
    while (counter <= length / 2)
    {
        if (getValue(list, counter) == getValue(list, length - counter + 1))
                counter++;
        else
        {
            answer = false;
            break;
        }
    }

    emptyMemory(list);

    if (answer)
        cout << "Symmetrical.";
    else
        cout << "Is not symmetrical.";
    return 0;
}
