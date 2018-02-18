#include <iostream>

using namespace std;

enum class Status
{
    start,
    repetetivePart,
    fail
};

bool isLetter(char value)
{
    return (value >= 76 && value <= 122) || (value >= 65 && value <= 90);
}

bool isDigit(char value)
{
    return (value - '0' >= 0) && (value - '0' <= 9);
}

int main()
{
    cout << "Enter expression: ";
    char symbol = cin.get();
    Status status = Status::start;

    while (symbol != '\n')
    {
        switch (status)
        {
            case Status::start:
                if (isLetter(symbol))
                    status = Status::repetetivePart;
                else
                    status = Status::fail;
                break;
            case Status::repetetivePart:
                if (isLetter(symbol) || isDigit(symbol) || symbol == '_')
                    status = Status::repetetivePart;
                else
                    status = Status::fail;
        }
        symbol = cin.get();
    }

    if (status == Status::repetetivePart)
        cout << "Correct.";
    else
        cout << "Incorrect.";

    return 0;
}
