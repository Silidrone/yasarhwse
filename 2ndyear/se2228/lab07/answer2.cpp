#include <iostream>
#include <vector> 

int totalWaste(std::vector<int> lengths, int roll_width) {
    int waste = 0;
    while(lengths.size() > 0) {
        int c_roll_width = roll_width;
        for(auto it = lengths.begin(); it != lengths.end();) {
            if(c_roll_width - *it >= 0) {
                c_roll_width -= *it;
                it = lengths.erase(it);
            } else {
                it++;
            }      
        }

        waste += c_roll_width;
    }
    
    return waste;
}

int main() {
    std::vector<int> lengths = {1380, 1930, 1520, 1880, 1560, 1710, 1220};
    int roll_width = 3000;

    std::cout << "Total waste: " << totalWaste(lengths, roll_width) << std::endl;
}