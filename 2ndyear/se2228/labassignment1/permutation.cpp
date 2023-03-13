// I created another program just to figure out the permutate function to be able to use it in my answer

#include <iostream>
#include <vector>

void permutate(std::vector<int> v, std::vector<int> parents = {}) {
    if(v.size() == 0) {
        for(auto &parent : parents) {
            std::cout << parent << " ";
        }
        std::cout << std::endl;
    }
    for(int i = 0; i < v.size(); i++) {
        std::vector<int> v1(v);
        v1.erase(v1.begin() + i);
        std::vector<int> parents_c(parents);
        parents_c.push_back(v[i]);
        permutate(v1, parents_c);
    }    
}

int main() {
    std::vector<int> v({1, 2, 3, 4, 5});    
    permutate(v);
}
