#include <iostream>
#include <vector>
#include <algorithm>

unsigned int maxSumBF(const std::vector<std::vector<unsigned int>> &triangle, int row_i = 0, int col_i = 0) {
    if(row_i + 1 == triangle.size()) return triangle[row_i][col_i];
    else return triangle[row_i][col_i] + std::max(maxSumBF(triangle, row_i + 1, col_i), maxSumBF(triangle, row_i + 1, col_i + 1));
}

int maxSumOn2(std::vector<std::vector<unsigned int>> triangle) {
    for(int i = triangle.size() - 1; i > 0; i--) {
        for(int j = 0; j < triangle[i - 1].size(); j++) {
            triangle[i - 1][j] += std::max(triangle[i][j], triangle[i][j + 1]);
        }
    }
    
    return triangle[0][0];   
}

int main()
{
    std::vector<std::vector<unsigned int>> triangle = {
        {75},
        {95, 64},
        {17, 47, 82},
        {18, 35, 87, 10},
        {20, 04, 82, 47, 65},
        {19, 01, 23, 75, 03, 34},
        {88, 02, 77, 73, 07, 63, 67},
        {99, 65, 04, 28, 06, 16, 70, 92},
        {41, 41, 26, 56, 83, 40, 80, 70, 33},
        {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
        {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
        {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
        {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
        {63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
        {4,  62, 98, 27, 23, 9,  70, 98, 73, 93, 38, 53, 60, 04, 23},
    };
    
    std::cout << "maxSum brute force: " << maxSumBF(triangle) << std::endl;
    std::cout << "maxSum O(n^2): " << maxSumOn2(triangle) << std::endl;

    return 0;
}