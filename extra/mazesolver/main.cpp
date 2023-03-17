#include <string>
#include <iostream>
#include <fstream>
#include <vector>
#include <limits>
#include "Maze.h"
#include "Treumax.h"
#include "Random.h"

using namespace std;

string stringify(ifstream&);
vector<vector<char>> mazify(string);
Position generate_random_start(const Maze &);

int main() {
	ifstream maze_input;
	ofstream maze_output;
	ofstream brute_testing_output;

	maze_input.open("maze1.txt");
	maze_output.open("maze2.txt");
	brute_testing_output.open("brute_testing_output.txt");
	maze_input >> std::noskipws;

	auto maze_s = stringify(maze_input);
	maze_output << maze_s;
	
	Maze maze(maze_s);
	//auto execution_count = std::numeric_limits<long long>::max();
	auto execution_count = 10;
	cout << "Number of executions: " << execution_count << endl;
	for(int i = 0; i < execution_count; i++){
		auto random_start = generate_random_start(maze);
		auto solution = Treumax().solve(maze, random_start);
		
//		brute_testing_output << "Start position: " << endl;
		brute_testing_output << "B: " << random_start.getX() << ", " << random_start.getY() << endl;
//		brute_testing_output << "Solution: " << endl;
		brute_testing_output << "E: " << solution.getX() << ", " << solution.getY() << endl;
	}
	
	maze_input.close();
	maze_output.close();
	brute_testing_output.close();

	return 0;
}

Position generate_random_start(const Maze &maze){
	Position random_start;
	auto x_count = maze.getBiggestXCount();
	auto y_count = maze.getYCount();
	while(true){
		random_start = {Random::instance().rand(0, x_count - 1), Random::instance().rand(0, y_count - 1)};
		if(maze.getField(random_start) == 'O'){ 
			return random_start;
		}
	}
}

string stringify(ifstream& file) {
	string s;
	char c;

	while (file >> c) {
		s += c;
	}
	return s;
}
