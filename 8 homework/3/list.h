#pragma once
#include "string.h"

struct List;
List *create();
void addInList(List *list, String *string);
void emptyMemory(List *list);
void print(List *list);
bool isListEmpty(List *list);
int getListSize(List *list);
