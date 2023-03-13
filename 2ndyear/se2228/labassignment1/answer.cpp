#include <iostream>
#include <functional>
#include <vector>
#include <string>
#include <stdlib.h>
#include <time.h>

#define EMPTY_SYMBOL ' '
#define HERO_SYMBOL 'H'
#define MONSTER_SYMBOL 'M'
#define STAMINA 10
#define MONSTER_REWARD 5
#define NUMBER_OF_MONSTERS 5
#define ROW 8
#define COL 8

/* Restrictions
 * Hero has a {STAMINA} amount of stamina.
 * Hero can spawn near a monster.
 * Moving each square decreases one stamina.
 * Moving on monster symbol grants the hero a {MONSTER_REWARD} amount of gold.
 * Hero can move in left, right, up, and down directions.
 * Hero cannot slay the same monster more than once.
 * When the hero's stamina reaches 0, he can not move further; however, he can slay the monster in the square and earn money.
 * Write a brute-force algorithm to calculate the most profiting path from the hero's starting position, and print it with the money earned.
 * Check example_output.txt for further details.
*/

/* Fill game area with emptySymbol
 * game area is {FIRST_DIMENSION} x {SECOND_DIMENSION} grid (or in programming terms 2 dimensional array)
 *  where the FIRST_DIMENSION and SECOND_DIMENSION parameters are constant variables with the initial value
 *  of 8 and 8. You have to use global constants so that If I want to test something, I can easily change the
 * dimensional values.
*/

char area[ROW][COL];

struct ProfitPath {
    int gold;
    std::string path;
};

struct Coordinate {
    int row, col;
};

Coordinate up(Coordinate c) {
    return { c.row - 1, c.col }; 
}

Coordinate down(Coordinate c) {
    return { c.row + 1, c.col };
}

Coordinate left(Coordinate c) {
    return { c.row, c.col - 1 };
}

Coordinate right(Coordinate c) {
    return { c.row, c.col + 1 };
}

Coordinate upleft(Coordinate c) {
    return up(left(c));
}

Coordinate upright(Coordinate c) {
    return up(right(c));
}

Coordinate downleft(Coordinate c) {
    return down(left(c));
}

Coordinate downright(Coordinate c) {
    return down(right(c));
}

typedef Coordinate (*Direction)(Coordinate);

Direction directions[8] = { up, down, left, right, upleft, upright, downleft, downright };

char getSymbol(Coordinate c) {
    return area[c.row][c.col];
}

void assignSymbol(Coordinate c, char symbol) {
    area[c.row][c.col] = symbol;
}

bool coordinatesEqual(Coordinate a, Coordinate b) {
    return a.row == b.row && a.col == b.col;
}

void fillGameArea() {
    for(int i = 0; i < ROW; i++) {
        for(int j = 0; j < COL; j++) {
            area[i][j] = EMPTY_SYMBOL;
        }
    }
}

