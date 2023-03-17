#ifndef _MAZE_H
#define _MAZE_H 

#include <vector>
#include <string>
#include "Position.h"

class Maze {
public:
	Maze(std::string);
	Maze(std::vector<std::vector<char>>);
	int getYCount() const, getBiggestXCount() const;
	char getField(Position) const;
	std::string getString();
protected:
	std::vector<std::vector<char>> m_maze;
	std::vector<std::vector<char>> mazify(std::string maze_s) const;
};

#endif 