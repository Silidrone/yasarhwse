#include "Maze.h"

using namespace std;

Maze::Maze(string maze) {
	m_maze = mazify(maze);
}

Maze::Maze(vector<vector<char>> maze) {
	m_maze = maze;
}

int Maze::getYCount() const {
	return m_maze.size();
}

int Maze::getBiggestXCount() const{
	int biggest = -1;
	for(int i = 0; i < m_maze.size(); i++){
		int x_count = m_maze[i].size();
		if(x_count > biggest){
			biggest = x_count;
		}
	}
	return biggest;
}

char Maze::getField(Position position) const {
	return m_maze[position.getY()][position.getX()];
}

string Maze::getString() {
	string str;
	for (auto &row : m_maze) {
		for (auto &c : row) {
			str += c;
		}
		str += '\n';
	}
	return str;
}

vector<vector<char>> Maze::mazify(string maze_s) const {
	vector<vector<char>> maze = { {} };
	for (unsigned int i = 0, j = 0; i < maze_s.length(); i++) {
		char c = maze_s[i];
		if (c == '\n') {
			maze.push_back({});
			j++;
		}
		else {
			maze[j].push_back(c);
		}
	}
	return maze;
}