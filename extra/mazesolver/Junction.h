#ifndef _JUNCTION_H
#define _JUNCTION_H

#include <vector>
#include <map>
#include "Position.h"
#include "Degree.h"
#include "Passage.h"

class Junction {
public:
	Junction() {
		constexpr int passage_count = 4;
		for (unsigned int i = 0; i < passage_count; i++) {
			m_passages.push_back(Passage(Degree(i * 90), PassageState::NON_EXISTENT));
		}
	}

	void setPassageState(Degree degree, PassageState state) {
		for (auto &passage : m_passages) {
			if (passage.getDegree() == degree) {
				passage.setState(state);
			}
		}
	}

	Passage getPassage(Degree degree) {
		for (auto &passage : m_passages) {
			if (passage.getDegree() == degree) {
				return passage;
			}
		}
	}

	std::vector<Passage> getLabeledPassages() const {
		std::vector<Passage> result;
		for (auto &passage : m_passages) {
			if (passage.getState() == PassageState::X || passage.getState() == PassageState::N) {
				result.push_back(passage);
			}
		}
		return result;
	}

	std::vector<Passage> getNonLabeledPassages() const {
		std::vector<Passage> result;
		for (auto &passage : m_passages) {
			if (passage.getState() == PassageState::EMPTY) {
				result.push_back(passage);
			}
		}
		return result;
	}


	std::vector<Passage> getAvailableDirections() const {
		std::vector<Passage> result;
		for (auto &passage : m_passages) {
			if (passage.getState() != PassageState::NON_EXISTENT) {
				result.push_back(passage);
			}
		}
		return result;
	}

	void setPosition(Position p) {
		m_position = p;
	}

	Position getPosition() const {
		return m_position;
	}
protected:
	std::vector<Passage> m_passages;
	Position m_position;
};

#endif 