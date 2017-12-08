#pragma once

struct List;
List *create();
void add(List *list, int value);
void emptyMemory(List *list);
void sort(List *list);
void print(List *list);
