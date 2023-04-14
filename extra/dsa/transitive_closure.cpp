#include <iostream>

#define MAX_VERTICES 6

bool d[MAX_VERTICES + 1][MAX_VERTICES][MAX_VERTICES];

//d[MAX_VERTICES] is the solution
void transitive_closure() {
    for(int k = 1; k <= MAX_VERTICES; k++) {
        for(int i = 0; i < MAX_VERTICES; i++) {
            for(int j = 0; j < MAX_VERTICES; j++) {
                d[k][i][j] = d[k - 1][i][j] || (d[k - 1][i][k - 1] && d[k - 1][k - 1][j]);;
            }
        }
    }
}

int main() {
    // Fill d with an adjacency matrix (form your directed weighted graph that you want a solution for)
    d[0][0][0] = 1;
    d[0][0][1] = 1;
    d[0][0][2] = 0;
    d[0][0][3] = 1;
    d[0][0][4] = 0;
    d[0][0][5] = 0;

    d[0][1][0] = 0;
    d[0][1][1] = 1;
    d[0][1][2] = 1;
    d[0][1][3] = 0;
    d[0][1][4] = 0;
    d[0][1][5] = 0;
    
    d[0][2][0] = 1;
    d[0][2][1] = 0;
    d[0][2][2] = 1;
    d[0][2][3] = 0;
    d[0][2][4] = 0;
    d[0][2][5] = 0;

    d[0][3][0] = 0;
    d[0][3][1] = 0;
    d[0][3][2] = 0;
    d[0][3][3] = 1;
    d[0][3][4] = 1;
    d[0][3][5] = 0;

    d[0][4][0] = 0;
    d[0][4][1] = 0;
    d[0][4][2] = 0;
    d[0][4][3] = 0;
    d[0][4][4] = 1;
    d[0][4][5] = 1;

    d[0][5][0] = 0;
    d[0][5][1] = 0;
    d[0][5][2] = 0;
    d[0][5][3] = 0;
    d[0][5][4] = 0;
    d[0][5][5] = 1;
    
    transitive_closure();
    
    // printing the solution
    for(int k = 0; k <= MAX_VERTICES; k++) {
        std::cout << "k = " << k << ": " << std::endl;
        for(int i = 0; i < MAX_VERTICES; i++) {
            for(int j = 0; j < MAX_VERTICES; j++) {
                std::cout << d[k][i][j];
                if(j != MAX_VERTICES - 1) {
                    if(d[k][i][j + 1] >= 0) {
                        std::cout << "   ";
                    } else {
                        std::cout << "  ";
                    }
                }
            }
            std::cout << std::endl;
        }
        std::cout << std::endl << std::endl;
    } 
}