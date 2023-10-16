#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
#define DIMENSION 3
#define TOTAL 11
#define UPPER_LIMIT 362880 // 9!
int board[DIMENSION][DIMENSION];

void printBoard() {
    cout << "Board" << endl;
    for (int row = 0; row < DIMENSION; row++) {
        for (int column = 0; column < DIMENSION; column++) {
            cout << '[' << board[row][column] << ']';
        }
        cout << endl;
    }
    cout << endl << endl;
}

bool isValidPosition(int row, int column) {
    return (row >= 0) && (row < DIMENSION) && (column >= 0) && (column < DIMENSION);
}

bool checkRatio(int newValue, int value) {
    return ((newValue * 2) != value) && ((value * 2) != newValue);
}

bool checkSum(int newValue, int value) {
    return (newValue + value != 5) && (newValue + value != 10);
}

bool checkTotal() {
    //restricted puzzle type version
//    int firstOption = board[1][1] + board[2][1];
//    int secondOption = board[1][2] + board[2][2];
//    int thirdOption = board[1][0] + board[2][0];
//
//    return (firstOption == TOTAL) || (secondOption == TOTAL) || (thirdOption == TOTAL);
    //generic rotatable version
    int middle = board[1][1];
    int downSum = middle + board[2][1];
    int upSum = middle + board[0][1];
    int leftSum = middle + board[1][0];
    int rightSum = middle + board[1][2];

    return (leftSum == TOTAL) || (rightSum == TOTAL) || (upSum == TOTAL) || (downSum == TOTAL);

}

bool checkConsecutive(int newValue, int value) {
    return (newValue - value != 1) && (value - newValue != 1);
}

bool validatePosition(int row, int column) {
    bool isValid = true;
    int direction[][2] = {
            {+1, 0},//up
            {-1, 0},//down
            {0,  -1},//left
            {0,  +1},//right
    };
    for (int i = 0; i < 4; ++i) {
        int newRow = row + direction[i][0];
        int newColumn = column + direction[i][1];
        if (isValidPosition(newRow, newColumn)) {
            int newValue = board[newRow][newColumn];
            int value = board[row][column];
            if (!checkTotal() || !checkSum(newValue, value) || !checkRatio(newValue, value) ||
                !checkConsecutive(newValue, value)) {
                isValid = false;
                break;
            }
        }
    }
    return isValid;
}

bool validateBoard() {
    bool isValid = true;
    for (int row = 0; row < DIMENSION; row++) {
        for (int column = 0; column < DIMENSION; column++) {
            if (!validatePosition(row, column)) {
                isValid = false;
                break;
            }
        }
    }
    return isValid;
}

void fillBoard(vector<int> permutation) {
    int index = 0;
    for (int row = 0; row < DIMENSION; row++) {
        for (int column = 0; column < DIMENSION; column++) {
            board[row][column] = permutation.at(index);
            index++;
        }
    }
}

int main() {
    vector<int> numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    for (int i = 0; i < UPPER_LIMIT; i++) {
        fillBoard(numbers);
        if (validateBoard()) {
            printBoard();
        }
        next_permutation(numbers.begin(), numbers.end());
    }

    return 0;
}