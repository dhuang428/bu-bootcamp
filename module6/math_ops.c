#include <stdio.h>

void print_math(int a, int b) { 
    int sum = a + b;
    printf("Sum: %d\n", sum);

    int product = a * b;
    printf("Product: %d\n", product);
} 

int main() { 
    int val1;
    int val2;

    printf("Enter first number: ");
    scanf("%d", &val1);

    printf("Enter second number: ");
    scanf("%d", &val2);

    print_math(val1, val2);

    return 0;
}