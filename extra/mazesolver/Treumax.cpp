#include "Treumax.h"
#include <vector>
#include <iostream>
#include <algorithm>
#include "Random.h"

using namespace std;

Treumax::~Treumax(){
	for (auto &junction : m_junctions) {
		delete junction;
	}
}

void Treumax::adjustRobotStartRotation(const Maze &maze){
	const Degree degrees[4] = { Degree(0), Degree(90), Degree(180), Degree(270) }; 
	
	for(int i = 0; i < 4; i++){
		m_robot.setRotationDegree(degrees[i]);
		auto sensor_values = read_next_field(maze);
		if(sensor_values[2]){
			return;
		}
	}
} 

Position Treumax::solve(const Maze &maze, Position start_position) {
	m_forward = true;
	m_junctional_position = Position(0, 0);
	m_robot.setStartPosition(start_position);
	adjustRobotStartRotation(maze);
	m_robot.move_forward();
	adjustJunctionPosition();
	while (true) {
		auto sensor_output = m_robot.readSensors(maze);
		if (sensor_output == vector<int>{0, 0, 1, 0, 0}) {
			m_robot.move_forward(); //straight only
			adjustJunctionPosition();
		}
		else if (sensor_output == vector<int>{1, 1, 1, 0, 0}) {
			sensor_output = read_next_field(maze);
			if (sensor_output != vector<int>{0, 0, 0, 0, 0}) { //straight left
				auto robot_degree = m_robot.getRotationDegree();
				junction_entered({ robot_degree, robot_degree + 180, robot_degree + 90 });
			}
			else { //left only
				m_robot.rotate(90);
				m_robot.move_forward();
				adjustJunctionPosition();
			}
		}
		else if (sensor_output == vector<int>{0, 0, 1, 1, 1}) {
			sensor_output = read_next_field(maze);
			if (sensor_output != vector<int>{0, 0, 0, 0, 0}) { //straight right
				auto robot_degree = m_robot.getRotationDegree();
				junction_entered({ robot_degree, robot_degree + 180, robot_degree - 90 });
			}
			else { //right only
				m_robot.rotate(-90);
				m_robot.move_forward();
				adjustJunctionPosition();
			}
		}
		else if (sensor_output == vector<int>{1, 1, 1, 1, 1}) {
			sensor_output = read_next_field(maze);
			auto robot_degree = m_robot.getRotationDegree();
			if (sensor_output != vector<int>{0, 0, 0, 0, 0}) { //plus passage
				junction_entered({ robot_degree, robot_degree + 180, robot_degree + 90, robot_degree - 90 });
			}
			else { //T_passage
				junction_entered({ robot_degree + 180, robot_degree + 90, robot_degree - 90 });
			}
		}
		else if (sensor_output == vector<int>{0, 0, 0, 0, 0} || sensor_output == vector<int>{0, 0, 2, 0, 0}) { // dead_end or starting point
			m_robot.rotate(180);
			m_robot.move_forward();
			adjustJunctionPosition();
			m_forward = false;
		}
		else if (sensor_output == vector<int>{0, 0, 3, 0, 0}) { // end of maze
			return m_robot.getPosition();
		}
	}

	return Position(-1, -1); //code will never reach here
}

std::vector<int> Treumax::read_next_field(const Maze &maze) {
	m_robot.move_forward();
	auto sensor_output = m_robot.readSensors(maze);
	m_robot.move_backward();
	return sensor_output;
}

void Treumax::junction_entered(std::vector<Degree> degrees) {
/*	static int debug_epoch = 0;
	debug_epoch++;
	std::cout << "junction_entered debug epoch: " << debug_epoch << std::endl;*/
	Junction *j = getJunction(m_junctional_position);
	if (j == nullptr) {
		j = new Junction();
		for (auto &degree : degrees) {
			j->setPassageState(degree, PassageState::EMPTY);
		}
		j->setPosition(m_junctional_position);
		m_junctions.push_back(j);
	}
	operate_junction(j);
}

void Treumax::operate_junction(Junction *j) {
	auto labeled_passages = j->getLabeledPassages();
	auto non_labeled_passages = j->getNonLabeledPassages();
	if (m_forward) { // marching forward into a 
		if (labeled_passages.size() == 0) { // new junction
			j->setPassageState(m_robot.getRotationDegree() + 180, PassageState::X);

			auto available_directions = j->getAvailableDirections();
			for (unsigned int i = 0; i < available_directions.size(); i++) {
				auto available_direction = available_directions[i];
				if (available_direction.getState() == PassageState::X) {
					available_directions.erase(available_directions.begin() + i);
				}
			}
			//auto random_direction_index = Random::instance().rand(0, available_directions.size() - 1);
			//auto random_direction_degree = available_directions[random_direction_index];
			//std::cout << "randomly selected passage index: " << random_direction_index << std::endl;
			//auto random_direction_degree = available_directions[0];
			auto random_direction_degree = available_directions[Random::instance().rand(0, available_directions.size() - 1)];
			m_robot.setRotationDegree(random_direction_degree.getDegree());
			m_robot.move_forward();
			adjustJunctionPosition();
			j->setPassageState(m_robot.getRotationDegree(), PassageState::N);
		}
		else { // old junction
			m_robot.move_backward();
			m_robot.rotate(180);
			adjustJunctionPosition();
			j->setPassageState(m_robot.getRotationDegree(), PassageState::N);
			m_forward = false;
		}
	}
	else { // marching backward into a
		if (non_labeled_passages.size() > 0) { // junction with some unmarked passages
			auto selected_passage_degree = non_labeled_passages[0];
			m_robot.setRotationDegree(selected_passage_degree.getDegree());
			m_robot.move_forward();
			adjustJunctionPosition();
			j->setPassageState(m_robot.getRotationDegree(), PassageState::N);
			m_forward = true;
		}
		else { // junction with no unmarked passages
			Degree selected_passage_degree;
			for (auto &e : labeled_passages) {
				if (e.getState() == PassageState::X) {
					selected_passage_degree = e.getDegree();
				}
			}
			m_robot.setRotationDegree(selected_passage_degree);
			m_robot.move_forward();
			adjustJunctionPosition();
		}
	}
}

void Treumax::adjustJunctionPosition() {
	auto degree = m_robot.getRotationDegree();
	if (degree == 180) {
		m_junctional_position.setX(m_junctional_position.getX() - 1);
	}
	else if (degree == 0) {
		m_junctional_position.setX(m_junctional_position.getX() + 1);
	}
	else if (degree == 90) {
		m_junctional_position.setY(m_junctional_position.getY() + 1);
	}
	else if (degree == 270) {
		m_junctional_position.setY(m_junctional_position.getY() - 1);
	}
}

Junction *Treumax::getJunction(Position p) {
	for (auto &junction : m_junctions) {
		if (junction->getPosition() == p) {
			return junction;
		}
	}
	return nullptr;
}