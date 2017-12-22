#include <iostream>

using namespace std;

enum class Status
{
    start,
    afterSigh,
    wholePart,
    afterDot,
    fraction,
    afterE,
    afterESigh,
    digitAfterE,
    fail
};

bool isDigit(char symbol)
{
    return symbol >= '0' && symbol <= '9';
}

bool isCorrectEnd(Status status)
{
    return status == Status::wholePart || status == Status::fraction || status == Status::digitAfterE;
}

int main()
{
    Status status = Status::start;

    cout << "Enter number: ";
    char symbol = cin.get();

    while (symbol != '\n')
        {
            switch (status)
            {
                case Status::start:
                    if (symbol == '+' || symbol == '-')
                        status = Status::afterSigh;
                    else if (isDigit(symbol))
                        status = Status::wholePart;
                    else
                        status = Status::fail;
                    break;

                case Status::afterSigh:
                    if (isDigit(symbol))
                        status = Status::wholePart;
                    else
                        status = Status::fail;
                    break;

                case Status::wholePart:
                    if (isDigit(symbol))
                        status = Status::wholePart;
                    else if (symbol == '.')
                        status = Status::afterDot;
                    else if (symbol == 'E')
                        status = Status::afterE;
                    else
                        status = Status::fail;
                    break;

                case Status::afterDot:
                    if (isDigit(symbol))
                        status = Status::fraction;
                    else
                        status = Status::fail;
                    break;

                case Status::fraction:
                    if (isDigit(symbol))
                        status = Status::fraction;
                    else if (symbol == 'E')
                        status = Status::afterE;
                    else
                        status = Status::fail;
                    break;

                case Status::afterE:
                    if (symbol == '+' || symbol == '-')
                        status = Status::afterESigh;
                    else if (isDigit(symbol))
                        status = Status::digitAfterE;
                    else
                        status = Status::fail;
                    break;

                case Status::afterESigh:
                    if (isDigit(symbol))
                        status = Status::digitAfterE;
                    else
                        status = Status::fail;
                    break;

                case Status::digitAfterE:
                    if (!isDigit(symbol))
                        status = Status::fail;
                    break;
            }
            symbol = cin.get();
    }

    if (isCorrectEnd(status))
        cout << "This is the real number.";
    else
        cout << "This is not the real number.";
}
