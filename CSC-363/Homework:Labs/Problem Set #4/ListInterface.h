#ifndef _LIST_INTERFACE_GUARD
#define _LIST_INTERFACE_GUARD 1

#include <iostream>

template <typename T>
class ListInterface
{
    // Returns the first element in the list
    virtual T& first() const = 0;

    // Returns the first element in the list
    virtual T& last() const = 0;

    // Determines if the linked list contains no elements.
    virtual bool isEmpty() const = 0;

    // Returns the number of elements in this container
    virtual unsigned size() const = 0;

    // Adds an item to the left side of the linked list
    virtual void push(const T& x) = 0;

    // Removes and returns an item from the left side of the queue.
    virtual bool pop() = 0;

    // Returns the item at the given index: list[n]
    // @throws a std::string exception if n is out of bounds.
    virtual T& operator[](unsigned n) = 0;

    // Determines if a target value is in the LinkedList; uses == for equality comparison
    virtual bool contains(const T& target) const = 0;

    // Erase an element in the linked list (not based on an iterator)
    virtual bool remove(const T& target) = 0;

    //
    // Returns the 1-based position where an object is on this queue.
    // If the object target occurs as an item in this queue, this function
    // returns the distance from the top of the queue of the occurrence
    // nearest the top of the queue; the topmost item on the queue is
    // considered to be at distance 1. The overloaded == function is used to
    // compare target to the items in this queue.
    //
    virtual int search(const T& target = T{}) const = 0;

    // Delete everything from the linked list
    virtual void clear() = 0;
};

#endif
