#include <iostream>

#define MAX_NUM int(1E+7)
#define CHAIN1 1
#define CHAIN89 -1
int chain[MAX_NUM];

int digitsSquareSum(int n)
{
    int sum = 0;
    while (n > 0)
    {
        sum += (n % 10) * (n % 10);
        n /= 10;
    }

    return sum;
}

bool isChain89(int n)
{
    while (true)
    {
        if(n == 89 || chain[n] == CHAIN89) {
            return true;
        }
        else if(n == 1 || chain[n] == CHAIN1) {
            return false;
        }
        n = digitsSquareSum(n);
    }

    return false; //unreachable code
}

int main()
{
    int count = 0;

    for (int i = 1; i < MAX_NUM; i++)
    {
        if(isChain89(i)) {
            count++;
            chain[i] = CHAIN89;
        } else {
            chain[i] = CHAIN1;
        }
    }

    std::cout << count << std::endl;
}
