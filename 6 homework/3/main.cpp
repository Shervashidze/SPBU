#include <iostream>
#include "phoneBase.h"

using namespace std;

const int maxSize = 10000;

void menu(List *list)
{
    int choose = 0;
    cout << "Enter 0 to exit.\n";
    cout << "Enter 1 to add.\n";
    cout << "Enter 2 to find telephone by name.\n";
    cout << "Enter 3 to find name by telephone.\n";
    cout << "Enter 4 to save.\n";
    cin >> choose;

    if (choose == 0)
        return;
    if (choose == 1)
    {
        char *name = new char[maxSize];
        cout << "Enter name: ";
        cin >> name;
        char *telephone = new char[maxSize];
        cout << "Enter telephone: ";
        cin >> telephone;
        add(list, name, telephone);
    }
    if (choose == 2)
    {
        char *name = new char[maxSize];
        cout << "Enter name: ";
        cin >> name;
        findTelephone(list, name);
        delete[] name;
    }
    if (choose == 3)
    {
        char *telephone = new char[maxSize];
        cout << "Enter telephone: ";
        cin >> telephone;
        findName(list, telephone);
        delete[] telephone;
    }
    if (choose == 4)
        save(list);

    menu(list);
}

int main()
{
    List *list = create();
    cout << "Enter 0 to start without file.\n";
    cout << "Enter 1 to read from file.\n";
    int choose = 0;
    cin >> choose;
    if (choose == 1)
        addFromFile(list);
    menu(list);
    emptyMemory(list);
    delete list;
}
