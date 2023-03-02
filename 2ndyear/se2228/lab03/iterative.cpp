#include <iostream>
#include <cmath>

bool isDivisibleBy(long long number, int divider){
    return (number % divider) == 0;
}

bool question1Iterative(long long number){
    number %= static_cast<int>(pow(10, static_cast<size_t>(log10(number)))); //remove first digit
    int dividers[] = {17, 13, 11, 7, 5, 3, 2};
    for(int i = 0; number / 100; i++) {
        int index = i % 2; 
        if(!isDivisibleBy((number % 1000), dividers[i])) {
            return false;
        }
        number /= 10;
    }
    
    return true;
}

int main()
{
    std::cout << question1Iterative(1406357289);
}