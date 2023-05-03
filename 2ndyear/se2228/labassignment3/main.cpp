#include <iostream>
#include <vector>
#include <unordered_map>
#include <math.h>
#include <algorithm>

std::unordered_map<char, std::string> t9_map = {
    {'2', "abc"}, {'3', "def"}, {'4', "ghi"}, {'5', "jkl"}, {'6', "mno"}, {'7', "pqrs"}, {'8', "tuv"}, {'9', "wxyz"}
};

std::unordered_map<std::string, std::vector<std::string>> memo;

void generateWords(std::string& input, int pos, std::string& output, std::vector<std::string>& result) {
    if (pos == input.length()) {
        result.push_back(output);
        return;
    }
    char digit = input[pos];
    std::string letters = t9_map[digit];
    for (char c : letters) {
        output.push_back(c);
        generateWords(input, pos + 1, output, result);
        output.pop_back();
    }
    generateWords(input, pos + 1, output, result);
}

std::vector<std::string> getT9Words(std::string input) {
    if (memo.find(input) != memo.end()) {
        return memo[input];
    }
    std::vector<std::string> result;
    std::string output = "";
    generateWords(input, 0, output, result);
    memo[input] = result;
    return result;
}

std::vector<std::string> possibleWords(std::string number, std::vector<std::string> words)
{
    std::vector<std::string> allt9words;
    std::vector<std::string> result;
    for (int i = 0; i < number.length(); i++)
    {
        for (int j = i + 1; j <= number.length(); j++)
        {
            std::string substr = number.substr(i, j - i);
            std::vector<std::string> t9words = getT9Words(substr);
            allt9words.insert(allt9words.end(), t9words.begin(), t9words.end());
        }
    }

    for (auto &word : words)
    {
        if (std::find(allt9words.begin(), allt9words.end(), word) != allt9words.end())
        {
            result.push_back(word);
        }
    }

    return result;
}

int main()
{
    std::string input = "3662277";
    std::vector<std::string> words = {"foo", "bar", "baz", "foobar", "emo", "cap", "car", "cat"};
    std::vector<std::string> result = possibleWords(input, words);
    for (std::string word : result)
    {
        std::cout << word << std::endl;
    }
}