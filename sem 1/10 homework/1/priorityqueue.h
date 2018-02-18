#pragma once
#include "map.h"
struct Queue;

Queue *createQueue();
void deleteQueue(Queue *queue);
void append(Queue *queue, Tile *vertex);
Tile *pop(Queue *queue);
bool isEmpty(Queue *queue);
bool isAlone(Queue *queue);
bool contains(Queue *queue, Tile *value);
