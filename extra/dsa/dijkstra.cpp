#include <iostream>
#include <vector>
#include <queue>
#include <bits/stdc++.h>

class WeightedDirectedGraph {
protected:
    std::vector<char> vertices;
    std::vector<std::pair<std::pair<char, char>, int>> edges;
public:
    void addVertex(char v) {
        vertices.push_back(v);
    }
    
    bool exists(char v) {
        for(auto &_v : vertices) {
            if(_v == v) return true;
        }
        
        return false;
    }

    void addEdge(char v1, char v2, int w) {
        if(!exists(v1)) addVertex(v1);
        if(!exists(v2)) addVertex(v2);
        
        edges.push_back({{v1, v2}, w});            
    }
    
    std::vector<char> neighbours(char v) {
        std::vector<char> result;
        for(auto &e : edges) {
            if(e.first.first == v) {
                result.push_back(e.first.second);
            }
        }
        
        return result;
    }
    
    int weight(char v1, char v2) {
        for(auto &e : edges) {
            if(e.first.first == v1 && e.first.second == v2) {
                return e.second;
            }
        }
        
        return 0;
    }
    
    std::map<char, int> dijkstra(char s) {
        if(!exists(s)) return {};
        
        std::map<char, int> distances;
        std::vector<char> q;
        for(auto &v : vertices) {
            distances[v] = INT_MAX;
            q.push_back(v);
        }
        
        distances[s] = 0;
        std::vector<char> visited;
        
        auto mark = [&visited](char v) {
            visited.push_back(v);
        };
        
        auto is_marked = [&visited](char v) {
            for(auto _v : visited) {
                if(_v == v) return true;
            }
            
            return false;
        };
                
        auto extract_min = [&q, &distances]() {
            int min_d = INT_MAX;
            char min_v;
            for(auto v : q) {
                if(distances[v] <= min_d) {
                    min_d = distances[v];
                    min_v = v;
                }
            }
            
            return min_v;
        };
        
        auto remove_from_q = [&q](char v) {
            for(auto it = q.begin(); it != q.end(); it++) {
                if(*it == v) {
                    q.erase(it);
                    return;
                }
            }
        };
        
        while(!q.empty()) {
            auto u = extract_min();
            mark(u);
            remove_from_q(u);
            std::cout << "visiting: " << u << std::endl;
            for(auto &n : neighbours(u)) {
                if(is_marked(n)) continue;
                
                if(distances[u] + weight(u, n) < distances[n]) {
                    distances[n] = distances[u] + weight(u, n);
                }
            }
        }
        
        return distances;
    }        
};

void print_dijkstra(char s, std::map<char, int> distances) {
    std::cout << "The shortest paths from " << s << ": " << std::endl;
    for (const auto& kv : distances) {
        std::cout << kv.first << " - " << kv.second << std::endl;
    }
}

int main() {
    WeightedDirectedGraph wdg;
    wdg.addEdge('A', 'D', 6);
    wdg.addEdge('A', 'B', 2);
    wdg.addEdge('A', 'C', 12);
    wdg.addEdge('B', 'C', 8);
    wdg.addEdge('D', 'E', 3);
    wdg.addEdge('D', 'C', 5);
    wdg.addEdge('B', 'D', 9);
    wdg.addEdge('C', 'E', 4);
    wdg.addEdge('B', 'E', 7);
    print_dijkstra('A', wdg.dijkstra('A'));
}