#include <iostream>
#include <chrono>

struct Factor{
    int base, pow;
};

long long int pow(long long int b, long long int e) {
    if(e == 0) return 1;
    return b * pow(b, e - 1);
}

int main() {
    auto start = std::chrono::steady_clock::now();
    int last_step = 140;
    int steps[last_step];
    steps[0] = 3;
    steps[1] = 5;
    steps[2] = 9;
    steps[3] = 15;
    
    int init_steps_count = 4;
    int step = init_steps_count;
    
    int three_index = 5;
    int three_offset = 3;
    int three_pow = 3;
    
    int five_index = 4;
    int five_offset = 3;
    int five_pow = 2;
    bool five_flag = true;
    
    int past_i = 0;
    
    for(int i = 0; i < step; i++) {
        std::cout << i + 1 << ": " << steps[i] << std::endl;
    }
        
    while(step <= last_step) {
        if(step == three_index) {
            std::cout << step + 1 << ": " << pow(3, three_pow) << std:: endl;
            steps[step] = pow(3, three_pow);
            if(three_index % 2 == 0) three_offset++;
            
            three_index += three_offset;
            three_pow++;
        } else if(step == five_index) {
            std::cout << step + 1 << ": " << pow(5, five_pow) << std:: endl;
            steps[step] = pow(5, five_pow);
            if(five_flag) five_offset += 2;
            else five_offset += 1;
            
            five_index += five_offset;
            five_flag = !five_flag;
            five_pow++;
        } else {
            int val = steps[past_i] * 15;
            std::cout << step + 1 << ": " << val << std::endl;
            steps[step] = val;
            past_i++;
        }
        step++;
    }
    auto end = std::chrono::steady_clock::now();
    std::cout << "Time taken: " << std::chrono::duration <double, std::milli> (end - start).count() << "ms" << std::endl;
}