#include "phoneBase.h"
#include <iostream>
#include <fstream>

using namespace std;

const int maxSize = 100000;

struct ListElement
{
    char *name;
    char *telephone;
    ListElement *next;
};

struct List
{
    int numbeOfElements;
    ListElement *head;
};

List *create()
{
    List *newList = new List;
    newList->head = nullptr;
    newList->numbeOfElements = 0;
    return newList;
}

bool isEmpty(List *list)
{
    return list->head == nullptr;
}

bool isEqual(char *firstLine, char *secondLine)
{
    int counter = 0;
    while (firstLine[counter] != '\0')
    {
        if (firstLine[counter] != secondLine[counter])
            return false;
        counter++;
    }

    return firstLine[counter] == secondLine[counter];
}

void add(List *list, char *name, char *telephone)
{
    list->numbeOfElements++;
    ListElement *newElement = new ListElement;
    newElement->name = name;
    newElement->telephone = telephone;
    newElement->next = nullptr;

    if (list->head == nullptr)
    {
        list->head = newElement;
        return;
    }

    ListElement *temp = list->head;
    while (temp->next != nullptr)
        temp = temp->next;

    temp->next = newElement;
}

void findTelephone(List *list, char *name)
{
    ListElement *temp = list->head;
    while (!isEmpty(list))
    {
        if (isEqual(temp->name, name))
        {
                cout << temp->telephone << '\n';
                return;
        }
        temp = temp->next;
    }
    cout << "Not found\n";
}

void findName(List *list, char *telephone)
{
    ListElement *temp = list->head;
    while (!isEmpty(list))
    {
        if (isEqual(temp->telephone, telephone))
        {
                cout << temp->name << '\n';
                return;
        }
        temp = temp->next;
    }
    cout << "Not found\n";
}

void addFromFile(List *list)
{
    cout << "Enter file name: ";
    char *fileName = new char[maxSize];
    cin >> fileName;
    ifstream file(fileName);
    delete[] fileName;

    if (file.is_open())
    {
        int numberOfElements = 0;
        file >> numberOfElements;
        file.ignore();
        for (int i = 0; i < numberOfElements; i++)
        {
            char *name = new char[maxSize];
            char *telephone = new char[maxSize];
            file >> name;
            file >> telephone;
            add(list, name, telephone);
        }
        file.close();
    }
}

void save(List *list)
{
    cout << "Enter file name(place to save phone base): ";
    char *fileName = new char[maxSize];
    cin >> fileName;
    remove(fileName);
    ofstream file(fileName);
    if (!file.is_open())
    {
        cout << "Enter a valid file name: ";
        cin >> fileName;
        remove(fileName);
        ofstream file(fileName);
    }

    delete[] fileName;

    file << list->numbeOfElements << endl;

    ListElement *temp = list->head;
    while (temp != nullptr)
    {
        file << temp->name << endl << temp->telephone << endl;
        temp = temp->next;
    }

    file.close();
}

void emptyMemory(List *list)
{
    while (!isEmpty(list))
    {
        ListElement *temp = list->head;
        list->head = list->head->next;
        delete[] temp->name;
        delete[] temp->telephone;
        delete temp;
    }
}
