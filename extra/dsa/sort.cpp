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

void merge(int *arr, int start, int mid, int end) {
    int l_len = mid - start + 1;
    int r_len = end - mid;
    
    int l[l_len], r[r_len];
    
    for(int i = 0; i < l_len; i++)
        l[i] = arr[start + i];
    for(int i = 0; i < r_len; i++)
        r[i] = arr[mid + 1 + i];
    
    int l_index = 0, r_index = 0;
    int i = start;
    while(l_index < l_len && r_index < r_len) {
        if(l[l_index] <= r[r_index]) {
            arr[i++] = l[l_index++];
        } else {
            arr[i++] = r[r_index++];
        }
    }
    
    while(l_index < l_len) {
        arr[i++] = l[l_index++];
    }
    
    while(r_index < r_len) {
        arr[i++] = r[r_index++];
    }
}

void merge_sort(int *arr, int low, int high) {
    if(low > high) return;
    int med = (low + high) / 2;
    merge_sort(arr, low, med - 1);
    merge_sort(arr, med + 1, high);
    merge(arr, low, med, high); 
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
    int data[] = {3, 2, 5, 1, 4, 15000};
    int size = sizeof(data) / sizeof(int);
    print(data, size);
    //selection_sort(data, size);
    //bubble_sort(data, size);
    //insertion_sort(data, size);
    //std::cout << binary_search(data, 32211, 0, size - 1) << std::endl;
    merge_sort(data, 0, size - 1); 
    print(data, size);
}