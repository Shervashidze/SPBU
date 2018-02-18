#include <iostream>

using namespace std;

int iterative(int number)
{
    if (number == 0)
    {
        return 1;
    }

    if (number == 1)
    {
        return 1;
    }

    int prevNumber = 1;
    int answer = 1;
    for (int i = 1; i < number; i++)
    {
        answer += prevNumber;
        prevNumber = answer - prevNumber;
    }

    return answer;
}

int main()
{
    int number = 0;
    printf("Enter number of Fibonacci: ");
    cin >> number;

    printf("answer: ");
    cout << iterative(number);

    return 0;
}
