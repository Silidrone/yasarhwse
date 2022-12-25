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

std::string getHuffmanCode(char symbol, HuffmanNode *root, std::string code = "") {
  if(root == nullptr) return "";
  if(root->symbol == symbol) return code;
  
  auto r1 = getHuffmanCode(symbol, root->left, code + '0');
  if(!r1.empty()) return r1;
  else return getHuffmanCode(symbol, root->right, code + '1');    
}

char getSymbol(std::string huffmanCode, HuffmanNode *root) {
  if(root == nullptr) return '\0';
  if(huffmanCode.empty()) return root->symbol;
  
  auto c = huffmanCode.front();
  huffmanCode.erase(0, 1);
  char result = '\0';
  if(c == '0') {
    result = getSymbol(huffmanCode, root->left);
  } else if(c == '1') {
    result = getSymbol(huffmanCode, root->right);
  }
  
  return result;
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

std::string encodeText(std::string text, HuffmanNode *root) {
  std::string result;
  for(int i = 0; i < text.size(); i++) {
    result += getHuffmanCode(text.at(i), root);
  }
  
  return result;
}

std::string decodeText(std::string text, HuffmanNode *root) {
  std::string result;
  for(int i = 0; i < text.size(); i++) {
    for(int j = i + 1; j <= text.size(); j++) {
      std::string p = text.substr(i, j);
      if(!p.empty()) {
        result += p;
        i += j - 1;
        break;
      }
    }  
  } 
  
  return result;
}

int main() {
    HuffmanNode *nodes[] = { new HuffmanNode('B', 1), new HuffmanNode('D', 3), new HuffmanNode('A', 5), new HuffmanNode('C', 6)};    
    auto size = sizeof(nodes) / sizeof(HuffmanNode *);
    HuffmanNode *t1 = constructHuffmanTree(nodes, size);
    inorder(t1);
    std::cout << std::endl;
    char symbols[] = {'A', 'B', 'C', 'D', 'E', 'F'};
    for(int i = 0; i < size; i++) {
        std::cout << symbols[i] << ": " << getHuffmanCode(symbols[i], t1) << std::endl;
    }
    
    std::string message = "CCBDDAAADCCCACA";
    std::string message_encoded = encodeText(message, t1);
    std::string message_decoded = decodeText(message, t1);
    std::cout << "Message: " << message << std::endl;
    std::cout << "Encoded: " << message_encoded << std::endl;
    std::cout << "Decoded: " << message_decoded << std::endl;
}