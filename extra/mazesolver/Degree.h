#ifndef _DEGREE_H
#define _DEGREE_H
#include <stdlib.h>

class Degree {
public:
	Degree() {
		m_degree = 90;
	}

	Degree(int degree) {
		set(degree);
	}

	void set(int degree) {
		if (degree >= 360) {
			degree = degree % 360;
		}
		else if (degree < 0) {
			degree = 360 - (abs(degree) % 360);
		}
		m_degree = degree;
	}

	int get() const { return m_degree; }

	Degree operator+(const Degree &other) const {
		Degree result;
		result.set(m_degree + other.m_degree);
		return result;
	}

	Degree operator+(int degree) const {
		Degree result;
		result.set(m_degree + degree);
		return result;
	}

	Degree operator-(const Degree &other) const {
		Degree result;
		result.set(m_degree - other.m_degree);
		return result;
	}

	Degree operator-(int degree) const {
		Degree result;
		result.set(m_degree - degree);
		return result;
	}

	bool operator==(const Degree &other) {
		return (m_degree == other.m_degree);
	}

	bool operator==(int degree) {
		Degree degree_obj;
		degree_obj.set(degree); 
		return (m_degree == degree_obj.m_degree);
	}
protected:
	int m_degree;
};

#endif 