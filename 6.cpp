#include <stdio.h>
#include <iostream>
using namespace std;

int countEntrance(char s1[100], char s2[100], int len1, int len2)
{
    int answer = 0;
    for (int i = 0; i <= len1 - len2; i++)
    {

        int j = 0;
        while ((s1[i + j] == s2[j]) && (j < len2))
        {
            j++;
        }
        if (j == len2)
        {
            answer++;
        }
    }
    return answer;
}

int main()
{
        setlocale(0, "");

        cout << "Введите длину строки s1: ";
        int len1 = 0;
        cin >> len1;

        char s1[len1] = "";
        printf("Введите s1: ");
        cin >> s1;

        cout << "Введите длину строки s2: ";
        int len2 = 0;
        cin >> len2;

        char s2[len2] = "";
        printf("Введите s2: ");
        cin >> s2;

        printf("Количество вхождений: ");
        cout << countEntrance(s1, s2, len1, len2);
}
