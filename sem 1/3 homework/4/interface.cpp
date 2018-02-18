#include "functions.h"
#include "interface.h"
#include <iostream>
using namespace std;

char rules()
{
    cout << "There is 4-digit secret number.\n";
    cout << "You should try to guess right number.\n";
    cout << "All digits are different.\n";
    cout << "If the matching digits are in their right positions, they are 'bulls'.\n";
    cout << "if in different positions, they are 'cows'.\n";
    cout << "Good luck!\n";
    cout << "Enter '2' to return in main menu.\n";
    cout << "Enter '1' to start the game.\n";
    cout << "Enter '0' to exit.\n";

    char userChoose = userConsoleChoose();
    return userChoose;
}

void game()
{
    int *computerNumberDigits = buildRandom();

    int counter = 1;
    while (counter >= 0)
    {   
        cout << "Attempt " << counter << ". Enter number: ";

        char *attempt = userAttempt();

        if (attempt[0] == '4' && attempt[1] == '2' && attempt[2] == '\0')
        {
            cout << "Ultimate answer to life, the universe, and everything: ";
            printComputerNumber(computerNumberDigits);
            cout << "\n";
            continue;
        }

        if (not isCorrectUserAttempt(attempt))
        {
            cout << "Wrong number!\nTry again.\n";
            continue;
        }

        int *userNumberDigits = attemptToUserNumber(attempt);

        if (isItVictory(computerNumberDigits, userNumberDigits))
        {
            cout << "Congratulations, You win!\n";
            counter = -1;
        }
        else
        {
            cows(computerNumberDigits, userNumberDigits);
            bulls(computerNumberDigits, userNumberDigits);
            counter++;
        }

        emptyMemory(attempt, userNumberDigits);
    }

    delete[] computerNumberDigits;
    computerNumberDigits = nullptr;

    cout << "Enter '1' to return in main menu.\n";
    cout << "Enter '0' to exit.\n";

    char userChoose = userConsoleChoose();

    while (userChoose == '2')
    {
        cout << "Enter correct number: ";
        userChoose = userConsoleChoose();
    }

    if (userChoose == '1')
    {
        menu();
    }

    return;
}

void menu()
{
    cout << "Bulls and Cows!\n";
    cout << "Enter '2' to open the rules.\n";
    cout << "Enter '1' to start the game.\n";
    cout << "Enter '0' to exit.\n";

    char userChoose = userConsoleChoose();

    if (userChoose == '2')
    {
        userChoose = rules();
        if (userChoose == '2')
        {
            menu();
        }
    }

    if (userChoose == '0')
    {
        return;
    }

    if (userChoose == '1')
    {
        game();
    }

    return;
}
