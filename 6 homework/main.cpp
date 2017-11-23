#include <iostream>
#include "cyclicallist.h"

using namespace std;

int main()
{
    int n = 0;
    cout << "Enter n: ";
    cin >> n;
    int m = 0;
    cout << "Eneter m: ";
    cin >> m;

    List *list = init(1);
    for (int i = 2; i <= n; i++)
        addElement(list, i);

    move(list, n - 1);
    while (!isAlone(list))
    {
        move(list, m - 1);
        deleteNextElement(list);
    }

    cout << "Answer: " << deleteNextElement(list);
    emptyMemory(list);
}
