#include <iostream>

//end not included
int findInfiltratorIndex(int arr[], int start, int end) {
    if(start == end) {
        return start;
    }
    
    int middle = (start + end) / 2;
    int left = findInfiltratorIndex(arr, start, middle);
    int right = findInfiltratorIndex(arr, middle + 1, end);

    if(arr[left] <= arr[right]) {
        return middle;
    } else if(arr[middle] >= arr[right]) {
        return left;
    }
    
    return right;
}


int main() {
    int arr[] = {1, 1, 17, 1};
    int sz = sizeof(arr) / sizeof(arr[0]);
    std::cout << sz << std::endl;

    int inf = findInfiltratorIndex(arr, 0, sz - 1);    
    std::cout << "infiltrator: " << arr[inf] << std::endl;

}