#pragma once

struct Stack;

bool isEmpty(Stack *stack);
Stack *createStack();
void push(Stack *stack, int number);
int pop(Stack *stack);
void emptyMemory(Stack *stack);
