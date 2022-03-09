package adt;
/**
 * Used for pop and peek methods
 * of stack class on an
 * empty stack
 * @author Boone Tison
 * Date: 10/07/19
 */

public class IllegalOperationOnStackException extends Exception {
	
	private static final long serialVersionUID = 4979149563313310284L;

	public IllegalOperationOnStackException() {
		super("Can't perform this action an empty stack.");
	}
}
