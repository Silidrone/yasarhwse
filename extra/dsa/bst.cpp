#include <iostream>

struct BST_Node{
    BST_Node *left, *right;
    int val;
    
    BST_Node(int _val) {
        val = _val;
        left = right = nullptr;
    }
};

BST_Node *insert(int val, BST_Node *root) {
    if(root == nullptr) {
        return new BST_Node(val);    
    }
    
    if(val < root->val) {
        root->left = insert(val, root->left);
    } else {
        root->right = insert(val, root->right);
    }
    
    return root;
}

BST_Node *minNode(BST_Node *root) {
    if(root->left) {
        return minNode(root->left);
    }
    
    return root;
}
    
BST_Node *remove(int val, BST_Node *root) {
    if(root == nullptr) return nullptr;
    
    if(val < root->val) {
        root->left = remove(val, root->left);
    } else if(val > root->val) {
        root->right = remove(val, root->right);
    } else {
        if(root->left == nullptr && root->right == nullptr) {
            delete root;
            return nullptr;
        } else if(root->right == nullptr) {
            BST_Node *tmp = root->right;
            delete root;
            return tmp;
        } else if(root->left == nullptr) {
            BST_Node *tmp = root->left;
            delete root;
            return tmp;
        } else {
            BST_Node *inorder_successor = minNode(root->right);
            root->val = inorder_successor->val;
            root->right = remove(inorder_successor->val, root->right);            
        }
    }
    
    return root;
}

BST_Node* search(int val, BST_Node *root) {
    if(root == nullptr || val == root->val) return root;
    
    if(val < root->val) {
        return search(val, root->left);
    }
    
    return search(val, root->right);        
}
    
void inorder(BST_Node *root) {
    if(root == nullptr) return;
    inorder(root->left);
    std::cout << root->val << " ";
    inorder(root->right);
}

int main() {
    BST_Node *root = new BST_Node(5);
    root = insert(2, root);
    root = insert(7, root);
    root = insert(6, root);
    root = insert(9, root);
    root = insert(8, root);
    inorder(root);
    std::cout << std::endl;
    root = remove(8, root);
    inorder(root);
    std::cout << std::endl;
    root = remove(7, root);
    inorder(root);
    std::cout << std::endl;
    delete root;
}