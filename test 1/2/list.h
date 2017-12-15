#pragma once

struct List;
List *create();
void add(List *list, int value);
void emptyMemory(List *list);
int getValue(List *list, int index);
void print(List *list);
