#include <iostream>

//end not included
int findInfiltratorIndex(int arr[], int start, int end) {
    if(end == start) {
        return -1;
    } else if(end - start == 1) {
        if(arr[end] > arr[start]) {
            return end;
        } else if(arr[start] > arr[end]) {
            return start;
        }
        
        return -1;
    }
    
    int middle = (start + end) / 2;
    int left = findInfiltratorIndex(arr, start, middle);
    int right = findInfiltratorIndex(arr, (end - (middle + 1) >= 1) ? middle + 1 : middle, end);
    
    if(left != -1) {
        return left;
    } else {
        return right;
    }
}


int main() {
    int arr[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 11, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    int sz = sizeof(arr) / sizeof(arr[0]);

    int index = findInfiltratorIndex(arr, 0, sz - 1);    
    std::cout << "infiltrator: " << ((index != -1) ? arr[index] : index) << std::endl;

}