#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
        setlocale(0, "");

        int length = 0;
        printf("������� ����� ������ ������: ");
        cin >> length;

        printf("������� ������ ������: ");
        char bracket[length + 1] = "";
        cin >> bracket;

        int i = 0;
        int counter = 0;
        while (i < length)
        {
            if (bracket[i] == '(')
            {
                counter++;
            }
            if (bracket[i] == ')')
            {
                counter--;
            }
            if (counter < 0)
            {
                break;
            }
            i++;
        }

        if (counter == 0)
        {
            printf("������ ������ ��������");
        }
        if (counter != 0)
        {
            printf("������ ������ �� ��������");
        }
}
