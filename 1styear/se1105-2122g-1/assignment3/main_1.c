#include <stdio.h>

#define SCREEN_COLUMNS_COUNT 20 //x
#define SCREEN_ROWS_COUNT 20 //y

typedef int Screen[SCREEN_ROWS_COUNT][SCREEN_COLUMNS_COUNT];

void drawRectangle(Screen screen, int row, int column, int width, int height) {
    for (int y = row; y <= row + height - 1; y++) {
        screen[y][column] = 1;
        screen[y][column + width - 1] = 1;
    }

    for (int x = column; x <= column + width - 1; x++) {
        screen[row][x] = 1;
        screen[row + height - 1][x] = 1;
    }
}

void fillRectangle(Screen screen, int row, int column, int width, int height) {
    for (int y = row; y <= row + height - 1; y++) {
        for (int x = column; x <= column + width - 1; x++) {
            screen[y][x] = 1;
        }
    }
}

void drawHLine(Screen screen, int row, int column, int length) {
    for (int x = column; x <= column + length - 1; x++) {
        screen[row][x] = 1;
    }
}

void drawVLine(Screen screen, int row, int column, int length) {
    for (int y = row; y <= row + length - 1; y++) {
        screen[y][column] = 1;
    }
}

void clearScreen(Screen screen) {
    for (int y = 0; y < SCREEN_ROWS_COUNT; y++) {
        for (int x = 0; x < SCREEN_COLUMNS_COUNT; x++) {
            screen[y][x] = 0;
        }
    }
}

void printScreen(Screen screen) {
    for (int i = 0; i < SCREEN_COLUMNS_COUNT + 2; i++) {
        printf("%c ", 196);
    }
    printf("\n");

    for (int y = 0; y < SCREEN_ROWS_COUNT; y++) {
        printf("| ");
        for (int x = 0; x < SCREEN_COLUMNS_COUNT; x++) {
            int val = screen[y][x];
            if (val) printf("* ");
            else printf("  ");
        }
        printf("|\n");
    }

    for (int i = 0; i < SCREEN_COLUMNS_COUNT + 2; i++) {
        printf("%c ", 196);
    }
}

int main() {
    Screen screen;
    clearScreen(screen); // basically initializing the screen with 0s, as it is a local variable it will have garbage values by default
    drawRectangle(screen, 0, 0, 6, 6); //left eye
    fillRectangle(screen, 2, 2, 2, 2); //left eyeball
    drawRectangle(screen, 0, 7, 6, 6); //right eyeball
    fillRectangle(screen, 2, 9, 2, 2); //left eyeball
    /* the neck/head */
    drawVLine(screen, 6, 3, 6);
    drawVLine(screen, 6, 9, 6);
    drawHLine(screen, 12, 3, 7);
    fillRectangle(screen, 10, 5, 3, 2); //mouth
    drawRectangle(screen, 6, 5, 10, 3); //nose
    printScreen(screen);
    return 0;
}
