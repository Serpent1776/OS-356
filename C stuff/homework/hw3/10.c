#include <stdio.h>

int main() {
    char x = 'z';
    printf("%c", x);
    char *ptr = &x;
    printf("\n%c %p", *ptr, ptr);
    *ptr = '@';
    printf("\n%c", x);
    return 0;
}

