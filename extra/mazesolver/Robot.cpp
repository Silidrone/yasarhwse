#include "Robot.h"
#include <chrono>
#include <thread>

using namespace std;

Robot::Robot() {
	initSensors();
	setStartPosition(Position(0, 0));
}

void Robot::setStartPosition(Position p) {
	m_position = p;
	adjustSensorPositions();
}

void Robot::move_forward() {
	if (m_rotation_degree == 0) {
		m_position.setX(m_position.getX() + 1);
	}
	else if (m_rotation_degree == 90) {
		m_position.setY(m_position.getY() - 1);
	}
	else if (m_rotation_degree == 180) {
		m_position.setX(m_position.getX() - 1);
	}
	else if (m_rotation_degree == 270) {
		m_position.setY(m_position.getY() + 1);
	}
	adjustSensorPositions();
}

void Robot::move_backward() {
	rotate(180);
	move_forward();
	rotate(180);
}

void Robot::setRotationDegree(Degree degree) {
	m_rotation_degree = degree;
}

Degree Robot::getRotationDegree() {
	return m_rotation_degree;
}

void Robot::rotate(Degree amount) {
	m_rotation_degree = m_rotation_degree + amount;
}

Position Robot::getPosition() const {
	return m_position;
}

vector<int> Robot::readSensors(const Maze &maze) const {
	vector<int> sensors_output = {};
	for (int i = 0; i < m_sensors.size(); i++) {
		sensors_output.push_back(static_cast<int>(m_sensors[i].getFieldType(maze)));
	}
	return sensors_output;
}

void Robot::adjustSensorPositions() {
	for (int i = 0, sensor_position_odd = -2, sensor_position_even = 2; i < m_sensors.size(); i++, sensor_position_odd++, sensor_position_even--) {
		auto &sensor = m_sensors[i];
		int sensor_position = sensor_position_odd;
		if (m_rotation_degree == 180 || m_rotation_degree == 270) {
			sensor_position = sensor_position_even;
		}

		if (m_rotation_degree == 0 || m_rotation_degree == 180) {
			sensor.setPosition(Position(m_position.getX(), m_position.getY() + sensor_position));
		}
		else if (m_rotation_degree == 90 || m_rotation_degree == 270) {
			sensor.setPosition(Position(m_position.getX() + sensor_position, m_position.getY()));
		}
	}
}

void Robot::initSensors() {
	const int sensor_count = 5;
	for (int i = 0; i < sensor_count; i++) {
		m_sensors.push_back(LineSensor(Position()));
	}
}
