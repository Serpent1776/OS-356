#include <stdio.h>
#include <stdlib.h>
#include <string.h>



struct Person {
    char name[100];
    int age;
};

void main() {
    struct Person seth;
    struct Person *ptr = &seth;
    ptr->age = 22;
    strcpy(seth.name, "Seth");
    printf("%s %d", seth.name, seth.age);
}
