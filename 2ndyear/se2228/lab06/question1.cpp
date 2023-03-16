#include <iostream>

int peak(int *arr, int from, int to) {
    if(from == to) {
        return arr[from];
    }

    int middle = (from + to) / 2;
    int left_peak = peak(arr, from, middle);
    int right_peak = peak(arr, middle + 1, to);
    int current_peak = arr[middle];
    if(current_peak >= left_peak && current_peak >= right_peak) {
        return current_peak;
    } else if(left_peak >= right_peak) {
        return left_peak;
    }
    
    return right_peak;
}

int main() {
    srand(time(nullptr));

    int sz = 100001;
    int arr[sz];
    for(int i = 0; i < sz; i++) {
      arr[i] = rand() % 100;
    }
    
    
    std::cout << "arr: { ";
    for(int i = 0; i < sz; i++) {
        std:: cout << arr[i] << " ";
    }
    std::cout << "}" << std::endl;
    
    peak(arr, 0, sz - 1);
    std::cout << "peak of arr is: " << peak(arr, 0, sz - 1) << std::endl;
}