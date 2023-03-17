#ifndef _BST_H
#define _BST_H

#include <string>
#include <vector>

struct WordOccurrence
{
	std::string str;
	int count;		
};

struct Node
{	
	WordOccurrence data;
	Node *left;
	Node *right;
};

class BST
{
public:
	BST();
	void insert(std::string);
	Node *search(std::string);
	std::vector<WordOccurrence> get_word_occurrences();
private:
	Node *createNode(std::string);
	std::vector<WordOccurrence> get_word_occurrences_helper(Node *);
	Node *root;
};

#endif