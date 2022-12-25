#include <iostream>

void swap(int *a, int *b) {
    int c = *a;
    *a = *b;
    *b = c;
}

//in-place algorithm O(N^2)
//idea: on each iteration the smallest number of the rest of the data is selected and swapped with the current one
void selection_sort(int *input, int size) {
    for(int i = 0; i < size; i++) {
        int min = input[i];
        int min_index = -1;
        //find min
        for(int j = i + 1; j < size; j++) {
            if(input[j] < min) {
                min = input[j];
                min_index = j;
            }
        }
        if(min_index != -1)
            swap(input + i, input + min_index);
    }
}

//in-place algorithm O(N^2)
//idea: the largest number "bubbles" up to the top of the array on each iteration
void bubble_sort(int *input, int size) {
    bool sorted = false;
    for(int i = 0; i < size && !sorted; i++) {
        sorted = true;
        for(int j = 0; j < size - i - 1; j++) {
            if(*(input + j + 1) < *(input + j)) {
                swap(input + j + 1, input + j);
                sorted = false;
            }
        }
    }
}

//in-place algorithm O(N^2)
//idea: people say it is analogous to sorting cards in your left hand but I feel the best description is that it is the opposite of bubble sort (bubbles down the smallest element on each iteration)
void insertion_sort(int *input, int size) {
    for(int i = 1; i < size; i++) {
        int j = i, k = i - 1;
        while(k >= 0 && input[k] > input[j]) {
            swap(input + j, input + k);
            j--;
            k--;
        }
    }
}

int linear_search(int *data, int size, int x) {
    for(int i = 0; i < size; i++) {
        if(data[i] == x) return i;
    }
    return -1;
}

//int binary_search_helper(int *data, int *it, int size, int x) {
//    int median_index = (size + 1) / 2 - 1;
//    if(x == it[median_index]) return (it - data) + median_index;
//    if(size == 1) return -1;
//    if(x < it[median_index]) return binary_search_helper(data, it, median_index, x);
//    return binary_search_helper(data, it + median_index + 1, size - (median_index + 1) - (int)(size % 2 == 0), x); 
//}

//int binary_search(int *data, int size, int x) {
//    return binary_search_helper(data, data, size, x);
//}

int binary_search(int *arr, int x, int low, int high) {
    if(low > high) return -1;
    else {
        int med = (low + high) / 2;
        if(x == arr[med]) return med;
        else if (x < arr[med]) 
            return binary_search(arr, x, low, med - 1);
        else
            return binary_search(arr, x, med + 1, high); 
    }
}

void print(int *input, int size) {
    bool first = true;
    for(int i = 0; i < size; i++) {
        if(!first) std::cout << ", ";
        std::cout << input[i];
        first = false;
    }
    std::cout << std::endl;
}

int main() {
    int data[] = {7, 15, 27, 322, 1888, 9000, 21300, 21311, 21321, 32211, 32212};
    int size = sizeof(data) / sizeof(int);
    print(data, size);
    //selection_sort(data, size);
    //bubble_sort(data, size);
    //insertion_sort(data, size);
    std::cout << binary_search(data, 32211, 0, size - 1) << std::endl;
    //print(data, size);
}