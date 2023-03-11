#include <iostream>
#include <vector>
#include <math.h>

bool isPrime(int number) {
    for (int i = 2; i < number; i++) {
        if (number % i == 0 && i != number) return false;
    }
    
    return true;
}

bool isGoldBach(int n) {
    for(int i = 0; i < n; i++) {
        if(isPrime(i)) {
            for(int k = 1; k < sqrt(n/2); k++) {
                if(i + 2*k*k == n) return true;
            }
        }
    }
    
    return false;
}

int question2() {
    int i = 1;
    while(true) {
        int n = 2 * i + 1;
        if(!isPrime(n) && !isGoldBach(n)) return n;
        i++;
    }
    
    return 0;
}

int main() {
    std::cout << question2() << std::endl;
}
