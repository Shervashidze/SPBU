#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
        setlocale(0, "");

        float a = 0;
        cout << "¬ведите a: ";
        cin >> a;

        int n = 0;
        cout << "¬ведите n: ";
        cin >> n;

        if (n == 0)
        {
            cout << "a^n = " << "1";
        }

        if (n > 0)
        {
            float answer = 1;
            for (int i = 0; i < n; i++)
            {
                answer *= a;
            }

            cout << "a^n = " << answer;
        }

        if (n < 0)
        {
            n = -n;

            float answer = 1;

            for (int i = 0; i < n; i++)
            {
                answer = (answer) / a;
            }

            cout << "a^n = " << answer;
        }
        return 0;
}