// Prints the game area
void printGameArea() {
    std::cout << "   ";
    for(int j = 0; j < COL; j++) {
        std::cout << j << "  ";
    }
    std::cout << std::endl;
    
    for(int i = 0; i < ROW; i++) {
        std::cout << i << " ";
        for(int j = 0; j < COL; j++) {
            std::cout << "[" << area[i][j] << "]";
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;
}

// Checks that if the given coordinate is in the bound of the game area.
bool isValidCoordinate(Coordinate c) {
    return c.row > 0 && c.row < ROW && c.col > 0 && c.col < COL;
}

// Checks that if the given position is suitable to place a monster
// The position must be valid and empty
bool validMonsterPosition(Coordinate c) {
    if(!isValidCoordinate(c) || getSymbol(c) != EMPTY_SYMBOL) return false;
    
    for(Direction &direction : directions) {
        Coordinate neighbour1 = direction(c);
        Coordinate neighbour2 = direction(neighbour1);
        if((isValidCoordinate(neighbour1) && getSymbol(neighbour1) == MONSTER_SYMBOL) || 
            (isValidCoordinate(neighbour2) && getSymbol(neighbour2) == MONSTER_SYMBOL)) {
            return false;
        }
    }
    
    return true;
}

bool validHeroPosition(Coordinate c) {
    return isValidCoordinate(c) && (getSymbol(c) == EMPTY_SYMBOL);    
}

/* There must be 5 monsters, and each of them should be at least 2 square apart (diagonals included, the second square is not allowed)
 * to each other in every direction.
 * Monster positions will be assigned randomly to suitable squares
 * You can use import random in the desired language.
*/
std::vector<Coordinate> assignMonsters() {
    std::vector<Coordinate> monsters;
    for(int i = 0; i < NUMBER_OF_MONSTERS; i++) {
        Coordinate c;
        do {
            c = { rand() % ROW, rand() % COL };
        } 
        while (!validMonsterPosition(c));
        
        assignSymbol(c, MONSTER_SYMBOL);
        monsters.push_back(c);
    }
    
    return monsters;
}

// Assigns hero to a random suitable position and returns the coordinate.
// I advise you to create a simple Coordinate structure to store the position but, it is not mandatory to do that.
Coordinate assignHero() {
    Coordinate c;
    do {
        c = { rand() % ROW, rand() % COL };
    } while(!validHeroPosition(c));
    
    assignSymbol(c, HERO_SYMBOL);

    return c;
}

//calls the parameter function f, for each possible permutation Coordinate of all of the permutations of monsters coordinate vector
void permutate(std::vector<Coordinate> v, std::function<void(std::vector<Coordinate>)> f, std::vector<Coordinate> parents = {}) {
    if(v.size() == 0) {
        f(parents);
    }
    for(int i = 0; i < v.size(); i++) {
        std::vector<Coordinate> v1(v);
        v1.erase(v1.begin() + i);
        std::vector<Coordinate> parents_c(parents);
        parents_c.push_back(v[i]);
        permutate(v1, f, parents_c);
    }    
}

ProfitPath getMostProfitablePath (Coordinate hero, std::vector<Coordinate> monsters) {
    ProfitPath mostProfitablePath = {0, ""};
    permutate(monsters, [&hero, &mostProfitablePath](std::vector<Coordinate> targets) {
        int stamina = STAMINA;
        Coordinate currentCoordinate = hero;
        ProfitPath currentPath = {0, ""};
        int i = 0;
        while(stamina > 0 && i < NUMBER_OF_MONSTERS) {
            Coordinate monsterCoordinate = targets[i];
            if(coordinatesEqual(currentCoordinate, monsterCoordinate)) {
                currentPath.gold += MONSTER_REWARD;
                i++;
            } else {
                if(currentCoordinate.row > monsterCoordinate.row) {
                    currentCoordinate = up(currentCoordinate);
                    currentPath.path += "-U";
                } else if(currentCoordinate.row < monsterCoordinate.row) {
                    currentCoordinate = down(currentCoordinate);
                    currentPath.path += "-D";
                } else if(currentCoordinate.col > monsterCoordinate.col) {
                    currentCoordinate = left(currentCoordinate);
                    currentPath.path += "-L";
                } else if (currentCoordinate.col < monsterCoordinate.col) {
                    currentCoordinate = right(currentCoordinate);
                    currentPath.path += "-R";
                }
                stamina--;
            }
        }
        
        if(i < NUMBER_OF_MONSTERS && coordinatesEqual(currentCoordinate, targets[i])) {
            currentPath.gold += MONSTER_REWARD;
        }    
        
        if(currentPath.gold > mostProfitablePath.gold) {
            mostProfitablePath = currentPath;
        }
    });
         
    return mostProfitablePath;
}

int main() {
    srand ( time(NULL) );

    fillGameArea();
    printGameArea();
    
    std::vector<Coordinate> monsters = assignMonsters();
    Coordinate hero = assignHero();
    printGameArea();
    
    ProfitPath mostProfitablePath = getMostProfitablePath(hero, monsters);
    std::cout << "Maximum Possible Gold: " << mostProfitablePath.gold << std::endl;
    std::cout << "Best Path: " << mostProfitablePath.path << std::endl;
}