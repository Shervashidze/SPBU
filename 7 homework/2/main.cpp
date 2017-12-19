#include <iostream>
#include "bst.h"
using namespace std;

void printMenu()
{
    cout << "Enter 1 to add number. \n";
    cout << "Enter 2 to delete number. \n";
    cout << "Enter 3 to check whether the number is in the set. \n";
    cout << "Enter 4 to print in ascending order. \n";
    cout << "Enter 5 to print in descending order. \n";
    cout << "Enter 6 to print in (a b c). \n";
    cout << "Enter 0 to exit. \n";
}

int main()
{
    BinarySearchTree *tree = createTree();
    printMenu();

    enum choices {end, addition, removeNumber, containsNumber, accPrint, desPrint, errorPrint};
    int choice = 0;
    cin >> choice;
    while (choice != 0)
    {
        int number = 0;
        switch (choice)
        {
            case end:
                cout << "Error";
                break;

            case addition:
                cout << "Enter number: ";
                cin >> number;
                add(tree, number);
                break;

            case removeNumber:
                cout << "Enter number: ";
                cin >> number;
                remove(tree, number);
                break;

            case containsNumber:
                cout << "Enter number: ";
                cin >> number;
                if (contains(tree, number))
                    cout << "Contains.\n";
                else
                    cout << "Dont contain.\n";
                break;

            case accPrint:
                cout << "Set: ";
                print(tree);
                break;

            case desPrint:
                cout << "Set: ";
                descendingPrint(tree);
                break;

            case errorPrint:
                cout << "Set: ";
                errorSearchPrint(tree);
                break;

            default:
                cout << "Unknown command.\n";
                break;
        }
        cin >> choice;
    }
}
