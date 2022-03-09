package die;
/**
 * Testing the die class and its methods
 * @author Boone Tison
 * Date: 09/12/2019
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import card.Card;
import card.Card.CardSuit;

class DieTest {
	
	/**
	 * Tests the default constructor
	 * And some other number of sides
	 */
	@Test
	public void testConstructor() {
		// Default
		Die d1 = new Die();
		assertTrue (d1.getNumberOfSides() == 6, "Make default");
		// Other
		Die d2 = new Die(6);
		assertTrue (d2.getNumberOfSides() == 6, "Make 6 sided");
		Die d3 = new Die(32);
		assertTrue (d3.getNumberOfSides() == 32, "Make 32 sided");
		Die d4 = new Die(2);
		assertTrue (d4.getNumberOfSides() == 2, "Make 2 sided");
	}
	
	/**
	 * Tests the equals method
	 * Tests 2 equal dice,
	 * 2 nonequal dice,
	 * A card and a die,
	 * A die and a null value
	 */
	@Test
	public void testEquals() {
		Die d1 = new Die(6);
		Die d2 = new Die(6);
		assertTrue (d1.equals(d2) == true, "2 Equal Dice");
		Die d3 = new Die(6);
		Die d4 = new Die(7);
		assertTrue (d3.equals(d4) == false, "2 Different Dice");
		Card c = new Card(1,CardSuit.Spades);
		assertTrue (d1.equals(c) == false, "2 Different objects");
		Die other = null;
		assertTrue (d1.equals(other) == false, "Compare dice with null");
	}
	
	/**
	 * Tests an invalid value
	 * that should make die
	 * with default value
	 */
	@Test
	public void testInvalidConstructor() {
		Die d1 = new Die(50);
		assertTrue (d1.getNumberOfSides() == 6, "Make 6 sided");
	}
	
	/**
	 * Tests a coin
	 * that is flipped 100
	 * times for randomness
	 */
	@Test
	public void testRoll() {
		Die d1 = new Die(2);
		double sum1 = 0;
		double sum2 = 0;
		for (int i = 0; i < 100; i++) {
			d1.roll();
			if (d1.getFaceValue() == 1) {
				sum1 += 1;
			}
			else if (d1.getFaceValue() == 2) {
				sum2 += 1;
			}
		}
		assertEquals (sum1, sum2, 20, "Number of 1s and 2s rolled");
	}
	
	/**
	 * Tests a six sided die
	 * that is flipped 1000 times
	 * for randomness
	 */
	@Test
	public void testArray() {
		Die d1 = new Die(6);
		double[] numRoll = new double[6];
		for (int i = 0; i < 1000; i++) {
			d1.roll();
			numRoll[d1.getFaceValue()-1] += 1;
		}
		assertEquals (numRoll[0], numRoll[1], 75, "Randomness");
		assertEquals (numRoll[1], numRoll[2], 75, "Randomness");
		assertEquals (numRoll[2], numRoll[3], 75, "Randomness");
		assertEquals (numRoll[3], numRoll[4], 75, "Randomness");
		assertEquals (numRoll[4], numRoll[5], 75, "Randomness");
	}
	
	/**
	 * Tests the to string method
	 */
	@Test
	public void testToString() {
		Die d1 = new Die();
		assertTrue (d1.toString().equals("A 6-sided die: value 1"), "Test To String");
	}
}
