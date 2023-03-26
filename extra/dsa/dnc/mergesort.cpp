#include <iostream>

int *merge(int *arr1, int *arr2, int n) {
    int *merged = new int[2 * n];
    
    int i = 0;
    int j = 0;
    int k = 0;
    while(i < n && j < n) {
        if(arr1[i] <= arr2[j]) {
            merged[k++] = arr1[i];
            i++;
        } else {
            merged[k++] = arr2[j];
            j++;
        }
    }
    
    for(int _i = i; _i < n; _i++) {
        merged[k++] = arr1[_i];
    }
    
    for(int _j = j; _j < n; _j++) {
        merged[k++] = arr2[_j];
    }
    
    return merged;
}

int *mergesort(int *arr, int i, int j) {
    if(j == i) {
        int *x = new int[1];
        x[0] = arr[i];
        return x;
    }
    
    int mid = (i + j) / 2;
    int *left = mergesort(arr, i, mid);
    int *right = mergesort(arr, mid + 1, j);
    int *merged = merge(left, right, mid - i + 1);
    delete left;
    delete right;
    return merged;
}

int main() {
    int arr[] = { 8, 3, 2, 9, 7, 1, 5, 4 };
    int size = sizeof(arr) / sizeof(arr[0]);
    int *sorted = mergesort(arr, 0, size - 1);
    
    for(int i = 0; i < size; i++) {
        std::cout << sorted[i] << " ";
    }
    std::cout << std::endl;
    delete sorted;
}