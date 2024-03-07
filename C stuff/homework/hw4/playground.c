#include <stdio.h>

void func(int *a, int b);

void main() {
    int *buf = malloc(50000*sizeof(int));
    printf("%p\n", buf);
    printf("%p\n", &buf[0]); 
    char x[2] = "yo";
    char y[3] = {'y', 'e', 'o'};
    if(*x == *y) {
        printf("true\n");
    } else {
        printf("false\n");   
    } //code I used as work for 4.
    //rest is work I used for 5.
    int a = 2, b = 5;
    func(&a, b);
    printf("%d %d", a, b);
}

void func(int *a, int b) {
    *a = 99;
    b = 55;
}