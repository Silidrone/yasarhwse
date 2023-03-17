#ifndef _ROBOT_H
#define _ROBOT_H

#include "LineSensor.h"
#include "Degree.h"

class Robot {
public:
	Robot();
	void setStartPosition(Position);
	void move_forward();
	void move_backward();
	void setRotationDegree(Degree);
	Degree getRotationDegree();
	void rotate(Degree);
	Position getPosition() const;
	std::vector<int> readSensors(const Maze &) const;
protected:
	Position m_position;
	Degree m_rotation_degree;
	std::vector<LineSensor> m_sensors;
	void adjustSensorPositions();
	void initSensors();
};

#endif 