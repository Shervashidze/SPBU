#include <iostream>
#include <fstream>

using namespace std;
const int maxWordSize = 10000;

int main()
{
    ifstream file("d:\\file.txt");
    char *word = new char[maxWordSize];
    int *usedCharacters = new int[maxWordSize];

    for (file >> word; !file.eof(); file >> word)
    {
        for (int i = 0; i < 128; i++)
            usedCharacters[i] = 0;

        int counter = 0;
        while (word[counter] != '\0' and word[counter] != '\t')
        {
            if (usedCharacters[(int)word[counter]] == 0)
            {
                cout << word[counter];
                usedCharacters[(int)word[counter]] = 1;
            }
            counter++;
        }
        cout << ' ';
    }

    file.close();
    delete[] word;
    delete[] usedCharacters;
    word = nullptr;
    usedCharacters = nullptr;
}
