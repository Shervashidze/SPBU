#include <iostream>

using namespace std;

int findColor(int *copies, int student)
{
    if (copies[student] != 0)
    {
        int color = findColor(copies, copies[student]);
        copies[student] = color;
        return color;
    }
    else if (student == 1)
        return 1;
    else if (student == 2)
        return 2;
    else if (student == 3)
        return 3;
    else
        return 0;
}

void takePairs(int *copies, int pairs)
{
    cout << "Enter pairs.\n";
    for (int i = 0; i < pairs; i++)
    {
        int student = 0;
        cin >> student;
        int copy = 0;
        cin >> copy;
        copies[student] = copy;
    }
}

void printStudents(int *copies, int amount)
{
    for (int i = 1; i <= amount; i++)
    {
        int color = findColor(copies, i);
        if (color == 0)
            cout << "Student " << i << " should be excluded\n";
        else
            cout << "Student " << i << " decision " << color << '\n';
    }
}

int main()
{
    int amount = 0;
    cout << "Enter number of students: ";
    cin >> amount;
    int pairs = 0;
    cout << "Enter number of pairs: ";
    cin >> pairs;

    int *copies = new int[amount + 1];
    for (int i = 0; i <= amount; i++)
        copies[i] = 0;

    takePairs(copies, pairs);

    printStudents(copies, amount);

    delete[] copies;
}
