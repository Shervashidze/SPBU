#include <cstdlib>
#include <ctime>
#include <iostream>
using namespace std;

int largestUserInput = 100;
int numberLength = 4;


int *buildRandom()
{

    srand(time(NULL));
    int length = 10;
    int digits[length] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int digitNumber = 0;

    int *number = new int[numberLength];
    for (int i = 0; i < numberLength; i++)
    {
        digitNumber = (rand() % length) + 0;
        number[i] = digits[digitNumber];
        swap(digits[digitNumber], digits[length - 1]);
        length--;
    }

    if (number[0] == 0)
    {
        swap(number[0], number[1]);
    }

    return number;
}

void cows(int number[], int userNumber[])
{
    int answer = 0;
    for (int i = 0; i < numberLength; i++)
    {
        for (int j = 0; j < numberLength; j++)
        {
            if ((userNumber[i] == number[j]) && (i != j))
            {
                answer++;
            }
        }
    }

    cout << "Cows amount: " << answer << "\n";
    return;
}

void bulls(int number[], int userNumber[])
{
    int answer = 0;
    for (int i = 0; i < numberLength; i++)
    {
        if (number[i] == userNumber[i])
        {
            answer++;
        }
    }

    cout << "Bulls amount: " << answer << "\n";
    return;
}

int *attemptToUserNumber(char attempt[])
{
    int *userNumber = new int[numberLength];
    for (int i = 0; i < numberLength; i++)
    {
        userNumber[i] = attempt[i] - '0';
    }

    return userNumber;
}

char userConsoleChoose()
{
    char choose[largestUserInput] = " ";
    cin >> choose;
    while (choose[0] != '0' && choose[0] != '1' && choose[0] != '2' && choose[1] != '\0')
    {
        cout << "Enter correct digit: ";
        cin >> choose;
    }
    return choose[0];
}

char *userAttempt()
{
    char *attempt = new char[largestUserInput];
    cin >> attempt;

    return attempt;
}

bool isCorrectUserAttempt(char attempt[])
{
    int length = 0;
    while (attempt[length] != '\0')
    {
        if ((attempt[length] - '0' < 0) || (attempt[length] - '0' > 9))
        {
            return false;
        }
        length++;

        if (length > numberLength)
        {
            return false;
        }
    }

    if (length < numberLength)
    {
        return false;
    }

    return true;
}

bool isItVictory(int computerNumberDigits[], int userNumberDigits[])
{
    for (int i = 0; i < numberLength; i++)
    {
        if (computerNumberDigits[i] != userNumberDigits[i])
        {
            return false;
        }
    }

    return true;
}

void emptyMemory(char attempt[], int userNumberDigits[])
{
    delete[] attempt;
    attempt = nullptr;

    delete[] userNumberDigits;
    userNumberDigits = nullptr;
}

void printComputerNumber(int computerNumberDigits[])
{
    for (int i = 0; i < numberLength; i++)
    {
        cout << computerNumberDigits[i];
    }
}
