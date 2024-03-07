#include <stdio.h>

void main() {
    double *mal = malloc(50000*sizeof(double));
    printf("%p", &mal[0]);
    for(int i = 0; i < 49999; i++) {
        mal[i] = 9.99;
    }
    free(mal);
}