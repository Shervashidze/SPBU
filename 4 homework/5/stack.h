#pragma once

struct Stack;

bool isEmpty(Stack *stack);
Stack *createStack();
void push(Stack *stack, char number);
char pop(Stack *stack);
void emptyMemory(Stack *stack);
char peek(Stack *stack);
