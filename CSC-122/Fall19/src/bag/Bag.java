package bag;
/**
 * Generic bag class
 * Has methods to add and remove
 * items and look in the bag
 * @author Boone Tison
 * Date: 10/03/2019
 * @param <T> generic
 */

public class Bag<T> {
	private T[] myArray;
	private int count; 
	private final int DEFAULT_SIZE = 10;

	/**
	 * Bag constructor creates array
	 * of default size and sets
	 * top to 0
	 */
	public Bag() {
		clear();
	}

	/**
	 * @return Number of objects in the bag
	 */
	public int getCurrentSize() {
		return count;
	}

	/**
	 * @return boolean if empty or not
	 */
	public boolean isEmpty() {
		return (count == 0);
	}

	/**
	 * Adds an item to top of array
	 * If array is full, doubles size
	 * and then adds item
	 * @param item
	 */
	@SuppressWarnings("unchecked")
	public void add(T item) {
		int i = 0;
		//find the first null item
		while (i < myArray.length && myArray[i] != null) {
			i++;
		}
		//all items are filled
		if (i >= myArray.length) {
			T[] newArr = (T[]) (new Object[myArray.length * 2]);	
			for (int k = 0; k < myArray.length; k++)
				newArr[k] = myArray[k];
			myArray = newArr;
		}
		myArray[i] = item;
		count++;
	}

	/**
	 * Removes first object found
	 * @return object
	 */
	public T remove() {
		int i = 0;
		while (i < myArray.length && myArray[i] == null)
			i++;
		T temp = null;
		if (i < myArray.length) {
			temp = myArray[i];
			myArray[i] = null;
			count--;
		}
		return temp;
	}

	/**
	 * Removes first instance 
	 * of item given
	 * Returns boolean if found or not
	 * @param item
	 * @return boolean
	 */
	public boolean remove(T item) {
		int i = 0;
		while (i < myArray.length && (myArray[i] == null || !myArray[i].equals(item))) 
			i++;
		if (i < myArray.length && myArray[i].equals(item)) {
			myArray[i] = null;
			count--;
			return true;
		}
		return false;
	}

	/**
	 * Makes array empty
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		myArray = (T[]) (new Object[DEFAULT_SIZE]);
		count = 0;
	}

	/**
	 * Number of items found
	 * @param item
	 * @return integer
	 */
	public int getFrequencyOf(T item) {
		int num = 0;
		for (int i = 0; i < myArray.length; i++) {
			if (myArray[i] != null && myArray[i].equals(item))
				num++;
		}
		return num;
	}

	/**
	 * Boolean if finds item
	 * @param item
	 * @return boolean
	 */
	public boolean contains(T item) {
		for (int i = 0; i < myArray.length; i++) {
			if (myArray[i] != null && myArray[i].equals(item))
				return true;
		}
		return false;
	}

	/**
	 * Percent of items in array
	 * @return double
	 */
	public double percentFull() {
		return (double)count / myArray.length;
	}
}