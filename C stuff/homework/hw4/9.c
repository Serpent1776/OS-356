#include <stdio.h>
#include <stdlib.h>
struct Pair
{
    int x;
    int y;
};


void main() {
    struct Pair pair1;
    pair1.x = 5;
    struct Pair *ptr =  &pair1;
    ptr->y = 9;
    printf("%d %d", pair1.x, pair1.y);
}