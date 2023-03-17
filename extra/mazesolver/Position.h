#ifndef _POSITION_H
#define _POSITION_H

class Position {
public:
	Position(int x, int y) {
		m_x = x;
		m_y = y;
	}
	Position() {
		m_x = 0;
		m_y = 0;
	}

	void setX(int x) { m_x = x; }
	void setY(int y) { m_y = y; }
	int getX() const { return m_x; }
	int getY() const { return m_y; }
	bool operator==(const Position& other) {
		return ((m_x == other.m_x) && (m_y == other.m_y));
	}
protected:
	int m_x, m_y;
};

#endif 