#include <iostream>
#include <cmath>

bool isDivisibleBy(long long number, int divider){
    return (number % divider) == 0;
}

int digits[] = {1, 4, 0, 6, 3, 5, 7, 2, 8, 9};
int dividers[] = {2, 3, 5, 7, 11, 13, 17};

bool question1Recursive(long long number, int dividerIndex) {
    if (dividerIndex > 6) return true;
    if (isDivisibleBy(number, dividers[dividerIndex])) {
        return question1Recursive((number - ((number / 100) * 100)) * 10 + digits[dividerIndex + 4], dividerIndex + 1);
    } else {
        return false;
    }
}

int main()
{
    std::cout << question1Recursive(406, 0);
}