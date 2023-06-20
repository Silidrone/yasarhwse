#include <iostream>
#include <stdlib.h>
#include <time.h>

#define DIMENSION 3
#define PLAYER1_MARKER 'X'
#define COMPUTER_MARKER 'O'
#define EMPTY_SPACE ' '
using namespace std;

char board[DIMENSION][DIMENSION];

// Don't change/edit this function
void computerMove() {
    srand(time(NULL));
    int row, columnIndex;
    do {
        row = rand() % DIMENSION;
        columnIndex = rand() % DIMENSION;
    } while (!isAvailable(row, columnIndex));
    placeMarker(row, columnIndex, false);
}

// Fill the game board with empty space
void clearBoard() {
    for(int i = 0; i < DIMENSION; i++) {
        for(int j = 0; j < DIMENSION; j++) {
            board[i][j] = EMPTY_SPACE;
        }
    }
}

// Print the board in the given format in std.txt file
void printBoard() {
    for(int i = 0; i < DIMENSION; i++) {
        for(int j = 0; j < DIMENSION; j++) {
            std::cout << "[" << board[i][j] << "]";
        }
        std::cout << std::endl;
    }
}

// Check that if the given coordinate is in the bound of board and suitable to place a marker
bool isAvailable(int row, int col) {
    return row >= 0 && row < DIMENSION && col >= 0 && col < DIMENSION && board[i][j] == EMPTY_SPACE;
}

// Place Marker. Place the corresponding marker to the board
void placeMarker(int row, int col, bool firstPlayerTurn) {
    board[row][col] = firstPlayerTurn ? PLAYER1_MARKER : COMPUTER_MARKER;
}

// Place your marker to any available corner.
void playerFirstMove() {

}

// Place a second marker in an adjacent corner
void playerSecondMove() {

}

// Checks that if the game over in any state
bool gameOver() {

}

// Fill the function to apply correct strategy
void playerMove() {

}