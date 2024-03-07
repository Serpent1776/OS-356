#include <stdio.h>
#include <stdlib.h>

int main() {
    char *buffer = malloc(50*sizeof(char));
   printf("%p", &buffer[0]);
    
}