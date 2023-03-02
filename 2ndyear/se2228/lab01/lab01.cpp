// A program to find rth permutation of n symbols in O(n) time complexity

#include <iostream>
#include <vector>

int factorial(int n) {
    if(n == 0 || n == 1) return 1;
    return n * factorial(n - 1);
}

void print_vector(std::vector<char> &v) {
    std::cout << '[';
    for(int i = 0; i < v.size(); i++) {
        std::cout << v.at(i) << ' ';
    }
    std::cout << ']' << std::endl;
}

int main()
{
    std::vector<char> symbols = {'A', 'B', 'C', 'D'};
    int n = symbols.size(); // 4 symbols
    int r = 11; // this is the 9th permutation
    int offset = 0;
    std::vector<char> result;
    for(int i = 0; i < n; i++) {
        int rep = factorial(n - 1 - i);
        offset = r / rep;
        result.push_back(symbols.at(offset));
        symbols.erase(symbols.begin() + offset);
        r = r % rep;
        std::cout << "rep: " << rep << std::endl;
        std::cout << "offset: " << offset << std::endl;
        std::cout << "r: " << r << std::endl;
        std::cout << "symbols: ";
        print_vector(symbols);
    }
    
    std::cout << "result: ";
    print_vector(result);
    return 0;
}