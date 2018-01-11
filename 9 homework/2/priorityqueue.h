#pragma once
#include "huffman.h"

struct Queue;


Queue *createQueue();
void deleteQueue(Queue *queue);
void append(Queue *queue, HuffmanTree *value);
HuffmanTree *pop(Queue *queue);
bool isEmpty(Queue *queue);
bool isAlone(Queue *queue);
