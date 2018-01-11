#include "list.h"
#include <iostream>

using namespace std;
const int maxSize = 10000;

struct ListElement
{
    String *value;
    int amount;
    ListElement *next;
};

struct List
{
    int length;
    ListElement *head;
};

List *create()
{
    List *newList = new List;
    newList->length = 0;
    newList->head = nullptr;
    return newList;
}

bool isListEmpty(List *list)
{
    return list->head == nullptr;
}

void addInList(List *list, String *value)
{
    list->length++;

    ListElement *temp = list->head;
    while (temp != nullptr)
    {
        if (isEqual(temp->value, value))
        {
            temp->amount++;
            return;
        }
        temp = temp->next;
    }

    ListElement *newElement = new ListElement;
    newElement->value = value;
    newElement->amount = 1;
    newElement->next = nullptr;

    if (isListEmpty(list))
    {
        list->head = newElement;
        return;
    }

    temp = list->head;
    while (temp->next != nullptr)
        temp = temp->next;

    temp->next = newElement;
}

void emptyMemory(List *list)
{
    while (!isListEmpty(list))
    {
        deleteString(list->head->value);
        ListElement *temp = list->head;
        list->head = list->head->next;
        delete temp;
    }
    delete list;
}

void print(List *list)
{
    ListElement *temp = list->head;
    char *buff = nullptr;
    while (temp != nullptr)
    {
        buff = toChar(temp->value);
        cout << buff << " - " << temp->amount << ' ';
        temp = temp->next;
        delete[] buff;
    }
    cout << '\n';
}

int getListSize(List *list)
{
    return list->length;
}
