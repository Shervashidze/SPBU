#include "map.h"

int const inf = 2000000000;

struct Tile
{
    bool isNotWall;
    bool isVisited;
    Tile *previous;
    bool isInPath;
    int frequency;
    int distance;

    int x;
    int y;
};

Tile *newTile(bool isFree, int i, int j)
{
    Tile *tile = new Tile;
    tile->isNotWall = isFree;
    tile->isInPath = false;
    tile->isVisited = false;
    tile->previous = nullptr;
    tile->frequency = inf;
    tile->distance = inf;
    tile->x = i;
    tile->y = j;
    return tile;
}


void deleteTile(Tile *tile)
{
    delete tile;
}

void setVisited(Tile *tile)
{
    tile->isVisited = true;
}

Tile *previous(Tile *tile)
{
    return tile->previous;
}

void setDistance(Tile *tile, int distance)
{
    tile->distance = distance;
}

int distance(Tile *tile)
{
    return tile->distance;
}

int tileX(Tile *tile)
{
    return tile->x;
}

void setPrevios(Tile *tile, Tile *previous)
{
    tile->previous = previous;
}

void setInPath(Tile *tile)
{
    tile->isInPath = true;
}

void setPriority(Tile *tile, int priority)
{
    tile->frequency = priority;
}

bool isVisited(Tile *tile)
{
    return tile->isVisited;
}

bool isInPath(Tile *tile)
{
    return tile->isInPath;
}

int priority(Tile *tile)
{
    return tile->frequency;
}

int tileY(Tile *tile)
{
    return tile->y;
}

bool isFree(Tile *tile)
{
    return tile->isNotWall;
}
