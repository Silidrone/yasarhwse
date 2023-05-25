#include <stdio.h>

void printAddressOfVariable(int a) {
    printf("%p\n",
           (void *) &a); //I have used casting to void pointer because that's what %p advises, I hope it will not be a problem in terms of us seeing this in our class or not. If that's the case, I can re-upload the code without casting the pointer to void pointer.
}

void printValueOfAddress(int *a) {
    printf("%d\n", *a);
}

int add_v1(int a, int b) {
    return a + b;
}

void add_v2(int *s, int a, int b) {
    *s = a + b;
}

void add_v3(int *s, int *a, int *b) {
    *s = *a + *b;
}

void swap(int *a, int *b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

int findMin(int array[], int size) {
    int min = array[0];
    for (int i = 1; i < size; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }

    return min;
}

int findMax(int array[], int size) {
    int max = array[0];
    for (int i = 1; i < size; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }

    return max;
}

int getAvg(int array[], int size) {
    int sum = 0;
    for (int i = 0; i < size; i++) {
        sum += array[i];
    }

    return sum / size;
}

void labwork6(int *max, int *min, int *avg, int arr[], int size) {
    *max = findMax(arr, size);
    *min = findMin(arr, size);
    *avg = getAvg(arr, size); //the average is always rounded down (floored), I hope that's okay, if not I can upload a code using round() function to always round to the closest integer
}

int main() {
    printf("Testing printAddressOfVariable function ...\n");
    int a = 10;
    printf("The value of the variable is %d\n", a);
    printf("The address of the variable is ");
    printAddressOfVariable(a);
    printf("\n");

    printf("Testing printValueOfAddress function ...\n");
    int b = 20;
    int *c = &b;
    printf("The address of the variable is %d\n", c);
    printf("The value of the variable is ");
    printValueOfAddress(c);
    printf("\n");

    printf("addv1 test 15 + 5 = %d\n", add_v1(5, 15));
    int sum_result_v2 = 0;
    add_v2(&sum_result_v2, 6, 12);
    printf("addv2 test 6 + 12 = %d\n", sum_result_v2);
    int v3_a = 19;
    int v3_b = 22;
    int sum_result_v3 = 0;
    add_v3(&sum_result_v3, &v3_a, &v3_b);
    printf("addv3 test 19 + 22 = %d\n", sum_result_v3);

    printf("Testing swap function ...\n");
    int g = 45;
    int h = -7;
    printf("The first number is %d and the second one is %d\n", g, h);
    swap(&g, &h);
    printf("Swapping ...\n");
    printf("The first number is %d and the second one is %d\n", g, h);

    int labwork6_arr[] = {1, 7, 15, 3, 2, 9, 6};
    int labwork6_arr_size = 7;

    int max = 0;
    int min = 0;
    int avg = 0;

    labwork6(&max, &min, &avg, labwork6_arr, labwork6_arr_size);

    printf("labwork6 test arr: {1, 7, 15, 3, 2, 9, 6} max: %d min: %d avg: %d\n", max, min, avg);

    return 0;
}
