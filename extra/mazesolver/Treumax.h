#ifndef _TREUMAX_H 
#define _TREUMAX_H

#include "Robot.h"
#include "Position.h"
#include "Junction.h"
#include "Maze.h"
#include <vector>

class Treumax {
public:
	Position solve(const Maze &, Position);
	~Treumax();
protected: //member variables
	Robot m_robot;
	bool m_forward; // true = forward, false = backward
	Position m_junctional_position;
	std::vector<Junction *> m_junctions;
	void adjustRobotStartRotation(const Maze &);
protected: //member functions
	void junction_entered(std::vector<Degree>);
	void operate_junction(Junction *);
	Junction *getJunction(Position);

	std::vector<int> read_next_field(const Maze &);
	void adjustJunctionPosition();
};

#endif 