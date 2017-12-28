#pragma once

struct String
{
    char *string;
    int length;
};

String *createString(char *line);
void deleteString(String *string);
void concatenation(String *&string, String *secondString);
String *clone(String *string);
bool isEqual(String *string, String *secondString);
int stringLength(String *string);
bool isEmpty(String *string);
char *toChar(String *string);
char getChar(String *string, int index);
String *subString(String *string, int begin, int end);
