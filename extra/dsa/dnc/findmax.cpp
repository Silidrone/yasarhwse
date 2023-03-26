#include <iostream>

int findmax(int *arr, int s, int e) {
    if(e - s == 1) {
        return std::max(arr[s], arr[e]);
    }
    
    int mid = (s + e) / 2;
    return std::max(findmax(arr, s, mid), findmax(arr, ((e - (mid + 1)) >= 1) ? mid + 1 : mid, e));
}

int main() {
    int arr[] = { 8, 3, 2, 9, 7, 1, 5, 4, 16 };
    std::cout << findmax(arr, 0, sizeof(arr) / sizeof(arr[0]) - 1) << std::endl;
}