#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

#define INPUT_SIZE 10
#define TEST_SAMPLE_SIZE 1000000

void element_swap(int arr[], int xi, int yi) {
    int temp;
    temp = arr[xi];
    arr[xi] = arr[yi];
    arr[yi] = temp;
}

void sortOddOrEven(int arr[], int n, bool even, bool ascending)
{
    int i, swap_i;
    
    if(even) {
      i = 0;
    } else {
      i = 1;
    }
     
    for (; i < n - 1; i += 2) {
        swap_i = i;
        for (int j = i + 2; j < n; j += 2) {
          if ((ascending && arr[j] < arr[swap_i]) || (!ascending && arr[j] > arr[swap_i])) {
            swap_i = j;
          }
        }
        
        element_swap(arr, swap_i, i);
    }
}

void weirdSort(int arr[], int n) {
  sortOddOrEven(arr, n, true, true);
  sortOddOrEven(arr, n, false, false);
}

void fillWithRandomNums(int arr[], int n) {
    for(int i = 0; i < n; i++) {
        arr[i] = rand() % 1000;
    }
}

bool isSorted(int arr[], int n) {
    for(int i = 0, j = 1; (i < n - 1) || (j < n - 1); i += 2, j += 2) {
       if((j < n - 1 && j + 2 < n - 1 && arr[j] < arr[j + 2]) || (i < n - 1 && i + 2 < n - 1 && arr[i] > arr[i + 2])) return false;
    }
    
    return true;
}

int main() {    
    srand(time(NULL));
    
    int input[INPUT_SIZE];
    int times = 0;
    for(int i = 0; i < TEST_SAMPLE_SIZE; i++) {
        fillWithRandomNums(input, INPUT_SIZE);
        weirdSort(input, INPUT_SIZE);
        times += (1 * (int)isSorted(input, INPUT_SIZE));
    }
    
    if(times != 0) {
        printf("Correction percentage: %.1f%%\n", times * (100 / (float)TEST_SAMPLE_SIZE));
    } else {
        printf("Correction percentage: 0%%\n");
    } 
    
    return 0;
}