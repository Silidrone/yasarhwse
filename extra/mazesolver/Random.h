#ifndef R_H_RANDOM_H
#define R_H_RANDOM_H
#include <random>

class Random {
public:
	static Random & instance() {
		static Random r;
		return r;
	};
	int rand(int, int);
protected:
	std::random_device m_rd;
	std::mt19937 m_mt;
private:
	Random();
};

#endif 