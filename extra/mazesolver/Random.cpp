#include "Random.h"
#include <iostream>

Random::Random() : m_mt(m_rd()) {
	//std::mt19937::result_type seed = time(0);
	//std::mt19937::result_type seed = 1579713935;
	//std::cout << "seed is: " << seed << std::endl;
	//m_mt.seed(seed);
}

int Random::rand(int f_in, int t_in){
	std::uniform_int_distribution<int> dist(f_in, t_in);

	return dist(m_mt);
}