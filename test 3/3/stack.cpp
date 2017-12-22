#include "stack.h"

struct StackElement
{
    char value;
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

void push(Stack *stack, char value)
{
    StackElement *newStack = new StackElement;
    newStack->value = value;
    newStack->next = stack->head;
    stack->head = newStack;
}

char pop(Stack *stack)
{
   if (isEmpty(stack))
   {
       return -17;
   }
   StackElement *temp = nullptr;
   temp = stack->head;
   char value = temp->value;

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
    delete stack;
}

char peek(Stack *stack)
{
    if (isEmpty(stack))
        return 'k';
    return stack->head->value;
}
