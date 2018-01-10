#include <iostream>
#include "string.h"

using namespace std;

void isEqualCorrect()
{
    char a[15] = "abcde";
    char b[12] = "abcde";
    String *firstString = createString(a);
    String *secondString = createString(b);
    if (isEqual(firstString, secondString))
        cout << "isEqual correct.\n";
    else
        cout << "Problems with isEqual.\n";
    deleteString(firstString);
    deleteString(secondString);
}

void isConcatenationCorrect()
{
    char a[15] = "abc";
    char b[15] = "dfe";
    char c[15] = "abcdfe";
    String *aString = createString(a);
    String *bString = createString(b);
    String *cString = createString(c);
    concatenation(aString, bString);
    if (isEqual(aString, cString))
        cout << "Concatenation correct.\n";
    else
        cout << "Problems with concatenation.\n";
    deleteString(aString);
    deleteString(bString);
    deleteString(cString);
}

void isCloneCorrect()
{
    char a[15] = "abcd";
    String *aString = createString(a);
    String *cloned = clone(aString);
    if (isEqual(cloned, aString))
        cout << "Clone correct.\n";
    else
        cout << "Problems with clone.\n";
    deleteString(aString);
    deleteString(cloned);
}

void isSubStringCorrect()
{
    char a[15] = "abcde";
    char b[15] = "bcd";
    String *aString = createString(a);
    String *bString = createString(b);
    String *sub = subString(aString, 1, 3);
    if (isEqual(bString, sub))
        cout << "SubString correct.\n";
    else
        cout << "Problems with subString.\n";
    deleteString(aString);
    deleteString(bString);
    deleteString(sub);
}

void test()
{
    isEqualCorrect();
    isConcatenationCorrect();
    isCloneCorrect();
    isSubStringCorrect();
}

int main()
{
    test();

    return 0;
}
