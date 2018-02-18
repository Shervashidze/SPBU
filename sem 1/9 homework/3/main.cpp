#include <fstream>
#include <iostream>
#include "string.h"
#include "huffman.h"

using namespace std;

const int maxSize = 100000;

int main()
{
    char fileName[maxSize];
    cout << "Enter input file name: ";
    cin >> fileName;

    ifstream input(fileName);
    if (!input.is_open())
    {
        cout << "File not found.";
        int c = 0;
        cin >> c;
        return 0;
    }

    cout << "Enter output file name: ";
    cin >> fileName;
    ofstream output(fileName);

    decode(input, output);
    input.close();
    output.close();
    return 0;
}
