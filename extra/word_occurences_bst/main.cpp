#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
#include "bst.h"

using namespace std;

vector<WordOccurrence> find_word_occurrences(string str){
    const vector<char> ignored = {'.', '?', '!', ',', ';', ':', '-', '[', ']', '{', '}', '(', ')', '\'', '\"', '*'};
    BST bst;    
    
    string word = "";    
    for(auto c : str){
        if(isalpha(c)){
            word += c;    
        } 
        else if(std::find(ignored.begin(), ignored.end(), c) != ignored.end());
        else if(word != ""){
            bst.insert(word);
            word = "";
        }
    }
    
    return bst.get_word_occurrences();     
}

std::string slurp(std::ifstream& in) {
    std::stringstream sstr;
    sstr << in.rdbuf();
    return sstr.str();
}

int main(){    
    ifstream input_file("input.txt");
    string input_str;
    
    if(input_file.is_open()){
        input_str = slurp(input_file);
    }    
        
    vector<WordOccurrence> word_occurrences = find_word_occurrences(input_str);
    
    ofstream output_file("output.txt");
    
    if(output_file.is_open()){
        for(auto &word_occurrence : word_occurrences){
            output_file << "\t" << word_occurrence.count << "\t" << word_occurrence.str << endl;    
        }    
        output_file << "------------" << endl;
        output_file << "\t" << word_occurrences.size() << "\t" << "Total number of different words" << endl;    
    }
}

