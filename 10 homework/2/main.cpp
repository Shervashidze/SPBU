#include <iostream>
#include "string.h"

using namespace std;

const int maxSize = 100000;
const int mod = 1009;
const int prime = 17;

int getHash(String *string, int start, int end)
{
    int result = 0;
    for (int i = start; i < end; i++)
        result = ((result * prime) % mod + (int) getChar(string, i)) % mod;

    return result;
}

void getEntrances(String *string, String *wanted, int *indexes)
{
    if (stringLength(wanted) > stringLength(string))
        return;

    int start = 0;
    int subHash = getHash(wanted, 0, stringLength(wanted));
    int currentHash = getHash(string, 0, stringLength(wanted));

    int power = 1;
    for (int i = 1; i < stringLength(wanted); i++)
        power = (power * prime) % mod;

    int counter = 0;
    for (int i = start; i <= stringLength(string) - stringLength(wanted); i++)
    {
        if (currentHash == subHash)
        {
            String *slice = subString(string, i, i + stringLength(wanted) - 1);
            if (isEqual(wanted, slice))
            {
                indexes[counter] = i + 1;
                counter++;
            }
            deleteString(slice);
        }
        currentHash = (((((currentHash - ((int) getChar(string, i)) * power) % mod + mod) % mod) * prime) % mod
                      + getChar(string, i + stringLength(wanted))) % mod;
    }
}

int main()
{
    char word[maxSize];
    cout << "Enter a sting: ";
    cin >> word;
    String *string = createString(word);

    cout << "Enter a substring: ";
    cin >> word;
    String *subString = createString(word);

    int indexes[maxSize];
    for (int i = 0; i < maxSize; i++)
        indexes[i] = -1;
    getEntrances(string, subString, indexes);

    int counter = 0;
    if (indexes[counter] == -1)
    {
        cout << "There is no Entrances.";
        return 0;
    }

    cout << "Substrings first indexes: ";
    while (indexes[counter] != -1)
    {
        cout << indexes[counter] << ' ';
        counter++;
    }
    deleteString(string);
    deleteString(subString);

    return 0;
}
