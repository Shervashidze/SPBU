#include <iostream>
#include "stack.h"
using namespace std;

const int maxSizeOfExpression = 1000;

bool isHigher(Stack *stack, char statement)
{
    if (peek(stack) == '*' || peek(stack) == '/')
        return true;
    else
        return ((statement == '+' || statement == '-') && peek(stack) != '(');
}

void processCharacter(Stack *stack, char term, char output[], int &outputPosition)
{
    if (term == '(')
        push(stack, term);
    else if (term == '+'|| term == '-' || term == '*' || term == '/')
    {
        while (!isEmpty(stack) && isHigher(stack, term))
        {
            output[outputPosition] = pop(stack);
            outputPosition++;
        }
        push(stack, term);
    }
    else if (term == ')')
    {
        while (peek(stack) != '(')
        {
            output[outputPosition] = pop(stack);
            outputPosition++;
        }
        pop(stack);
    }
    else if ((term - '0') >= 0 && (term - '0') <= 9)
    {
        output[outputPosition] = term;
        outputPosition++;
    }
}

int main()
{
    cout << "Enter expression: ";
    Stack *stack = createStack();
    char input[maxSizeOfExpression] = " ";
    char output[maxSizeOfExpression] = " ";
    cin.get(input, maxSizeOfExpression);

    int outputPosition = 0;
    int inputPosition = 0;

    while (input[inputPosition] != '\0')
    {
        processCharacter(stack, input[inputPosition], output, outputPosition);
        inputPosition++;
    }

    while (!isEmpty(stack))
    {
        output[outputPosition] = pop(stack);
        outputPosition++;
    }

    cout << "Reverse Polish notation: ";
    for (int counter = 0; counter < outputPosition; counter++)
    {
        cout << output[counter] << ' ';
    }

    emptyMemory(stack);
    delete stack;
    stack = nullptr;
}
