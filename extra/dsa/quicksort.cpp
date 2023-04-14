#include <iostream>

void swap(int *x, int *y) {
    int tmp = *x;
    *x = *y;
    *y = tmp;
}

int partition(int *a, int l, int r) {
    int i = l;
    for(int j = l; j < r; j++) {
        if(a[j] <= a[r]) {
            swap(&a[i], &a[j]);
            i++;
        }
    }
    
    swap(&a[i], &a[r]);
    return i;
}

void quicksort(int *a, int l, int r) {
    if (l < r) {
        int p = partition(a, l, r);
        quicksort(a, l, p - 1);
        quicksort(a, p + 1, r);
    }
}

void print(int *a, int n) {
    for(int i = 0; i < n; i++) {
        std::cout << a[i] << " ";
    }
    
    std::cout << std::endl;
}

int main() {
    int a[] = {8, 3, 2, 6, 17, 15, 4, 2, 1, 8, 9};
    int n = sizeof(a) / sizeof(a[0]);
    
    print(a, n);
    quicksort(a, 0, n - 1);
    print(a, n);
}