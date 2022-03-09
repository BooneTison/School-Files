package maze;

import java.lang.reflect.Array;
import java.lang.Comparable;

/**
 * 
 * @author nickrobins
 *
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> implements Holder<T> {
	private T[] heap;
	private int size;
	public static final int HEAP_SIZE = 110;
	@SuppressWarnings("unchecked")
	public MinHeap() {
		heap = (T[]) new Comparable[(HEAP_SIZE)];
		size = 0;
	}
	@Override
	public void add(T item) {
		heap[size + 1] = item;
		size++;
		int loc = size;
		while (loc > 1 && heap[loc / 2].compareTo(heap[loc]) > 0) {
			T temp = heap[loc / 2];
			heap[loc / 2] = heap[loc];
			heap[loc] = temp;
			loc /= 2;
		}
		if (size + 1 == heap.length) {
			//TODO: resize
		}
	}
	public T remove() {
		T nextItem = heap[1];
		heap[1] = heap[size];
		heap[size] = null;
		size--;
		percolateDown(1);
		return nextItem;
	}
	public T peek() {
		return heap[1];
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 1; i < size + 1; i++) {
			s.append(heap[i].toString() + " ");
		}
		return s.toString();
	}
	private void percolateDown(int n) {
		if (n * 2  < heap.length && heap[n * 2] != null) {
			int x;
			if (heap[n * 2 + 1] == null 
					|| heap[n * 2].compareTo(heap[n * 2 + 1]) <= 0)
				x = n * 2;
			else
				x = n * 2 + 1;
			if (heap[n].compareTo(heap[x]) > 0) {
				T temp = heap[n];
				heap[n] = heap[x];
				heap[x] = temp;
				percolateDown(x);
			}
		}
	}

	@Override
	public boolean hasMore() {
		return !this.isEmpty();
	}

	@Override
	public T getNext() throws IllegalStateException {
		return this.remove();
	}



}

