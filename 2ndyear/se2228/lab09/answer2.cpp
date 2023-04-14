#include <iostream>
#define MAX_NUM int(1E+6)

long long chainLengths[MAX_NUM];

long long collatzNumber(long long n) {
    if(n % 2 == 0) return n / 2;
    else return 3 * n + 1;
}

long long chainLength(long long n) {
    long long i = 1;
    for(; n != 1; i++) {
        if(n < MAX_NUM && chainLengths[n]) {
            return i + chainLengths[n];
        }
        n = collatzNumber(n);
    }

    return i;
}

int main() {
    long long max_chain_length = 0;
    long long n = 13;
    for(long long i = 13; i < MAX_NUM; i++) {
        long long chain_length = chainLength(i);
        chainLengths[i] = chain_length;
        if(chain_length >= max_chain_length) {
            max_chain_length = chain_length;
            n = i;
        }
    }

    std::cout << n << std::endl;
}