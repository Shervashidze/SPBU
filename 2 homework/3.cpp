#include <iostream>
using namespace std;

void decomposition(int amount[], int number, int largestTerm, int termsAmount)
{
    if (number == 0)
    {
        for (int i = 0; i < termsAmount; i++)
        {
            cout << amount[i] << " ";
        }
        cout << "\n";
    }
    if (number > 0)
    {
        if (number - largestTerm >= 0)
        {
            amount[termsAmount] = largestTerm;
            decomposition(amount, number - largestTerm, largestTerm, termsAmount + 1);
        }
        if (largestTerm - 1 > 0)
        {
            decomposition(amount, number, largestTerm - 1, termsAmount);
        }
    }
}

int main()
{
        int number = 0;
        cout << "Enter N: ";
        cin >> number;

        int *amount = new int[number];
        for (int i = 0; i <= number; i++)
        {
            amount[i] = 0;
        }

        cout << "All views of N by the sum of the narutal terms:\n";
        decomposition(amount, number, number, 0);

        delete[] amount;
        amount = nullptr;

        return 0;
}
