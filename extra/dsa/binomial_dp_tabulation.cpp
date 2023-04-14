#include <iostream>

#define MAX_BINOMIAL_EXP 400

long long table[MAX_BINOMIAL_EXP][MAX_BINOMIAL_EXP];

long long coeff(int n, int k) {
    for(int i = 2; i <= n; i++) { 
        for(int j = 1; j <= k; j++) {
            table[i][j] = table[i - 1][j] + table[i - 1][j - 1];
        }
    }
    
    return table[n][k];
}

int main() {
    table[1][1] = 1;
    for(int i = 0; i < MAX_BINOMIAL_EXP; i++) {
        table[i][0] = 1;
    }
    
    std::cout << coeff(400, 6) << std::endl;
}