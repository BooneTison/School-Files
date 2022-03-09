package adt;

public class ContainerFullException extends Exception {

	private static final long serialVersionUID = 2803407545647306642L;
	
	public ContainerFullException() {
		super("Can't add to a full container");
	}

}
