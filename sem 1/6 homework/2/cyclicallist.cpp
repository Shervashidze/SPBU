#include "cyclicallist.h"

struct ListElement
{
    int value;
    ListElement *next;
};

struct List
{
    ListElement *head;
};

List *create()
{
    List *list = new List;
    list->head = nullptr;
    return list;
}

bool isEmpty(List *list)
{
    return list->head == nullptr;
}

void addElement(List *list, int value)
{
    ListElement *newList = new ListElement;
    newList->value = value;

    if (isEmpty(list))
    {
        list->head = newList;
        newList->next = newList;
        return;
    }

    ListElement *temp = list->head;
    while (temp->next != list->head)
        temp = temp->next;

    newList->next = list->head;
    temp->next = newList;
    temp = nullptr;
}

int deleteNextElement(List *list)
{
    if (list->head == nullptr)
        return 42;

    int value = list->head->next->value;

    if (isAlone(list))
    {
        delete list->head;
        list->head = nullptr;
        return value;
    }

    ListElement *temp = list->head->next;
    list->head->next = temp->next;

    delete temp;
    temp = nullptr;
    return value;
}

bool isAlone(List *list)
{
    return (list->head == list->head->next);
}

void move(List *list, int value)
{
    for (int i = 0; i < value; i++)
        list->head = list->head->next;
}

void emptyMemory(List *list)
{
    if (list->head == nullptr)
        return;
    else
        while (!isEmpty(list))
            deleteNextElement(list);
}
