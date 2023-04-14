#include <iostream>

#define MAX_BINOMIAL_EXP 400

long long memo[MAX_BINOMIAL_EXP][MAX_BINOMIAL_EXP];

long long coeff(int n, int k) {
    if(n == 0) return 0;
    if(k == 0 || k == n) {
        return 1;
    }
    
    if(memo[n][k]) {
        return memo[n][k];
    }
    
    memo[n][k] = coeff(n - 1, k) + coeff(n - 1, k - 1);
    return memo[n][k];
}

int main() {
    std::cout << coeff(400, 6) << std::endl;
}