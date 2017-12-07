#include <iostream>

using namespace std;

const int maxSize = 100000;
const int maxChars = 100;

int *toDigits(int number)
{
    int *reverse = new int[maxChars];
    int *digits = new int[maxChars];
    for (int i = 0; i < maxChars; i++)
        digits[i] = -1;
    int counter = 0;
    while (number > 0)
    {
        reverse[counter] = number % 10;
        number = number / 10;
        counter++;
    }

    for (int i = counter - 1; i >= 0; i--)
        digits[i] = reverse[counter - i - 1];

    delete[] reverse;
    return digits;
}

void isFirstMinus(int array[], char lowerString[], char upperString[], int &index)
{
    if (array[0] < 0)
    {
        lowerString[index] = '-';
        upperString[index] = ' ';
        index++;
        array[0] = -array[0];
    }

}

void addChars(char upperString[], char lowerString[], char first, char second, int &index)
{
    upperString[index] = first;
    lowerString[index] = second;
    index++;
}

void processIndex(int i, int array[], char upperString[], char lowerString[], int &index, int &power, int length)
{
    if (array[i] == 0)
    {
        power--;
        return;
    }

    if (array[i] < 0 && i != 0)
    {
        lowerString[index - 2] = '-';
        array[i] = -array[i];
    }

    int *digits = toDigits(array[i]);
    int counter = 0;
    while (digits[counter] != -1)
    {
        if (digits[counter] == 1 && digits[counter + 1] == -1 && counter == 0 && i != length - 1)
           break;
        else
            addChars(upperString, lowerString, ' ', digits[counter] + '0', index);

        counter++;
    }
    addChars(upperString, lowerString, ' ', 'x', index);
    delete[] digits;
    digits = nullptr;

    digits = toDigits(power);
    counter = 0;
    while (digits[counter] != -1)
    {
        if (digits[counter] == 1 && counter == 0 && digits[counter + 1] == -1)
        {
            addChars(upperString, lowerString, ' ', ' ', index);
            break;
        }

        addChars(upperString, lowerString, digits[counter] + '0', ' ', index);
        counter++;
    }
    power--;
    delete[] digits;
    digits = nullptr;

    addChars(upperString, lowerString, ' ', '+', index);
    addChars(upperString, lowerString, ' ', ' ', index);
}

void printPolynomial(int array[], int length)
{
    char upperString[maxSize];
    char lowerString[maxSize];
    int power = length - 1;

    int index = 0;
    isFirstMinus(array, lowerString, upperString, index);

    for (int i = 0; i < length; i++)
    {
        processIndex(i, array, upperString, lowerString, index, power, length);
    }

    if (array[length - 1] != 0)
        lowerString[index - 3] = ' ';

    lowerString[index - 2] = ' ';
    cout << upperString << '\n';
    cout << lowerString << '\n';
}

int main()
{
    int length = 0;
    cout << "Enter array length: ";
    cin >> length;

    cout << "Enter array: ";
    int *array = new int[length];
    for (int i = 0; i < length; i++)
        cin >> array[i];

    if (array[0] == 0)
    {
        cout << "the largest degree of a polynomial cant be zero.";
        return 0;
    }

    printPolynomial(array, length);
    delete[] array;
}
