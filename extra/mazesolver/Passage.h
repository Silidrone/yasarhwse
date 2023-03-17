#ifndef _PASSAGE_H
#define _PASSAGE_H
#include "Degree.h"

enum class PassageState
{
	NON_EXISTENT,
	EMPTY,
	X,
	N
};

class Passage {
public:
	Passage(Degree degree, PassageState passage_state) {
		m_degree = degree;
		m_passage_state = passage_state;
	}

	void setDegree(Degree degree) {
		m_degree = degree;
	}
	
	Degree getDegree() const{
		return m_degree;
	}

	void setState(PassageState passage_state) {
		m_passage_state = passage_state;
	}

	PassageState getState() const{
		return m_passage_state;
	}
protected:
	Degree m_degree;
	PassageState m_passage_state;
};

#endif 