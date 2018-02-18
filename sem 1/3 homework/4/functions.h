#pragma once

int *buildRandom();
void cows(int number[], int attempt[]);
void bulls(int number[], int attempt[]);
int *attemptToUserNumber(char attempt[]);
bool isCorrectUserAttempt(char attempt[]);
char userConsoleChoose();
char *userAttempt();
bool isItVictory(int computerNumberDigits[], int userNumberDigits[]);
void emptyMemory(char attempt[], int userNumberDigits[]);
void printComputerNumber(int computerNumberDigits[]);
