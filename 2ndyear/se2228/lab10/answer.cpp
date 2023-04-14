#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>
#include <chrono>

/*
 * I = 1
 * V = 5
 * X = 10
 * L = 50
 * C = 100
 * D = 500
 * M = 1000

Note: You can assume that all the Roman numerals in the file contain no more than four consecutive identical units.
The highest letter is the 'M' equals to 1000.
*/

std::unordered_map<std::string, int> rtoi_cache;
std::unordered_map<int, std::string> itor_cache;

int numeral_val(char numeral)
{
    switch (numeral)
    {
    case 'M':
        return 1000;
    case 'D':
        return 500;
    case 'C':
        return 100;
    case 'L':
        return 50;
    case 'X':
        return 10;
    case 'V':
        return 5;
    case 'I':
        return 1;
    default:
        return -1;
    }
};

int rtoi(std::string s)
{
    auto it = rtoi_cache.find(s);
    if(it != rtoi_cache.end()) {
        return it->second;
    }

    int result = 0;
    for (int i = 0; i < s.length(); i++)
    {
        auto current = numeral_val(s.at(i));
        auto next = i != s.length() - 1 ? numeral_val(s.at(i + 1)) : -1;
        if (next == -1 || current >= next)
        {
            result += current;
        }
        else if (current < next)
        {
            result -= current;
        }
    }
    
    rtoi_cache.insert({s, result});
    return result;
}

std::string itor(int n)
{
    auto it = itor_cache.find(n);
    if(it != itor_cache.end()) {
        return it->second;
    }

    const int increments_size = 13;
    std::string increments[increments_size] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    std::string result = "";
    int i = 0;
    while (i < increments_size && n != 0)
    {
        if (n - rtoi(increments[i]) >= 0)
        {
            result += increments[i];
            n -= rtoi(increments[i]);
        }
        else
        {
            i++;
        }
    }

    itor_cache.insert({n, result});
    return result;
}

int redundancies_count(std::string roman)
{
    return roman.size() - itor(rtoi(roman)).size();
}

int redundancies_count(std::vector<std::string> romans)
{
    int result = 0;
    for (auto &roman : romans)
    {
        result += redundancies_count(roman);
    }

    return result;
}

int main()
{
    auto t1 = std::chrono::high_resolution_clock::now();

    std::vector<std::string> numbers = {"MMMCCXXXVII","MCXXI","MMMMD","MMMMCCCLXXXXVI","MMDCCCXCVI"};
    std::cout << redundancies_count(numbers) << std::endl;
}