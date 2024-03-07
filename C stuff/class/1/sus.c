#include <stdio.h>

void swap(int *a, int *b);

int main() {
   int x = 9;
   int y = 12;
   char z = 'd';
   swap(&x, &y);
   printf("%c", z);
   return 0;
}

void swap(int *a, int *b) {
    int hold = *a;
    *a = *b;
    *b = hold;
}