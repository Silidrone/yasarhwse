#include <iostream>
#include <vector>
#include <cmath>

bool isPrime(int number) {
    bool prime = true;
    if ((number != 2) && (number % 2 == 0)) {
        return false;
    }
    for (int i = 3; i < sqrt(number) + 1; i = i + 2) {
        if (number % i == 0) {
            prime = false;
            break;
        }
    }
    return prime;
}

int pow(int base, int exp) {
    if(exp == 0) return 1;
    return base * pow(base, exp - 1);
}

int factorial(int n) {
    if(n == 0) return 1;
    return n * factorial(n - 1);
}

std::vector<int> get_digits(int n) {
    std::vector<int> digits;
    while(n != 0) {
        digits.insert(digits.begin(), n % 10);
        n /= 10;
    }
    
    return digits;    
}

int sum(std::vector<int> v) {
    int result = 0;
    for(auto &e : v) {
        result += e;
    }
    
    return result;
}

std::vector<int> q1() {
    std::vector<int> result;
    for(int i = 10; i < 9999; i++) {
        auto digits = get_digits(i);
        int sum = 0;
        for(auto &digit : digits) {
            sum += pow(digit, 4);
        }
        	
        if(i == sum) {
            result.push_back(i);
        }
    }
    
    return result;
}


std::vector<int> q2() {
    std::vector<int> result;
    for(int i = 10; i < 99999; i++) {
        auto digits = get_digits(i);
        int sum = 0;
        for(auto &digit : digits) {
            sum += factorial(digit);
        }
        	
        if(i == sum) {
            result.push_back(i);
        }
    }
    
    return result;
}

std::vector<int> get_primes(int limit) {
    std::vector<int> result;
    
    int sum = 0, i = 2;
    while(true) {
        if(isPrime(i)) {
            sum += i;
            if(sum >= limit) return result;

            result.push_back(i);
        }
        
        i++;
    }
    
    return result;
}

int q3(int limit) {
    int result = 0; 
    auto primes = get_primes(limit);
    
    while(primes.size() > 0) {
        int sum = 0;
        for(auto &prime : primes) {
            sum += prime;
        }
        if(isPrime(sum) && sum > result) {
            result = sum;
        }
        primes.erase(primes.begin());
    }
    
    return result;
}

int main() {
    std::cout << "q1: " << sum(q1()) << std::endl;
    std::cout << "q2: " << sum(q2()) << std::endl;
    std::cout << "q3: " << q3(1000000) << std::endl;
}