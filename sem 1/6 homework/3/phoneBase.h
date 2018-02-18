#pragma once

struct List;
List *create();
void add(List *list, char *name, char *telephone);
void findTelephone(List *list, char *name);
void findName(List *list, char *telephone);
void print(List *list);
void addFromFile(List *list);
void save(List *list);
void emptyMemory(List *list);
