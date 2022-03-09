package adt;
/**
 * Generic linked list.
 * Implements interface List<E>
 * @author Boone Tison
 * Date: 10/17/2019
 * @param <E>
 */

public class LinkedList<E> implements List<E> {
	
	/**
	 * Node class used 
	 * for data record
	 * @author Boone Tison
	 * Date: 10/17/2019
	 */
	private class Node {
		E data; // stored data
		Node next; // link to next node
		
		// single constructor to create a node
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private int numberOfEntries; // count of nodes in list
	private Node start; // first node in list
	
	/**
	 * Default constructor
	 * Sets number of entries to 0
	 * and start to null
	 */
	public LinkedList() {
		numberOfEntries = 0;
		start = null;
	}
	
	/**
	 * Add item to 
	 * beginning list
	 */
	@Override
	public void add(E item) {
		numberOfEntries++;
		Node temp = new Node(item,start);
		start = temp;
	}
	
	/**
	 * Add item to list
	 * at specific position
	 */
	@Override
	public void add(int pos, E item) {		
		if (pos <= 0 || isEmpty()) {
			add(item); // number of entries is increased in this method
		}
		else {
			Node before = start;
			int i = 0;
			while (i < pos && i < numberOfEntries-1) {
				before = before.next;
				i++;
			}
			Node temp = new Node(item,before.next);
			before.next = temp;
			numberOfEntries++;
		}
	}
	
	/**
	 * Returns boolean
	 * if contains item
	 */
	@Override
	public boolean contains(E item) {
		return false;
	}
	
	/**
	 * Returns size of list
	 */
	@Override
	public int size() {
		return numberOfEntries;
	}
	
	/**
	 * Returns boolean if
	 * list is empty
	 */
	@Override
	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}
	
	/**
	 * Returns object
	 * at position
	 */
	@Override
	public E get(int pos) {
		return null;
	}
	
	/**
	 * Returns object at
	 * position and removes
	 * the object from list
	 */
	@Override
	public E remove(int pos) {
		return null;
	}
	
	/**
	 * Returns string of list
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = start;
		while (current != null) {
			sb.append(current.data.toString() + ",");
			current = current.next;
		}
		return sb.toString();
	}

}
