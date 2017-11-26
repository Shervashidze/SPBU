#pragma once

struct List;

List *create();
void addElement(List *list, int value);
int deleteNextElement(List *list);
bool isAlone(List *list);
void move(List *list, int number);
void emptyMemory(List *list);
