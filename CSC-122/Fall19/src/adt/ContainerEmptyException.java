package adt;

public class ContainerEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public ContainerEmptyException() {
		super("Can't remove from a empty container");
	}
}
