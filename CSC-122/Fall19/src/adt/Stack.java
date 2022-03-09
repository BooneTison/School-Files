package adt;

import maze.Holder;

/**
 * Generic stack class
 * @author Boone Tison
 * Date: 10/07/19
 */

public class Stack<T> implements Holder<T> {
	private T[] myStack;
	private int top = 0; 
	private static final int DEFAULT_SIZE = 10;
	
	/**
	 * Default constructor
	 */
	public Stack() {
		this (DEFAULT_SIZE);
	}
	
	/**
	 * If n is an invalid value for 
	 * the size of the stack,
	 * it is set to the default size.
	 * Otherwise, creates a stack with
	 * a size of n.
	 * @param n
	 */
	@SuppressWarnings("unchecked")
	public Stack(int n) {
		if (n < 1) 
			myStack = (T[]) (new Object[DEFAULT_SIZE]);
		else 
			myStack = (T[]) (new Object[n]);
	}
	
	/**
	 * Puts item on top of the stack
	 * and moves the top up one
	 * If the stack is full,
	 * the stack doubles in size
	 * and the item is added on top
	 * @param item
	 */
	@SuppressWarnings("unchecked")
	public void push(T item) {
		if (top >= myStack.length) {
			T[] newStack = (T[]) (new Object[myStack.length * 2]);	
			for (int i = 0; i < myStack.length; i++)
				newStack[i] = myStack[i];
			myStack = newStack;
			myStack[top] = item;
		}
		else 
			myStack[top] = item;
		top++;
	}
	
	/**
	 * Looks at the top
	 * of the stack
	 * @return object
	 * Throws custom exception
	 * if trying on empty stack
	 */
	public T peek() throws IllegalOperationOnStackException {
		if (top > 0)
			return myStack[top-1];
		else
			throw new IllegalOperationOnStackException();
	}
	
	/**
	 * Removes the top of the
	 * stack and moves the
	 * top down one
	 * @return object
	 * Throws custom exception
	 * if trying on empty stack
	 */
	public T pop() throws IllegalOperationOnStackException {
		if (top > 0) {
			top--;
			T item = myStack[top];
			myStack[top] = null;
			return item;
		}
		else
			throw new IllegalOperationOnStackException();
	}
	
	/**
	 * Returns the number of
	 * objects in the stack
	 * @return integer
	 */
	public int getSize() {
		return top;
	}
	
	/**
	 * Checks if the stack is empty,
	 * if the first value of the stack
	 * is null, then the rest is null.
	 * Since a stack can't be created
	 * without starting at the top,
	 * which starts at 0.
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (myStack[0] == null) 
			return true;
		else
			return false;
	}
	
	/**
	 * Prints out the stack
	 * Separated by commas
	 * @return String
	 */
	public String toString() {
		String end = "";
		for (T item: myStack) 
			if (item != null)
				end += item + ", ";
		return end;
	}

	@Override
	public boolean hasMore() {
		if (myStack[0] == null) 
			return false;
		return true;
	}

	@Override
	public T getNext() throws IllegalStateException {
		try {
			return pop();
		} catch (IllegalOperationOnStackException e) {
			throw new IllegalStateException();
		}
	}
	
	@Override
	public void add (T item) {
		push((T)item);
	}
}
