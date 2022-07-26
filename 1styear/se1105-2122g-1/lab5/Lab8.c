#include <stdio.h>
#include <stdlib.h>

#define ARRAY_SIZE 4
typedef int matrix[ARRAY_SIZE][ARRAY_SIZE];

void fillMatrix(matrix m) {
	// TODO: Fill
}

void printMatrix(matrix m) {
	// TODO: Fill
}

/*
 * operation = 'a'  ->  matrix addition
 * operation = 's'  ->  matrix substraction
 * operation = 'h'  *>  hadamard product
 */
void matrixOperation(matrix s, matrix a, matrix b, char operation) {
	// TODO: Fill
}

void matrixMultiplication(matrix s, matrix a, matrix b) {
	// TODO: Fill
}

int main() {
	matrix a, b, add, sub, hmd, mul;
	printf("Fill the first matrix:\n");
	fillMatrix(a);
	printf("\nFill the second matrix:\n");
	fillMatrix(b);
	matrixOperation(add, a, b, 'a');
	matrixOperation(sub, a, b, 's');
	matrixOperation(hmd, a, b, 'h');
	matrixMultiplication(mul, a, b);

	printf("\nMatrix Addition:\n");
	printMatrix(add);
	printf("\nMatrix Subtraction:\n");
	printMatrix(sub);
	printf("\nHamadard product:\n");
	printMatrix(hmd);
	printf("\nMatrix multiplication:\n");
	printMatrix(mul);
	
	return 0;
}
