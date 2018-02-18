#include "list.h"
#include <iostream>

using namespace std;

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
    List *newList = new List;
    newList->head = nullptr;
    return newList;
}

bool isEmpty(List *list)
{
    return list->head == nullptr;
}

void add(List *list, int value)
{

    ListElement *newElement = new ListElement;
    newElement->value = value;
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

void emptyMemory(List *list)
{
    while (!isEmpty(list))
    {
        ListElement *temp = list->head;
        list->head = list->head->next;
        delete temp;
    }
    delete list;
    list = nullptr;
}

int getValue(List *list, int index)
{
    ListElement *temp = list->head;
    for (int i = 2; i <= index; i++)
        temp = temp->next;
    return temp->value;
}

void print(List *list)
{
    ListElement *temp = list->head;
    while (temp != nullptr)
    {
        cout << temp->value << ' ';
        temp = temp->next;
    }
}
