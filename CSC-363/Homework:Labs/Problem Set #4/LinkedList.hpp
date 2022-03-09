/***************************************************************
Boone Tison
LinkedList.hpp
Problem Set #4
A singly linked list with sentinel nodes for the head and tail
Implements the ListInterface.h
***************************************************************/

#ifndef _LINKED_LIST_GUARD
#define _LINKED_LIST_GUARD 1

#include <iostream>

#include "ListInterface.h"

template <typename T>
class LinkedList : public ListInterface<T>
{
private:
	//
	// Private node class to facilitate linked list
	//
	class Node
	{
	public:
		T _data;
		Node* _next;

		// Constructor: default
		Node(T d = T{}, Node* n = nullptr) : _data(d), _next(n) {}

		~Node() { _next = nullptr; }
	};

	//
	// Prevent copying and assigning
	//
	LinkedList(const LinkedList& rhs) {}
	const LinkedList& operator=(const LinkedList& rhs) {}

public:
	//
	// LinkedList instance variables; we use dummy head and tail nodes in this implementation
	//
	unsigned _size;
	Node* _head;
	Node* _tail;

	// Default constructor
	LinkedList() {
		_size = 0;
		_tail = new Node(T{}, nullptr);
		_head = new Node(T{}, _tail);
	}

	// Destructor
	~LinkedList() {
		clear();
		delete _head;
		delete _tail;
	}

	// Returns the first element in the list
	T& first() const { 
		return _head->_next->_data; 
	}

	// Returns the last element in the list
	T& last() const {
		if (isEmpty()) return _head->_data; // Returns default data from head

		Node* pos = _head;
		while (pos->_next != _tail) { // Find the last node in the list
			pos = pos->_next;
		}
		return pos->_data;
	}

	// Determines if the linked list contains no elements
	bool isEmpty() const { return size() == 0; }

	// Returns the number of elements in this container
	unsigned size() const { return _size; }

	// Adds an item to the left side of the linked list
	void push(const T& x) {
		Node* n = new Node(x, _head->_next);
		_head->_next = n;
		_size++;
	}

	// Removes and returns an item from the left side of the linked list
	bool pop() {
		if (isEmpty()) return false;

		Node* next = _head->_next->_next;
		delete _head->_next;
		_head->_next = next;
		_size--;
		return true;
	}

	// Returns the item at the given index: list[n]
	// @throws a std::string exception if n is out of bounds.
	T& operator[](unsigned n) {
		if (n >= _size || n < 0) throw std::string("Index Out of Bounds Error");

		Node* pos = _head->_next;
		while (n >= 1) {
			n--;
			pos = pos->_next;
		}
		return pos->_data;
	}

	// Determines if a target value is in the LinkedList; uses == for equality comparison
	bool contains(const T& target) const {
		if (isEmpty()) return false;

		Node* pos = _head->_next;
		while (pos != _tail) {
			if (pos->_data == target) return true;
			pos = pos->_next;
		}
		return false; // Not found
	}

	// Erase an element in the linked list (not based on an iterator)
	bool remove(const T& target) {
		if (isEmpty()) return false;

		Node* pos = _head->_next;
		Node* prev = _head;
		while (pos != _tail) {
			if (pos->_data == target) {
				prev->_next = pos->_next;
				delete pos;
				_size--;
				return true;
			}
			prev = pos;
			pos = pos->_next;
		}
		return false; // Not found
	}

	// Returns the 1-based position where an object is on this queue.
	// If the object target occurs as an item in this queue, this function
	// returns the distance from the top of the queue of the occurrence
	// nearest the top of the queue; the topmost item on the queue is
	// considered to be at distance 1. The overloaded == function is used to
	// compare target to the items in this queue.
	int search(const T& target = T{}) const {
		Node* pos = _head->_next;
		int dist = 1;
		while (pos != _tail) {
			if (pos->_data == target) return dist;
			pos = pos->_next;
			dist++;
		}
		return -1; // Not found
	}

	// Delete everything from the linked list
	void clear() {
		if (isEmpty()) return;

		Node* pos = _head->_next;
		while (pos != _tail) {
			Node* next = pos->_next;
			delete pos;
			pos = next;
		}
		_size = 0;
		_head->_next = _tail;
	}


	//
	// Internal consistency check
	//
public:
	virtual bool check() const
	{
		bool sizeConsistent = isSizeConsistent();
		bool headTailConsistent = isEndConsistent();

		if (!sizeConsistent) std::cerr << "Size inconsistent" << std::endl;
		if (!headTailConsistent) std::cerr << "Head / Tail inconsistent" << std::endl;

		return sizeConsistent && headTailConsistent;
	}

	//
	// Stated size is accurate to the entire list
	//
	bool isSizeConsistent() const
	{
		int count = 0;
		for (Node* current = _head->_next; current != _tail; current = current->_next)
		{
			count++;
		}

		return size() == count;
	}

	//
	// Checks that the head and tail are defaulted properly and the
	// respective next pointers are appropriate.
	//
	bool isEndConsistent() const
	{
		if (_head->_data != T{}) return false;

		if (_tail->_data != T{}) return false;

		if (_head->_next == nullptr) return false;

		if (_tail->_next != nullptr) return false;

		if (isEmpty() && _head->_next != _tail) return false;

		if (!isEmpty() && _head->_next == _tail) return false;

		return true;
	}
};

#endif