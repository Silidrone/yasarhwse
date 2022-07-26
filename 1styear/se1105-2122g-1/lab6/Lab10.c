#include <stdio.h>

void printAddressOfVariable(int a) {
	// TODO: Fill this block.
}

void printValueOfAddress(int *a) {
	// TODO: Fill this block.
}

int add_v1(int a, int b) {
	// TODO: Fill this block.
}

void add_v2(int *s, int a, int b) {
	// TODO: Fill this block.
}

void add_v3(int *s, int *a, int *b) {
	// TODO: Fill this block.
}

void swap(int *a, int *b) {
	// TODO: Fill this block.
}

void labwork6(int *max, int *min, int *avg, int arr[], int size) {
	// TODO: Fill this block then test this function in the main function.
	// This is the labwork.
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
	
	// TODO: Write test codes for all add functions
	
	printf("Testing swap function ...\n");
	int g = 45;
	int h = -7;
	printf("The first number is %d and the second one is %d\n", g, h);
	swap(&g, &h);
	printf("Swapping ...\n");
	printf("The first number is %d and the second one is %d\n", g, h);
	
	return 0;
}
