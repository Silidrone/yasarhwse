#include <iostream>

// The theoretical upper limit is 2916 since (9^3)*4 = 2916, so anything larger than 2916 breaks the rules. Armstrong number in this question is simplified to sum of cubes of digits, not to the sum of its own digits each raised to the power of the number of digits (narcissistic number)

int pow(int base, int exp) {
    if(exp == 0) return 1;
    return base * pow(base, exp - 1);
}

int answer() {
    int result = 0;
    for(int i = 10; i <= 2916; i++) {
        std::cout << "n: " << i << std::endl;
        int n = i;
        int sum = 0;
        while(n != 0) {
            sum += pow(n % 10, 3);
            n /= 10;
        }
    
        if(sum == i) {
            result += sum;
        }
    }
    
    return result;
}

int main() {
    std::cout << answer() << std::endl;
}