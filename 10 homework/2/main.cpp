#include <iostream>
#include "string.h"

using namespace std;

const int maxSize = 100000;
const int mod = 10007;
const int prime = 17;

int findHash(String *string)
{
    int result = 0;
    for (int i = 0; i < stringLength(string); i++)
        result = ((result * prime) % mod + (int) getChar(string, i)) % mod;

    return result;
}

void printConcurrences(String *string, String *wanted, int start)
{
    if (start + stringLength(wanted) > stringLength(string))
        return;

    int subHash = findHash(wanted);
    String *firstSlice = subString(string, start, start + stringLength(wanted) - 1);
    int currentHash = findHash(firstSlice);
    deleteString(firstSlice);

    int power = 1;
    for (int i = 1; i < stringLength(wanted); i++)
        power = (power * prime) % mod;

    for (int i = start; i <= stringLength(string) - stringLength(wanted); i++)
    {
        if (currentHash == subHash)
        {
            String *slice = subString(string, i, i + stringLength(wanted) - 1);
            if (isEqual(wanted, slice))
            {
                deleteString(slice);
                cout << i << ' ';
            }
            deleteString(slice);
        }
        if (i + stringLength(wanted) < stringLength(string))
            currentHash = (((((currentHash - ((int) getChar(string, i)) * power) % mod + mod) % mod) * prime) % mod+ getChar(string, i + stringLength(wanted))) % mod;
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

    cout << "Substrings first indexes: ";
    printConcurrences(string, subString, 0);

    deleteString(string);
    deleteString(subString);
}
