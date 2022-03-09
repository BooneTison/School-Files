package die;

/**
 * Die class will model a single dice
 * Can be used in a variety of situations
 * requiring multi-sided die.
 * @author Boone Tison, Courtney Brown
 * Sep 5, 2019 CSC 122 Lab 1
 */

public class Die {
	private final int NUMBER_OF_SIDES; // Maximum value that can be rolled by die
	private int faceValue; // Value that is given by die, can be rolled
	public static final int DEFAULT_NUM_SIDES = 6; // Value used if no NUMBER_OF_SIDES given or invalid
	public final int MAX_SIDES = 32; // Maximum number of sides allowed
	public final int MIN_SIDES = 2; // Minimum number of sides allowed
	
	/**
	 * The default die
	 * with six sides
	 */
	public Die() {
		this (DEFAULT_NUM_SIDES);
	}
	
	/**
	 * The user can give the
	 * program the number of sides
	 * @param faces
	 */
	public Die(int faces) {
		// Ensures that the die has a valid number of sides, if not valid: set to the default maximum
		if (faces > MAX_SIDES || faces < MIN_SIDES) {
			faces = DEFAULT_NUM_SIDES;
		}
		NUMBER_OF_SIDES = faces;
		faceValue = 1;
	}
	
	/**
	 * Change the face value
	 */
	public void roll() {
		faceValue = 1 + (int)(Math.random() * NUMBER_OF_SIDES);
	}
	
	/**
	 * Share the face value
	 * @return integer
	 */
	public int getFaceValue() {
		return faceValue;
	}
	
	/**
	 * Share the number of sides
	 * @return integer
	 */
	public int getNumberOfSides() {
		return NUMBER_OF_SIDES;
	}
	
	/**
	 * equals - checks for object equality between two dice
	 */
	public boolean equals(Object obj) {
		//null instanceof Object will always return false
		if (!(obj instanceof Die))
			return false;
		if (obj == this) 	//always equal to self
			return true;
		return this.NUMBER_OF_SIDES == ((Die) obj).NUMBER_OF_SIDES
				&& this.faceValue == ((Die) obj).faceValue;
	}
	
	/**
	 * toString - returns a string representation of the Die
	 */
	public String toString() {
		return "A " + this.NUMBER_OF_SIDES + "-sided die: value " + this.faceValue;
	}
	
	/**
	 * A workaround to ensure that two die that are equal return the same hashcode
	 */
	public int hashCode() {
		return 0;
	}
	
}
