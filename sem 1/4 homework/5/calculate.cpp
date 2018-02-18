#include "calculate.h"
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

void processCharacterPN(Stack *stack, char term, char output[], int &outputPosition)
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

char *sortStation()
{
    Stack *stack = createStack();
    char input[maxSizeOfExpression] = " ";
    char *output = new char[maxSizeOfExpression];
    cin.get(input, maxSizeOfExpression);

    int outputPosition = 0;
    int inputPosition = 0;

    while (input[inputPosition] != '\0')
    {
        processCharacterPN(stack, input[inputPosition], output, outputPosition);
        inputPosition++;
    }
    while (!isEmpty(stack))
    {
        output[outputPosition] = pop(stack);
        outputPosition++;
    }

    output[outputPosition] = '\0';
    emptyMemory(stack);
    delete stack;
    stack = nullptr;
    return output;
}

bool isNumber(char symbol)
{
    symbol = symbol - '0';
    return (symbol >= 0 && symbol <= 9);
}

void processCharacterCS(Stack *stack, char symbol)
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

int countingStation(char *output)
{
    Stack *stack = createStack();
    int counter = 0;
    while (output[counter] != '\0')
    {
        processCharacterCS(stack, output[counter]);
        counter++;
    }
    int answer = pop(stack);
    emptyMemory(stack);
    delete stack;
    stack = nullptr;
    delete[] output;
    output = nullptr;
    return answer;
}

int calculate()
{
    char *output = sortStation();
    return countingStation(output);
}
