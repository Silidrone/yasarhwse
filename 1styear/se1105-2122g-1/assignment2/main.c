#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define INPUT_SIZE 10

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

void readArray(int arr[], int n) {
    int inputc;
    printf("Enter an array: \n");
    
    for(int i = 0; i < n; i ++) {
        scanf("%d", &inputc);
        arr[i] = inputc;
    }
}

void printArray(int arr[], int n) {
    for(int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    
    printf ("\n");
}

void weirdPrint(int arr[], int n) {
    for(int i = 0; i < n; i += 2) { //even
        printf("%d ", arr[i]);
    }
    printf("\n");
    for(int i = 1; i < n; i += 2) { //odd
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {    
    srand(time(NULL));
    
    int input[INPUT_SIZE];
    
    readArray(input, INPUT_SIZE);
    weirdSort(input, INPUT_SIZE);
    weirdPrint(input, INPUT_SIZE);
    
    return 0;
}