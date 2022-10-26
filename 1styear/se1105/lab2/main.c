#include <stdio.h>
#include <math.h>

int is_armstrong_number(int n) {
    int digit_count;
    if(n == 0) digit_count = 1;
    else digit_count = log10(n) + 1;
    int sum = 0;
    for(int iter_num = n; iter_num; iter_num /= 10) {    
        sum += pow((iter_num % 10), digit_count);                        
    }    
    
    return sum == n;
}

void main() {
    int n;
    printf("Please enter an integer: ");
    scanf("%d", &n);
    if(is_armstrong_number(n)) {
        printf("%d is an Armstrong number\n", n);
    } else {
        printf("%d is not an Armstrong number\n", n);
    }    
}