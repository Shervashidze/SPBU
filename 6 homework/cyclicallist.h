#pragma once

struct List;

List *init(int number);
void addElement(List *list, int value);
int deleteNextElement(List *&list);
bool isAlone(List *list);
void move(List *&lost, int number);
void emptyMemory(List *&list);
