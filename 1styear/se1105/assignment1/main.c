#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int digital_summation(int n) {
    int sum = 0;
    for(int iter_num = n; iter_num; iter_num /= 10) {
        sum += iter_num % 10;        
    }
        
    return sum;
}

int is_perfect_square(int n) {
    if(n <= 0) return 0;
    double sroot = sqrt(n);
    n = (int)sroot;
    return sroot == n;
}

void main() {
    int n;
    printf("n: ");
    scanf("%d:", &n);
    
    for(int i = 1; i < n; i++) {
        int dsum = digital_summation(i); 
        if(is_perfect_square(dsum)) {
            printf("%d ", i);
            
            for(int iter_num = i; iter_num; iter_num /= 10) {
                printf("%d", iter_num % 10);
                if(iter_num / 10) {
                    printf(":");
                }
            }
            
            printf(" %d\n", dsum);                        
        }        
    } 
}