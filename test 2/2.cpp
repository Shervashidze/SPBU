#include <iostream>
#include <fstream>
using namespace std;

const int maxWordSize = 10000;
const int inf = 1000000;

bool isDigit(char digit)
{
    return digit - '0' >= 0 && digit - '0' <=9;
}

bool isDate(char *word)
{
    bool digits = isDigit(word[0]) && isDigit(word[1]) && isDigit(word[3]) && isDigit(word[4]) && isDigit(word[6]) && isDigit(word[7]) && isDigit(word[8]) && isDigit(word[9]);
    bool symbols = word[2] == '.' && word[5] == '.' && word[10] == '\0';
    return digits && symbols;
}

int toDigit(char digit)
{
    return digit - '0';
}

int year(char *word)
{
    return toDigit(word[6]) * 1000 + toDigit(word[7]) * 100 + toDigit(word[8]) * 10 + toDigit(word[9]);
}

int month(char *word)
{
    return toDigit(word[3]) * 10 + toDigit(word[4]);
}

int day(char *word)
{
    return toDigit(word[0]) * 10 + toDigit(word[1]);
}

void rewrite(int *minData, char *word)
{
    minData[0] = day(word);
    minData[1] = month(word);
    minData[2] = year(word);
}

void printTwoChars(int number)
{
    if (number < 10)
        cout << '0' << number;
    else
        cout << number;
    cout << '.';
}

void printYear(int number)
{
    if (number < 10)
        cout << "000" << number;
    else if (number < 100)
        cout << "00" << number;
    else if (number < 1000)
        cout << '0' << number;
    else
        cout << number;
}

int main()
{
    ifstream file("d:\\file.txt");
    char *word = new char[maxWordSize];
    int minData[3];
    for (int i = 0; i < 3; i++)
        minData[i] = inf;

    for (file >> word; !file.eof(); file >> word)
    {
        if (isDate(word))
        {
            if (year(word) < minData[2])
                rewrite(minData, word);
            else if (year(word) == minData[2] && month(word) < minData[1])
                rewrite(minData, word);
            else if (year(word) == minData[2] && month(word) == minData[1] && day(word) < minData[0])
                rewrite(minData, word);
        }
    }

    file.close();
    delete[] word;
    word = nullptr;

    printTwoChars(minData[0]);
    printTwoChars(minData[1]);
    printYear(minData[2]);
    return 0;
}
