#include <iostream>
using namespace std;

void farey(int number)
{
    int nominator = 0;
    int denominator = 1;
    int newNominator = 1;
    int newDenominator = number;

    cout << nominator << "/" << denominator << " ";

    while (newNominator <= number)
    {
        int k = (number + denominator) / newDenominator;

        int counter = nominator;
        nominator = newNominator;
        newNominator = (k * newNominator) - counter;

        counter = denominator;
        denominator = newDenominator;
        newDenominator = (k * newDenominator) - counter;

        cout << nominator << "/" << denominator << " ";
    }
}

int main()
{
        int number = 0;
        printf("Enter n: ");
        cin >> number;

        cout << "All irreducible fractions: ";

        farey(number);

        return 0;
}
