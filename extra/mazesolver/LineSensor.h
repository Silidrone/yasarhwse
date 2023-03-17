#ifndef _LINESENSOR_H
#define _LINESENSOR_H

#include "Maze.h"

enum class FieldType : int{
	ERROR = -1, WHITE = 0, BLACK = 1, START = 2, END = 3
};

class LineSensor {
public:
	LineSensor() {
		m_position = Position(0, 0);
	};

	LineSensor(Position position) {
		setPosition(position);
	}

	void setPosition(Position position) {
		m_position = position;
	}

	Position getPosition() const {
		return m_position;
	}

	char getField(const Maze &m) const {
		return m.getField(m_position);
	}

	FieldType getFieldType(const Maze &m) const {
		auto field = getField(m);
		if (field == 'O') {
			return FieldType::BLACK;
		}
		else if (field == ' ') {
			return FieldType::WHITE;
		}
		else if (field == 'S'){
			return FieldType::START;
		}
		else if (field == 'E'){
			return FieldType::END;
		}
		return FieldType::ERROR;
	}
protected:
	Position m_position;
};

#endif 