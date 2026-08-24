#include <stdio.h>

void swap(int *a, int *b) { 
    int temp = *a;
    *a = *b;
    *b = temp;
}

void broken_swap(int a, int b) {
    /* This only changes copies. Does not change what is stored at the memory addresses */
    int temp = a;
    a = b;
    b = temp;
}

int main() { 
    int x = 10;
    int y = 20;

    /* Test the swap that works */
    printf("Before swap: ");
    printf("%d %d\n", x, y);    

    swap(&x, &y);

    printf("After swap: ");
    printf("%d %d\n", x, y);    

    /* Reset x and y to what they were originally */
    x = 10;
    y = 20;

    printf("After resetting: ");
    printf("%d %d\n", x, y);    

    /* Test the swap that is broken */
    printf("Before broken swap: ");
    printf("%d %d\n", x, y);    

    broken_swap(x, y);

    printf("After broken swap: ");
    printf("%d %d\n", x, y);    

    return 0;
}