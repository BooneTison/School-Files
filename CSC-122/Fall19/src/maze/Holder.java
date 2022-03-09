package maze;

public interface Holder<T> {
	public boolean hasMore();
	public T getNext() throws IllegalStateException;
	public void add(T item);
}
