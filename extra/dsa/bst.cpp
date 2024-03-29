#include <iostream>
#include <algorithm>

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

void insert_inorder(int **dst, int size, BST_Node *root) {
    if(root == nullptr) return;
    insert_inorder(dst, size, root->left);
    **dst = root->val;
    (*dst)++;
    insert_inorder(dst, size, root->right);
}

void disp_rml(BST_Node *root) {
    if(root == nullptr) return;
    disp_rml(root->right);
    std::cout << root->val << " ";
    disp_rml(root->left);
}

BST_Node* constructBalancedTree(int *dataset, int size) {
    if(size == 0) return nullptr;
    if(size == 1) return new BST_Node(dataset[0]);
    int median_index = (size + 1) / 2 - 1;
    BST_Node *new_node = new BST_Node(dataset[median_index]);
    new_node->left = constructBalancedTree(dataset, median_index);
    new_node->right = constructBalancedTree(dataset + median_index + 1, size - (median_index + 1));
    return new_node;
}

int bstSize(BST_Node *root) {
    if(root == nullptr) return 0;
    return bstSize(root->left) + 1 + bstSize(root->right);
}

int *treeSort(int *src, int size) {
    if(size <= 0) return nullptr;
    // O(nlogn)
    BST_Node *root = new BST_Node(*src);
    for(int *it = src + 1; it != src + size; it++) {
        root = insert(*it, root);
    }
    //BST_Node *root = constructBalancedTree(src, size);
    
    int *result = new int[size];
    int *prev = result;
    insert_inorder(&result, size, root);
    
    return prev;
}

int bstHeight(BST_Node *root) {
    if(root == nullptr) return 0;
    
    return 1 + std::max(bstHeight(root->left), bstHeight(root->right));
}

int main() {
    BST_Node *root = new BST_Node(5);
    root = insert(2, root);
    root = insert(7, root);
    root = insert(6, root);
    root = insert(9, root);
    root = insert(8, root);
    std::cout << "bstSize: " << bstSize(root) << std::endl;
    inorder(root);
    std::cout << std::endl;
    disp_rml(root);
    std::cout << std::endl;
    root = remove(8, root);
    std::cout << "bstSize: " << bstSize(root) << std::endl;
    inorder(root);
    std::cout << std::endl;
    root = remove(7, root);
    std::cout << "bstSize: " << bstSize(root) << std::endl;
    inorder(root);
    std::cout << std::endl;
    
    int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    BST_Node *avl_root = constructBalancedTree(arr, 10);
    std::cout << "AVL bstSize: " << bstSize(avl_root) << std::endl;
    inorder(avl_root);
    std::cout << std::endl;
    std::cout << "Height of the AVL tree: " << bstHeight(avl_root) << std::endl;
    delete root;
    
    int arr1[] = {7, 3, 1, 8, 2, 4, 10, 9};
    int *sorted_arr1 = treeSort(arr1, 8);
    std::cout << "sorted_arr1: " << std::endl;
    for(int i = 0; i < 8; i++) {
        std::cout << sorted_arr1[i] << " ";
    }
    std::cout << std::endl;
}