#include <iostream>
#include "phoneBase.h"

using namespace std;

const int maxSize = 10000;

void printMenu()
{
    cout << "Enter 0 to exit.\n";
    cout << "Enter 1 to add.\n";
    cout << "Enter 2 to find telephone by name.\n";
    cout << "Enter 3 to find name by telephone.\n";
    cout << "Enter 4 to save.\n";
}

void processUserInput(List *list)
{
    printMenu();

    enum choices {end, addition, findByTelephone, findByName, saveInFile};
    int choose = 0;
    cin >> choose;
    while (choose != end)
    {
        char *name = new char[maxSize];
        char *telephone = new char[maxSize];
        switch (choose)
        {
            case end:
                return;
                break;

            case addition:
                cout << "Enter name: ";
                cin >> name;
                cout << "Enter telephone: ";
                cin >> telephone;
                add(list, name, telephone);
                break;

            case findByTelephone:
                cout << "Enter name: ";
                cin >> name;
                cout << "Name: ";
                findTelephone(list, name);
                break;

            case findByName:
                cout << "Enter telephone: ";
                cin >> telephone;
                cout << "Telephone: ";
                findName(list, telephone);
                break;

            case saveInFile:
                save(list);
                break;

            default:
                cout << "Unknown comand.";
                break;
        }

        delete[] name;
        delete[] telephone;
        cin >> choose;
    }

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

    processUserInput(list);
    emptyMemory(list);
}
