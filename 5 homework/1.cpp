#include <iostream>
using namespace std;

char sign(double number)
{
    unsigned char *b = reinterpret_cast<unsigned char*> (&number);
    if (b[7] & 0x80)
        return '-';
    else
        return '+';
}

double mantissa(double number)
{
    unsigned char *b = reinterpret_cast<unsigned char*> (&number);
    b[7] = 0x3F;
    b[6] = b[6] | 0xF0;
    return number;
}

int power(double number)
{
    unsigned char *b = reinterpret_cast<unsigned char*> (&number);
    int answer = b[7] & 0x7F;
    answer = answer << 4;
    answer = answer | (b[6] >> 4);
    answer = answer - ((1 << 10) - 1);
    return answer;
}

int main()
{
    double number = 0.0;
    cout << "Enter the number: ";
    cin >> number;
    cout.precision(25);
    cout << "Result: " << sign(number) << mantissa(number) << "*2^" << power(number);
    return 0;
}
