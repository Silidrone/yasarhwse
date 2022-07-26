#include <stdio.h>
#include <stdlib.h>

#define ARRAY_SIZE 4
typedef int matrix[ARRAY_SIZE][ARRAY_SIZE];

void fillMatrix(matrix m) {
	for(int i = 0; i < ARRAY_SIZE; i++) {
		for(int j = 0; j < ARRAY_SIZE; j++) {
			scanf("%d", &m[i][j]);		
		}
	}
}

void printMatrix(matrix m) {
	for(int i = 0; i < ARRAY_SIZE; i++) {
		for(int j = 0; j < ARRAY_SIZE; j++) {
			printf("%d ", m[i][j]);
		}
		printf("\n");
	}
}

/*
 * operation = 'a'  ->  matrix addition
 * operation = 's'  ->  matrix substraction
 * operation = 'h'  *>  hadamard product
 */

void matrixAdd(matrix s, matrix a, matrix b) {
	for(int i = 0; i < ARRAY_SIZE; i++) {
		for(int j = 0; j < ARRAY_SIZE; j++) {
			s[i][j] = a[i][j] + b[i][j];
		}
	}
}

void matrixSubstract(matrix s, matrix a, matrix b) {
	for(int i = 0; i < ARRAY_SIZE; i++) {
		for(int j = 0; j < ARRAY_SIZE; j++) {
			s[i][j] = a[i][j] - b[i][j];
		}
	}
}

void matrixHadamardProduct(matrix s, matrix a, matrix b) {
	for(int i = 0; i < ARRAY_SIZE; i++) {
		for(int j = 0; j < ARRAY_SIZE; j++) {
			s[i][j] = a[i][j] * b[i][j];
		}
	}
} 
 
void matrixOperation(matrix s, matrix a, matrix b, char operation) {
	if(operation == 'a') {
		matrixAdd(s, a, b);
	} else if (operation == 's') {
		matrixSubstract(s, a, b);
	} else if (operation == 'h') {
		matrixHadamardProduct(s, a, b);
	}
}

void matrixMultiplication(matrix s, matrix a, matrix b) {
	for(int i = 0; i < ARRAY_SIZE; i++) { //rows in matrix a
		for(int j = 0; j < ARRAY_SIZE; j++) { //items in the k column
			s[i][j] = 0;
			for(int k = 0; k < ARRAY_SIZE; k++) { //elements in the i row, columns in matrix b
				s[i][j] += a[i][k] * b[k][j];		
			}
		}
	}	
}

int main() {
	matrix a, b, add, sub, hmd, mul;
	printf("Fill the first matrix:\n");
	fillMatrix(a);
	printf("\nFill the second matrix:\n");
	fillMatrix(b);
	
	printf("\nFirst matrix:\n");
	printMatrix(a);
	
	printf("\nSecond matrix:\n");
	printMatrix(b);
	
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
