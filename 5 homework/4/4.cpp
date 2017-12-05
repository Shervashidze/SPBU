#include <iostream>
#include <fstream>

using namespace std;

const int maxWordSize = 10000;

int main()
{
    char fileName[maxWordSize];
    cout << "Enter file name: ";
    cin >> fileName;

    ifstream file(fileName);
    if (!file.is_open())
    {
        cout << "File not found.";
        int c = 0;
        cin >> c;
        return 0;
    }

    int answer = 0;
    char line[maxWordSize];
    while (!file.eof())
    {
        file.getline(line, maxWordSize, '\n');
        int counter = 0;
        while (line[counter] != '\0')
        {
            if (line[counter] != ' ' and line[counter] != '\t')
            {
                answer++;
                break;
            }
            counter++;
        }
    }

    file.close();
    cout << "Number of non-empty lines: " << answer;
}
