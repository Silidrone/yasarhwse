#include <iostream>
#include <string>
#include <unordered_map>
#include <iterator>
#include <queue>
#include <exception>

bool equalFrequency(std::string word)
{
    if (word.size() < 2 || word.size() > 100)
        return false;
    std::unordered_map<char, int> freqs;
    for (auto &c : word)
    {
        freqs[c]++;
    }

    if (freqs.size() == 1)
    {
        return freqs.begin()->second == 1;
    }
    else if (freqs.size() == 2)
    {
        return abs(freqs.begin()->second - std::next(freqs.begin())->second) == 1;
    } else {
        int imposter = -1;
        for (int i = 0; i < freqs.size() - 2; i++) {
            int x = freqs[i];
            int y = freqs[i + 1];
            int z = freqs[i + 2];
            if(x != y) {
                if(y == z) {
                    imposter = x;
                } else if (x == z) {
                    imposter = y;
                }
            } else if(x != z) {
                if(z == y) {
                    imposter = x;
                } else {
                    imposter = z;
                }
            } else if(y != z) {
                
            }
        }
    }

    for (auto &f : freqs)
    {
        std::cout << "(" << f.first << ", " << f.second << ")" << std::endl;
    }

    return true;
    // return (freqs_r.size() == 1 && (freqs_r.begin())->first == 1) || (freqs_r.size() == 2 && abs((freqs_r.begin())->first - (std::next(freqs_r.begin()))->first) == 1);
}

int main()
{
    equalFrequency("dddeekkggg");
    // std::cout << equalFrequency("ddaccb") << std::endl;
}