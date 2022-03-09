package adt;
/**
 * Generic list interface with basic methods
 * @author Boone Tison
 * Date: 10/17/2019
 * @param <E>
 */

public interface List<E> {
	public void add(E item);
	public void add(int pos, E item);
	public boolean contains(E item);
	public int size();
	public boolean isEmpty();
	public E get(int pos);
	public E remove(int pos);
}