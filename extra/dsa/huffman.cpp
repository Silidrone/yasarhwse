#include <iostream>

struct HuffmanNode {
    char symbol;
    int weight;
    HuffmanNode *left;
    HuffmanNode *right;
    HuffmanNode *next;
    
    HuffmanNode(char _symbol, int _weight) {
        symbol = _symbol;
        weight = _weight;
    }
    
    HuffmanNode(int _weight) {
        symbol = '\0';
        weight = _weight;
    }
};

void inorder(HuffmanNode *root) {
    if(root == nullptr) return;
    
    inorder(root->left);
    if(root->symbol) {
     std::cout << root->symbol << " - ";
    }
    std::cout << root->weight << std::endl;
    inorder(root->right);
}

HuffmanNode *constructHuffmanTree(HuffmanNode **nodes, int size) {
    if(size > 1) {
        HuffmanNode *first_node = *nodes;
        HuffmanNode *second_node = *(nodes + 1);
        HuffmanNode *parent_node = new HuffmanNode(first_node->weight + second_node->weight);
        parent_node->left = first_node;
        parent_node->right = second_node;
        nodes++;
        HuffmanNode **it;
        for(it = nodes; (*it)->weight <= parent_node->weight && it < (nodes + size - 2); it++) {
          *it = *(it + 1);  
        }
        *it = parent_node;
        
        return constructHuffmanTree(nodes, size - 1);
    } else if(size == 1) {
        return *nodes;
    }
    
    return nullptr;
}

int main() {
    HuffmanNode *nodes[] = {
                             new HuffmanNode('A', 1), new HuffmanNode('D', 1), new HuffmanNode('E', 1),
                             new HuffmanNode('B', 2), new HuffmanNode('F', 3), new HuffmanNode('C', 4)
                           };    
    HuffmanNode *t1 = constructHuffmanTree(nodes, sizeof(nodes) / sizeof(HuffmanNode *));
    inorder(t1);
    std::cout << std::endl;
}