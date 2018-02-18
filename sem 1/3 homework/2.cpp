#include <iostream>

using namespace std;

const int lettersAmmount = 123;
const int maxStringLength = 1000;

void charDecomposition(char string[], int stringDecomposition[])
{
    int counter = 0;
    while (string[counter] != '\0')
    {
        int temp = (int)string[counter];
        stringDecomposition[temp]++;
        counter++;
    }
}

int main()
{
    char firstString[maxStringLength];
    cout << "Enter first string: ";
    cin >> firstString;
    char secondString[maxStringLength];
    cout << "Enter second string: ";
    cin >> secondString;

    int firstStringDecomposition[lettersAmmount];
    int secondStringDecomposition[lettersAmmount];
    for (int i = 0; i < lettersAmmount; i++)
    {
        firstStringDecomposition[i] = 0;
        secondStringDecomposition[i] = 0;
    }
    charDecomposition(firstString, firstStringDecomposition);
    charDecomposition(secondString, secondStringDecomposition);
    bool answer = true;
    for (int i = 0; i < lettersAmmount; i++)
    {
        if (firstStringDecomposition[i] != secondStringDecomposition[i])
        {
            answer = false;
            break;
        }
    }
    if (answer)
    {
        cout << "True.";
    }
    else
    {
        cout << "False.";
    }
    return 0;
}
