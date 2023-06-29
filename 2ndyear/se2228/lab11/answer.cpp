#include <iostream>
#include <stdlib.h>
#include <time.h>
#include <vector>

#define DIMENSION 3
#define PLAYER1_MARKER 'X'
#define COMPUTER_MARKER 'O'
#define EMPTY_SPACE ' '
using namespace std;

char board[DIMENSION][DIMENSION];
using BoardPosition = std::pair<int, int>;
const BoardPosition invalidBoardPosition = {-1, -1};

// Fill the game board with empty space
void clearBoard()
{
    for (int i = 0; i < DIMENSION; i++)
    {
        for (int j = 0; j < DIMENSION; j++)
        {
            board[i][j] = EMPTY_SPACE;
        }
    }
}

// Print the board in the given format in std.txt file
void printBoard()
{
    for (int i = 0; i < DIMENSION; i++)
    {
        for (int j = 0; j < DIMENSION; j++)
        {
            std::cout << "[" << board[i][j] << "]";
        }
        std::cout << std::endl;
    }

    std::cout << std::endl << std::endl;
}

bool equalsPosition(BoardPosition p1, BoardPosition p2)
{
    return p1.first == p2.first && p1.second == p2.second;
}

bool isValidPosition(int row, int col)
{
    return row >= 0 && row < DIMENSION && col >= 0 && col < DIMENSION;
}

bool isValidPosition(BoardPosition pos)
{
    return isValidPosition(pos.first, pos.second);
}

// Check that if the given coordinate is in the bound of board and suitable to place a marker
bool isAvailable(int row, int col)
{
    return isValidPosition(row, col) && board[row][col] == EMPTY_SPACE;
}

bool isAvailable(BoardPosition pos)
{
    return isAvailable(pos.first, pos.second);
}

// Place Marker. Place the corresponding marker to the board
void placeMarker(int row, int col, bool firstPlayerTurn)
{
    board[row][col] = firstPlayerTurn ? PLAYER1_MARKER : COMPUTER_MARKER;
}

std::vector<std::pair<std::vector<BoardPosition>, int>> getStreaksScores(char marker)
{
    std::pair<std::vector<BoardPosition>, int> top = {{}, 0};
    std::pair<std::vector<BoardPosition>, int> left = {{}, 0};
    std::pair<std::vector<BoardPosition>, int> down = {{}, 0};
    std::pair<std::vector<BoardPosition>, int> right = {{}, 0};
    std::pair<std::vector<BoardPosition>, int> left_diagonal = {{}, 0};
    std::pair<std::vector<BoardPosition>, int> right_diagonal = {{}, 0};

    for (int i = 0; i < DIMENSION; i++)
    {
        top.first.push_back({0, i});
        if (board[0][i] == marker)
            top.second++;

        left.first.push_back({i, 0});
        if (board[i][0] == marker)
            left.second++;

        down.first.push_back({DIMENSION - 1, i});
        if (board[DIMENSION - 1][i] == marker)
            down.second++;

        right.first.push_back({i, DIMENSION - 1});
        if (board[i][DIMENSION - 1] == marker)
            right.second++;

        left_diagonal.first.push_back({i, i});
        if (board[i][i] == marker)
            left_diagonal.second++;

        right_diagonal.first.push_back({i, DIMENSION - 1 - i});
        if (board[i][DIMENSION - 1 - i] == marker)
            right_diagonal.second++;
    }

    std::vector<std::pair<std::vector<BoardPosition>, int>> result = {top, left, down, right, left_diagonal, right_diagonal};

    for (int i = 1; i < DIMENSION - 1; i++)
    {
        std::pair<std::vector<BoardPosition>, int> horizontal = {{}, 0};
        std::pair<std::vector<BoardPosition>, int> vertical = {{}, 0};
        for (int j = i; i < DIMENSION; i++)
        {
            horizontal.first.push_back({i, j});
            if (board[i][j] == marker)
                horizontal.second++;

            vertical.first.push_back({j, i});
            if (board[j][i] == marker)
                vertical.second++;
        }

        result.push_back(horizontal);
        result.push_back(vertical);
    }

    return result;
}

std::vector<BoardPosition> getStreak(char marker, int score)
{
    auto streaks = getStreaksScores(marker);
    for (auto streak : streaks)
    {
        if (streak.second == score)
        {
            return streak.first;
        }
    }

    return {};
}

void randomMove(char marker) {
    srand(time(NULL));
    int row, col;
    do
    {
        row = rand() % DIMENSION;
        col = rand() % DIMENSION;
    } while (!isAvailable(row, col));

    board[row][col] = marker;
}

