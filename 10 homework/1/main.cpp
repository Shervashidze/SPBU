#include <iostream>
#include <fstream>
#include "map.h"
#include "priorityqueue.h"

const int maxSize = 10000;

using namespace std;

struct Map
{
    int rows;
    int columns;
    Tile ***vertexes;
};

Map *EnterMap(istream &stream)
{
    Map *map = new Map;
    stream >> map->rows >> map->columns;
    map->vertexes = new Tile**[map->rows];
    for (int i = 0; i < map->rows; i++)
    {
        map->vertexes[i] = new Tile*[map->columns];
        char current = '\0';
        for (int j = 0; j < map->columns; j++)
        {
            stream >> current;
            map->vertexes[i][j] = newTile(current == '0', i, j);
        }
    }
    return map;
}

void printMap(Map *map)
{
    for (int i = 0; i < map->rows; i++)
    {
        for (int j = 0; j < map->columns; j++)
            if (isInPath(map->vertexes[i][j]))
                cout << "*";
            else if (isFree(map->vertexes[i][j]))
                cout << "0";
            else
                cout << "1";
        cout << '\n';
    }
}

void deleteMap(Map *map)
{
    for (int i = 0; i < map->rows; i++)
    {
        for (int j = 0; j < map->columns; j++)
            deleteTile(map->vertexes[i][j]);
        delete[] map->vertexes[i];
    }
    delete[] map->vertexes;
    delete map;
}

void relax(Tile *relaxed, Tile *current, Queue *queue, int euristicValue)
{
    if (contains(queue, relaxed))
        pop(queue);
    setDistance(relaxed, distance(current) + 1);
    setPrevios(relaxed, current);
    setPriority(relaxed, distance(relaxed) + euristicValue);
    append(queue, relaxed);
}

int euristic(int x, int y, int finishX, int finishY)
{
    return abs(finishX - x) + abs(finishY - y);
}

void AStar(Map *map, int startX, int startY, int finishX, int finishY)
{
    if (!isFree(map->vertexes[startX][startY]))
        return;

    Queue *priorityQueue = createQueue();

    setDistance(map->vertexes[startX][startY], 0);
    setPriority(map->vertexes[startX][startY], euristic(startX, startY, finishX, finishY));

    append(priorityQueue, map->vertexes[startX][startY]);

    while(!isEmpty(priorityQueue) && !isVisited(map->vertexes[finishX][finishY]))
    {
        Tile *current = pop(priorityQueue);
        setVisited(current);

        int x = tileX(current);
        int y = tileY(current);

        if (x - 1 >= 0 && isFree(map->vertexes[x - 1][y]) && distance(map->vertexes[x - 1][y]) > distance(current) + 1)
            relax(map->vertexes[x - 1][y], current, priorityQueue, euristic(x - 1, y, finishX, finishY));
        if (y - 1 >= 0 && isFree(map->vertexes[x][y - 1]) && distance(map->vertexes[x][y - 1]) > distance(current) + 1)
            relax(map->vertexes[x][y - 1], current, priorityQueue, euristic(x, y - 1, finishX, finishY));
        if (x + 1 < map->rows && isFree(map->vertexes[x + 1][y]) && distance(map->vertexes[x + 1][y]) > distance(current) + 1)
            relax(map->vertexes[x + 1][y], current, priorityQueue, euristic(x + 1, y, finishX, finishY));
        if (y + 1 < map->columns && isFree(map->vertexes[x][y + 1]) && distance(map->vertexes[x][y + 1]) > distance(current) + 1)
            relax(map->vertexes[x][y + 1], current, priorityQueue, euristic(x, y + 1, finishX, finishY));
    }

    deleteQueue(priorityQueue);

    if (isVisited(map->vertexes[finishX][finishY]))
    {
        Tile *current = map->vertexes[finishX][finishY];
        setInPath(current);
        while (current != map->vertexes[startX][startY])
        {
            current = previous(current);
            setInPath(current);
        }
    }
}

int main()
{
    char fileName[maxSize];
    cout << "Enter input file name: ";
    cin >> fileName;

    ifstream input(fileName);
    if (!input.is_open())
    {
        cout << "File not found.";
        int c = 0;
        cin >> c;
        return 0;
    }

    Map *map = EnterMap(input);
    input.close();

    int startX = 0;
    int startY = 0;
    int finishX = 0;
    int finishY = 0;
    printMap(map);
    cout << "Columns Y\n";
    cout << "Rows X\n";
    cout << "Enter X and Y of start: (X <= " << map->rows - 1 << "), (Y <= " << map->columns - 1 << ")\n";
    cin >> startX >> startY;
    cout << "Enter X and Y of finish: (X <= " << map->rows - 1 << "), (Y <= " << map->columns - 1 << ")\n";
    cin >> finishX >> finishY;

    AStar(map, startX, startY, finishX, finishY);

    if (isVisited(map->vertexes[finishX][finishY]))
    {
        cout << "Path is found. Length: " << distance(map->vertexes[finishX][finishY]) << endl;
        printMap(map);
    }
    else
    {
        cout << "Path not found\n";
    }

    deleteMap(map);
    return 0;
}
