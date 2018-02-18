#include <iostream>
#include "priorityqueue.h"

struct QueueElement
{
    HuffmanTree *value;
    QueueElement *next;
};

struct Queue
{
    QueueElement *head;
};

Queue *createQueue()
{
    Queue *newQueue = new Queue;
    newQueue->head = nullptr;
    return newQueue;
}

void deleteQueue(Queue *queue)
{
    while (!isEmpty(queue))
        pop(queue);
    delete queue;
}

void append(Queue *queue, HuffmanTree *value)
{
    QueueElement *newElement = new QueueElement;
    newElement->value = value;
    newElement->next = nullptr;
    if (!isEmpty(queue))
    {
        QueueElement *temp = queue->head;
        if (frequency(value) < frequency(temp->value))
        {
            newElement->next = temp;
            queue->head = newElement;
            return;
        }
        while (temp->next != nullptr && frequency(value) > frequency(temp->value) && frequency(value) > frequency(temp->next->value))
            temp = temp->next;
        newElement->next = temp->next;
        temp->next = newElement;
    }
    if (isEmpty(queue))
    {
        queue->head = newElement;
    }
}

bool isAlone(Queue *queue)
{
    return queue->head->next == nullptr;
}

HuffmanTree *pop(Queue *queue)
{
    HuffmanTree *value = queue->head->value;

    QueueElement *temp = queue->head->next;
    delete queue->head;
    queue->head = temp;

    return value;
}

bool isEmpty(Queue *queue)
{
    return queue->head == nullptr;
}
