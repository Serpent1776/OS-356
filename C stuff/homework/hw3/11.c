#include <stdio.h>
#include <math.h>
int sqrt_print();

int main() {
 int x;
 sqrt_print(6);
 return 0;
}

int sqrt_print(int n) {
    for (int i = 0; i <= n; i++)
    {
        printf("%d %6.2f\n", i, sqrt((double)(i)));
    }
    
    return 0;
}