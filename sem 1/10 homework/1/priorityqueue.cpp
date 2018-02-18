#include <iostream>
#include "priorityqueue.h"

struct QueueElement
{
    Tile *vertex;
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

void append(Queue *queue, Tile *tile)
{
    QueueElement *newElement = new QueueElement;
    newElement->vertex = tile;
    newElement->next = nullptr;
    if (!isEmpty(queue))
    {
        QueueElement *temp = queue->head;
        if (priority(tile) < priority(temp->vertex))
        {
            newElement->next = temp;
            queue->head = newElement;
            return;
        }
        while (temp->next != nullptr && (priority(tile) > priority(temp->vertex)) && (priority(tile) > priority(temp->vertex)))
            temp = temp->next;
        newElement->next = temp->next;
        temp->next = newElement; 
    }
    if (isEmpty(queue))
        queue->head = newElement;
}

Tile *pop(Queue *queue)
{
    Tile *answer = queue->head->vertex;
    QueueElement *temp = queue->head->next;
    delete queue->head;
    queue->head = temp;

    return answer;
}

bool isEmpty(Queue *queue)
{
    return queue->head == nullptr;
}

bool contains(Queue *queue, Tile *value)
{
    QueueElement *temp = queue->head;
    bool answer = false;
    while (temp != nullptr)
    {
        if (temp->vertex == value)
        {
            answer = true;
            break;
        }
        temp = temp->next;
    }
    return answer;
}
