#include "bst.h"
#include <string.h>
#include <algorithm>

using namespace std;

BST::BST()
{
	root = nullptr;
}

Node *BST::createNode(string data)
{
	std::transform(data.begin(), data.end(), data.begin(), ::tolower);

	Node *n = new Node;
	n->data.str = data;
	n->data.count = 1;
	n->left = nullptr;
	n->right = nullptr;

	return n;
}

void BST::insert(string data)
{
	if(root == nullptr)
	{
		root = createNode(data);
		return;
	}

	std::transform(data.begin(), data.end(), data.begin(), ::tolower);
	
	Node *pre = nullptr;
	Node *cur = root;
	while(cur != nullptr)
	{
		pre = cur;
		int cmp_result = strcmp(data.c_str(), cur->data.str.c_str());
		if(cmp_result > 0){
			cur = cur->right;
		} else if (!cmp_result){
			cur->data.count++;
			return;
		} else{
			cur = cur->left;
		}
	}

	if(strcmp(data.c_str(), pre->data.str.c_str()) > 0){
		pre->right = createNode(data);
	} else{
		pre->left = createNode(data);
	}
}

Node *BST::search(string data)
{
	std::transform(data.begin(), data.end(), data.begin(), ::tolower);
	
	Node *cur = root;
	int cmp_result;
	
	while(cur != nullptr && (cmp_result = strcmp(data.c_str(), cur->data.str.c_str())))
	{
		if(cmp_result > 0){
			cur = cur->right;
		} else{
			cur = cur->left;
		}
	}

	return cur;
}

vector<WordOccurrence> BST::get_word_occurrences(){
	return get_word_occurrences_helper(root);
}

vector<WordOccurrence> BST::get_word_occurrences_helper(Node *node){
	static vector<WordOccurrence> result;
	if(node == nullptr) return result;
	
	get_word_occurrences_helper(node->left);
	result.push_back(node->data); 
	get_word_occurrences_helper(node->right);
	
	return result; 
}