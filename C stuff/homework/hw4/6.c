#include <stdio.h>

void main() {
    char x = 0b10000001;
    if(((x & 0b00000001) != 0) && ((x & 0b10000000) != 0)) {
    printf("%u", x);
    }
}