#include "cyclicallist.h"

struct List
{
    int value;
    List *next;
};

List *init(int number)
{
    List *list = new List;
    list->value = number;
    list->next = list;
    return list;
}

void addElement(List *list, int value)
{
    List *temp = new List;
    temp = list;
    while (temp->next != list)
        temp = temp->next;

    List *newList = new List;
    newList->value = value;
    newList->next = list;
    temp->next = newList;
    temp = nullptr;
}

int deleteNextElement(List *&list)
{
    if (list == nullptr)
        return 42;

    int value = list->next->value;

    if (isAlone(list))
    {
        delete list;
        list = nullptr;
        return value;
    }

    List *temp = new List;
    temp = list->next;
    list->next = temp->next;

    delete temp;
    temp = nullptr;
    return value;
}

bool isAlone(List *list)
{
    return (list == list->next);
}

bool isEmpty(List *list)
{
    return list == nullptr;
}

void move(List *&list, int value)
{
    for (int i = 0; i < value; i++)
        list = list->next;
}

void emptyMemory(List *&list)
{
    if (list == nullptr)
        return;
    else
        while (!isEmpty(list))
            deleteNextElement(list);
}
