#include <iostream>
#include "bst.h"
using namespace std;

void menu(BinarySearchTree *tree)
{
    cout << "Enter 1 to add number. \n";
    cout << "Enter 2 to delete number. \n";
    cout << "Enter 3 to check whether the number is in the set. \n";
    cout << "Enter 4 to print in ascending order. \n";
    cout << "Enter 5 to print in descending order. \n";
    cout << "Enter 6 to print in (a b c). \n";
    cout << "Enter 0 to exit. \n";

    int choose = 0;
    cin >> choose;

    if (choose == 0)
    {
        clearTree(tree);
        delete tree;
        tree = nullptr;
        return;
    }
    if (choose == 1)
    {
        cout << "Enter number: ";
        int number = 0;
        cin >> number;
        add(tree, number);
    }
    if (choose == 2)
    {
        cout << "Enter number: ";
        int number = 0;
        cin >> number;
        remove(tree, number);
    }
    if (choose == 3)
    {
        cout << "Enter number: ";
        int number = 0;
        cin >> number;
        if (contains(tree, number))
            cout << "Contains.";
        else
            cout << "Dont contain.";
    }
    if (choose == 4)
    {
        cout << "Set: ";
        print(tree);
    }
    if (choose == 5)
    {
        cout << "Set: ";
        descendingPrint(tree);
    }
    if (choose == 6)
    {
        cout << "Set: ";
        errorSearchPrint(tree);
    }

    cout << "\nEnter 0 to exit.\n";
    cout << "Enter 1 to return in menu.\n";
    cin >> choose;
    if (choose == 0)
        return;
    else
        menu(tree);
}

int main()
{
    BinarySearchTree *tree = createTree();
    menu(tree);
    delete tree;
    tree = nullptr;
}
