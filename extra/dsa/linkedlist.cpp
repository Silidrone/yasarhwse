#include <iostream>

struct Node {
    Node *next;
    int val;
    
    Node(int _val) : val(_val) {}
};

class LinkedList{
protected:
    Node *m_head;
public:
    LinkedList() : m_head(nullptr) {};
    LinkedList(int val) : m_head(new Node(val)) {};
    void insert(int val) {
        if(m_head == nullptr) {
            m_head = new Node(val);
            return;
        }
        
        Node *iterator = m_head;
        while(iterator->next != nullptr) {
            iterator = iterator->next;
        }
        
        iterator->next = new Node(val);
    }
    
    int size() {
        int _size = 0;
        Node *iterator = m_head;
        while(iterator != nullptr) {
            _size++;
            iterator = iterator->next;
        }
        
        return _size;
    }
    
    int occurrence_count(int val) {
        int count = 0;
        Node *iterator = m_head;
        while (iterator != nullptr) {
            if(iterator->val == val) {
                count++;
            }
            
            iterator = iterator->next;
        }
        
        return count;
    }
    
    void print() {
        Node *iterator = m_head;
        while(iterator != nullptr) {
            std::cout << iterator->val;
            if(iterator->next) std::cout << "->";
            iterator = iterator->next;
        }
        std::cout << std::endl;
    }
};

int main() {
    LinkedList ll1(5);    
    ll1.insert(5);
    ll1.insert(3);
    ll1.insert(1);
    ll1.insert(5);
    ll1.insert(12);
    ll1.insert(1);
    std::cout << "ll1 size: " << ll1.size() << std::endl;
    std::cout << "number of 5s in ll1: " << ll1.occurrence_count(5) << std::endl;
    std::cout << "number of 1s in ll1: " << ll1.occurrence_count(1) << std::endl;
    ll1.print();    
}