void computerMove()
{
    randomMove(COMPUTER_MARKER);
}

// Place your marker to any available corner.
BoardPosition playerFirstMove()
{
    BoardPosition corners_positions[4] = {{0, 0}, {0, DIMENSION - 1}, {DIMENSION - 1, 0}, {DIMENSION - 1, DIMENSION - 1}};
    for (auto &c : corners_positions)
    {

        if (board[c.first][c.second] == EMPTY_SPACE)
        {
            board[c.first][c.second] = PLAYER1_MARKER;
            return c;
        }
    }

    return invalidBoardPosition;
}

// Place a second marker in an adjacent corner
void playerSecondMove(BoardPosition first_move)
{
    std::vector<std::pair<BoardPosition, std::vector<BoardPosition>>> corners_neighbours = {
        {{0, 0}, {{0, 1}, {1, 0}, {1, 1}}},
        {{0, DIMENSION - 1}, {{0, DIMENSION - 2}, {1, DIMENSION - 1}, {1, DIMENSION - 2}}},
        {{DIMENSION - 1, 0}, {{DIMENSION - 2, 0}, {DIMENSION - 1, 1}, {DIMENSION - 2, 1}}},
        {{DIMENSION - 1, DIMENSION - 1}, {{DIMENSION - 1, DIMENSION - 2}, {DIMENSION - 2, DIMENSION - 1}, {DIMENSION - 2, DIMENSION - 2}}}};

    if (isValidPosition(first_move))
    {
        for (auto &corner_neighbours : corners_neighbours)
        {
            if (equalsPosition(first_move, corner_neighbours.first))
            {
                for (auto &corner_neighbour : corner_neighbours.second)
                {
                    if (isAvailable(corner_neighbour))
                    {
                        board[corner_neighbour.first][corner_neighbour.second] = PLAYER1_MARKER;
                        break;
                    }
                }
            }
        }
    }
}

// Fill the function to apply correct strategy
void playerMove(int current_move_count)
{
    auto fillThreatStreak = [](char threatMarker, char fillMarker) {
        auto threatStreak = getStreak(threatMarker, DIMENSION - 1); // This is a possible win streak, that is one move away from win
        if (!threatStreak.empty())
        {
            for (auto pos : threatStreak)
            {
                if (board[pos.first][pos.second] == EMPTY_SPACE)
                {
                    board[pos.first][pos.second] = fillMarker;
                    return true;
                }
            }
        }
        return false;
    };

    auto filled = fillThreatStreak(COMPUTER_MARKER, PLAYER1_MARKER) || fillThreatStreak(PLAYER1_MARKER, PLAYER1_MARKER);

    if(!filled) {
        randomMove(PLAYER1_MARKER);
    }
}

bool computerWon()
{
    return !getStreak(COMPUTER_MARKER, DIMENSION).empty();
}

bool player1Won()
{
    return !getStreak(PLAYER1_MARKER, DIMENSION).empty();
}

bool boardIsFull()
{
    bool availablePos = false;
    for (int i = 0; i < DIMENSION; i++)
    {
        for (int j = 0; j < DIMENSION; j++)
        {
            if (isAvailable(i, j))
            {
                availablePos = true;
                break;
            }
        }
    }

    return !availablePos;
}

// Checks if the game is over in any state
bool gameOver()
{
    // here getStreaks is computed twice unnecessarily but I am too lazy to optimize it tbh, I don't want to make the code look uglier and I don't want to spend effort trying to refactor it so it is optimized and is not uglier
    return computerWon() || player1Won() || boardIsFull();
}

int main()
{
    int current_move_count = 1;
    BoardPosition first_move = invalidBoardPosition;
    bool compWin, playerWin, boardFull;

    clearBoard(); // here used in context of initialization

    do
    {
        if (current_move_count == 1)
        {
            first_move = playerFirstMove();
        }
        else if (current_move_count == 3)
        {
            playerSecondMove(first_move);
        }
        else if(current_move_count % 2 != 0)
        {
            playerMove(current_move_count);
        } 
        else {
            computerMove();
        }

        current_move_count++;

        printBoard();
        compWin = computerWon();
        playerWin = player1Won();
        boardFull = boardIsFull();
    } while (!compWin && !playerWin && !boardFull);

    if (compWin)
    {
        std::cout << "Computer won!" << std::endl;
    }
    else if (playerWin)
    {
        std::cout << "Player won!" << std::endl;
    }
    else if (boardIsFull)
    {
        std::cout << "Draw!" << std::endl;
    }
    else
    {
        std::cout << "Game ended due to unexpected error!" << std::endl;
    }
}