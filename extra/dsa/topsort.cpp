#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>
#include <queue>

class LLNode
{
public:
    int val;
    LLNode *next;

    LLNode(int _val) : val(_val), next(nullptr) {}
};

class LinkedList
{
private:
    LLNode *m_root;

public:
    LinkedList() : m_root(nullptr) {}

    void push_front(int val)
    {
        LLNode *newNode = new LLNode(val);
        newNode->next = m_root;
        m_root = newNode;
    }

    void print()
    {
        std::cout << "{";
        for (auto it = m_root; it != nullptr; it = it->next)
        {
            std::cout << it->val;
            if (it->next != nullptr)
            {
                std::cout << ", ";
            }
        }
        std::cout << "}" << std::endl;
    }
};

class DAG
{
private:
    std::vector<int> m_nodes;
    std::vector<std::unordered_set<int>> m_adjList;
    std::vector<int> m_indegrees;
public:
    void add(int val, std::vector<int> adjList = {})
    {
        m_nodes.push_back(val);

        if (m_adjList.size() < val + 1)
        {
            m_adjList.resize(val + 1);
        }
        for (auto adj : adjList)
        {
            if (m_indegrees.size() < adj + 1)
            {
                m_indegrees.resize(adj + 1);
            }
            m_indegrees[adj]++;
            m_adjList[val].insert(adj);
        }
    }

    std::vector<int> getZeroInDegreeNodes()
    {
        std::vector<int> result;

        for (auto node : m_nodes)
        {
            if (m_indegrees[node] == 0)
                result.push_back(node);
        }

        return result;
    }

    int getSize()
    {
        int size = 0;
        for (auto node : m_nodes)
        {
            if (node != 0)
                size++;
        }

        return size;
    }

    void print()
    {
        for (auto node : m_nodes)
        {
            std::cout << node << ": {";
            auto adjList = m_adjList[node];
            int i = 0;
            for (auto it = adjList.begin(); it != adjList.end(); it++)
            {
                std::cout << *it;
                if (i++ != adjList.size() - 1)
                {
                    std::cout << ", ";
                }
            }
            std::cout << "} - indegrees: " << m_indegrees[node] << std::endl;
        }
    }

    void dfs_add(int node, LinkedList *ll, std::vector<int> &visited)
    {
        if (std::find(visited.begin(), visited.end(), node) != visited.end())
            return;
        visited.push_back(node);

        for (auto adj : m_adjList[node])
        {
            dfs_add(adj, ll, visited);
        }

        ll->push_front(node);
    }

    void topsort_dfs()
    {
        LinkedList *ll = new LinkedList();
        std::vector<int> visited = {};
        auto zeroInDegreeNodes = getZeroInDegreeNodes();
        for (auto zeroInDegreeNode : zeroInDegreeNodes)
        {
            dfs_add(zeroInDegreeNode, ll, visited);
        }
        ll->print();
        delete ll;
    }

    void topsort_kahn()
    {
        std::vector<int> visited;
        std::vector<int> indegrees(m_indegrees);
        std::queue<int> toVisit;

        for (auto zeroInDegreeNode : getZeroInDegreeNodes())
        {
            toVisit.push(zeroInDegreeNode);
        }

        while (!toVisit.empty())
        {
            int node = toVisit.front();
            toVisit.pop();
            visited.push_back(node);
            for (auto adj : m_adjList[node])
            {
                indegrees[adj] -= 1;
                if (indegrees[adj] == 0)
                {
                    toVisit.push(adj);
                }
            }
        }

        if (visited.size() != getSize())
        {
            std::cout << "No topsort!" << std::endl;
        }
        else
        {
            std::cout << "{";
            for (int i = 0; i < visited.size(); i++)
            {
                std::cout << visited[i];
                if(i != visited.size() - 1) {
                    std::cout << ", ";
                }
            }
            std::cout << "}" << std::endl;
        }
    }
};

int main()
{
    DAG dag;
    dag.add(10);
    dag.add(8);
    dag.add(2, {10});
    dag.add(6, {8});
    dag.add(3);
    dag.add(1);
    dag.add(9);
    dag.add(7, {6, 3, 1});
    dag.add(5, {6, 3});
    dag.add(4, {2, 8});
    dag.print();
    dag.topsort_dfs();
    dag.topsort_kahn();
}