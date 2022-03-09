package adt;
/**
 * Generic queue
 * First in, first out
 * @author Boone Tison
 * Date: 10/20/2019
 * @param <T>
 */

public class Queue<T> {
	private T[] myArray;
	private int head; // beginning of queue
	private int tail; // end of queue
	private int count;
	private final int DEFAULT_SIZE = 10;
	
	/**
	 * Default constructor
	 * Make a DEFAULT_SIZE item queue
	 */
	@SuppressWarnings("unchecked")
	public Queue() {
		myArray = (T[]) (new Object[DEFAULT_SIZE]);
		head = 0; tail = 0;
		count = 0;
	}
	
	/**
	 * Adds item to tail of queue
	 * @param item
	 * @throws ContainerFullException
	 */
	public void enqueue(T item) throws ContainerFullException {
		if (getSize() == myArray.length) 
			throw new ContainerFullException();
		myArray[tail] = item;
		tail = (tail + 1) % myArray.length;
		count++;
	}
	
	/**
	 * 
	 * @return item from head of queue
	 * @throws ContainerEmptyException
	 */
	public T dequeue() throws ContainerEmptyException{
		if (isEmpty()) 
			throw new ContainerEmptyException();
		T pc = myArray[head];
		myArray[head] = null;
		head = (head + 1) % myArray.length;
		count--;
		return pc;
	}
	
	/**
	 * @return if queue is empty
	 */
	public boolean isEmpty() {
		return (count == 0);
	}
	
	/**
	 * @return count of queue
	 */
	public int getSize() {
		return count;
	}
	
	/**
	 * @return item at head of queue
	 */
	public T peek() {
		return myArray[head];
	}
	
	/**
	 * Creates string of queue
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("Queue: ");
		for (int i=0; i < getSize(); i++) {
			int index = (head+i) % myArray.length;
			s.append(myArray[index]);
		}
		return s.toString();
	}
}
