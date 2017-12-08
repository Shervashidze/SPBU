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

bool isEmpty(List *list)
{
    return list->head == nullptr;
}

void add(List *list, int value)
{
    list->length++;

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

ListElement *getElement(List *list, int position)
{
    ListElement *temp = list->head;
    for (int i = 1; i < position; i++)
        temp = temp->next;
    return temp;
}

void deleteElem(List *list, int position)
{
    ListElement *temp = list->head;
    for (int i = 1; i < position - 1; i++)
        temp = temp->next;
    temp->next = temp->next->next;
}

void sort(List *list)
{
    for (int i = 2; i <= list->length; i++)
    {
        ListElement *temp = getElement(list, i);
        ListElement *posElem = list->head;
        if (temp->value < list->head->value)
        {
            deleteElem(list, i);
            temp->next = list->head;
            list->head = temp;
            continue;
        }

        for (int j = 2; j < i; j++)
        {
            if (posElem->value <= temp->value && posElem->next->value <= temp->value)
                posElem = posElem->next;
            else
                break;
        }

        if (posElem->next != temp)
        {
            deleteElem(list, i);
            ListElement *switcher = posElem->next;
            posElem->next = temp;
            temp->next = switcher;
        }
    }
}
