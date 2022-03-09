package adt;
/**
 * Set subclass of bag
 * Only unique values allowed
 * @author Boone Tison
 * Date: 10/10/2019
 */

import bag.Bag;

public class Set<T> extends Bag<T>{
	
	public Set() {
		super();
	}
	
	/**
	 * Only adds item if
	 * the set does not
	 * contain it already
	 */
	@Override
	public void add(T item) {
		if (!contains(item)) 
			super.add(item);
	}
	
	/**
	 * No use for frequency in set
	 * Throws exception when called
	 */
	@Override
	public int getFrequencyOf(T item) {
		throw new UnsupportedOperationException();
	}
}
