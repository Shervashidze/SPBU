#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
        setlocale(0, "");

        int length = 0;
        printf("������� ����� ������: ");
        cin >> length;

        char line[length + 1] = "";
        printf("������� ������: ");
        cin >> line;

        int left = 0;
        int right = length - 1;
        while ((left < right) && (line[left] == line[right]))
        {
            left++;
            right--;
        }

        if (left >= right)
        {
            printf("������ �������� �����������");
        }
        else
        {
            printf("������ �� �������� �����������");
        }
}
