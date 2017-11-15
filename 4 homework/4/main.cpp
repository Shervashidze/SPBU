#include <iostream>
#include "stack.h"
using namespace std;

const int maxSize = 1000;

bool isNumber(char symbol)
{
    symbol = symbol - '0';
    return (symbol >= 0 && symbol <= 9);
}

void processCharacter(Stack *stack, char symbol)
{
    if (isNumber(symbol))
        push(stack, symbol - '0');
    else if (symbol != ' ')
    {
        int value = pop(stack);
        int prevValue = pop(stack);
        if (symbol == '+')
        {
            push(stack, value + prevValue);
        }
        else if (symbol == '-')
        {
            push(stack, prevValue - value);
        }
        else if (symbol == '*')
        {
            push(stack, prevValue * value);
        }
        else if (symbol == '/')
        {
            push(stack, prevValue / value);
        }
    }
}

int main()
{
    cout << "Enter expression in Reverse Polish notation: ";
    Stack *stack = createStack();
    char term[maxSize] = " ";
    cin.get(term, maxSize);

    int counter = 0;
    while (term[counter] != '\0')
    {
        processCharacter(stack, term[counter]);
        counter++;
    }

    cout << "Answer: ";
    cout << pop(stack);
    emptyMemory(stack);
    delete stack;
    stack = nullptr;
}
