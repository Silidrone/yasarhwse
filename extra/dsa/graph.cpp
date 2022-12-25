#include <iostream>
#include <vector>
#include <queue>

class DirectedGraph {
protected:
    std::vector<std::pair<int, std::vector<int>>> adj;
    
    bool exists(int v) {
        for(auto &pair : adj) {
            if(pair.first == v) return true;
        }
        
        return false;
    }
    
    std::vector<int> getAdj(int v) const {
        for(auto &pair : adj) {
            if(pair.first == v) return pair.second;
        }
        
        return {};
    }
    
    bool isVisited(int v, std::vector<std::pair<int, bool>> &visited) {
        for(auto &pair : visited) {
            if(pair.first == v) return pair.second;
        }
        
        return false;
    }
    
    void dfs_helper(int v, std::vector<std::pair<int, bool>> &visited) {
        std::cout << "visited: " << v << std::endl;
        visited.push_back({v, true});
        
        auto v_adj = getAdj(v);
        for(auto &w : v_adj) 
            if(!isVisited(w, visited))
                dfs_helper(w, visited);
    }
public:
    void addVertex(int v) {
        if(!exists(v))
            adj.push_back({v, {}});
    }
    
    void addEdge(int v1, int v2) {
        for(auto &pair : adj) {
            if(pair.first == v1) {
                pair.second.push_back(v2);
                return;
            }
        }
    }
    
    void print() {
        for(auto &pair : adj) {
            std::cout << pair.first << ", adj: [";
            bool first = true;
            for(auto &v : pair.second) {
                if(!first) std::cout << ", ";
                std::cout << v;
                first = false;
            }
            std::cout << "]" << std::endl;
        }  
    }
    
    void bfs(int v) {
        std::queue<int> q;
        std::vector<std::pair<int, bool>> visited;
        
        q.push(v);
        visited.push_back({v, true});
        do {
            int v1 = q.front();
            std::cout << "visited: " << v1 << std::endl;
            q.pop();
              
            auto v1_adj = getAdj(v1);
            for(auto &w : v1_adj) {
                if(!isVisited(w, visited)) {
                    visited.push_back({w, true});
                    q.push(w);
                }
            }
        } while(!q.empty());
    }
    
    void dfs(int v) {
        std::vector<std::pair<int, bool>> visited;
        dfs_helper(v, visited);
    }
};

int main() {
    DirectedGraph g1;
    g1.addVertex(0);
    g1.addVertex(1);
    g1.addVertex(2);
    g1.addVertex(3);
    g1.addVertex(4);
    g1.addEdge(0, 1);
    g1.addEdge(1, 0);
    g1.addEdge(0, 2);
    g1.addEdge(2, 0);
    g1.addEdge(1, 2);
    g1.addEdge(2, 1);
    g1.addEdge(1, 3);
    g1.addEdge(3, 1);
    g1.addEdge(2, 4);
    g1.addEdge(4, 2);
    g1.addEdge(3, 4);
    g1.addEdge(4, 3);
    g1.print();
    std::cout << "##BFS##" << std::endl;
    g1.bfs(0);
    std::cout << "##DFS##" << std::endl;
    g1.dfs(0);
}