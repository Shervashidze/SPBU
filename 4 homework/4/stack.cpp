#include "stack.h"

struct StackElement
{
    int value;
    StackElement *next;
};

struct Stack
{
    StackElement *head;
};

bool isEmpty(Stack *stack)
{
    return (stack->head == nullptr);
}

Stack *createStack()
{
    Stack *newStack = new Stack;
    newStack->head = nullptr;
    return newStack;
}

void push(Stack *stack, int value)
{
    StackElement *newStack = new StackElement;
    newStack->value = value;
    newStack->next = stack->head;
    stack->head = newStack;
}

int pop(Stack *stack)
{
   if (isEmpty(stack))
   {
       return -17;
   }
   StackElement *temp = nullptr;
   temp = stack->head;
   int value = temp->value;

   stack->head = stack->head->next;
   delete temp;
   temp = nullptr;
   return value;
}

void emptyMemory(Stack *stack)
{
    while (!isEmpty(stack))
    {
        pop(stack);
    }
}
