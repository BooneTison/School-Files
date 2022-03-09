package card;
/**
 * Testing the card class and its methods
 * @author Boone Tison, Michael Bowling
 * Date 09/12/2019
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import card.Card.CardSuit;

class CardTest {
	
	/**
	 * Creates an invalid card
	 * tests that card is set to
	 * default values
	 */
	@Test 
	public void testInvalidConstructor() {
		Card c = new Card(15,CardSuit.Spades);
		assertTrue(c.getSuit() == CardSuit.Spades,"Suit Value");
		assertTrue(c.getRank() == 2, "Rank");
	}
	
	/**
	 * creates a valid card
	 * tests that card is set
	 * to correct values
	 */
	@Test
	public void testValidConstructor() {
		Card c = new Card(2,CardSuit.Spades);
		assertTrue(c.getSuit() == CardSuit.Spades,"Suit Value");
		assertTrue(c.getRank() == 2, "Rank");
	}
	
	/**
	 * tests that correct face value
	 * is returned
	 */
	@Test
	public void testGetRank() {
		Card c = new Card(1,CardSuit.Spades);
		assertTrue(c.getRank() == 1, "Rank");
	}
	
	/**
	 * tests that correct suit
	 * is returned
	 */
	@Test
	public void testGetSuit() {
		Card c = new Card(1,CardSuit.Spades);
		assertTrue(c.getSuit() == CardSuit.Spades, "Suit");
	}
	
	/**
	 * tests that suit as 
	 * string returns the correct
	 * unicode symbol
	 */
	@Test
	public void testSuitAsString() {
		Card c = new Card(1,CardSuit.Spades);
		assertTrue(c.suitAsString().equals("\u2660"), "Suit As String");
	}
	
	/**
	 * tests that value as
	 * string returns face cards
	 * correctly and numbers
	 * correctly
	 */
	@Test
	public void testRankAsString() {
		Card c = new Card(1,CardSuit.Spades);
		assertTrue(c.rankAsString().equals("A"), "Face as String");
		Card c2 = new Card(2,CardSuit.Spades);
		assertTrue(c2.rankAsString().equals("2"), "Numebr as String");
	}
	
	/**
	 * tests that two card that are
	 * the same return as equal
	 * tests that two different cards
	 * return as not equal
	 */
	@Test
	public void testEquals() {
		Card c1 = new Card(1,CardSuit.Spades);
		Card c2 = new Card(1,CardSuit.Spades);
		assertTrue(c1.equals(c2) == true, "Equals");
		Card c3 = new Card(1,CardSuit.Spades);
		Card c4 = new Card(2,CardSuit.Spades);
		assertTrue(c3.equals(c4) == false, "Not Equal");
	}
	
	/**
	 * returns card as string
	 * tests unicode symbols,
	 * face cards, and numbers
	 */
	@Test
	public void testToString() {
		Card c = new Card(1,CardSuit.Spades);
		assertTrue(c.toString().equals("A\u2660"), "To String");
		Card c2 = new Card(2,CardSuit.Spades);
		assertTrue(c2.toString().equals("2\u2660"), "To String");
	}
	
	/**
	 * tests that card
	 * returns correct hashcode
	 */
	@Test
	public void testHashCode() {
		Card c = new Card(1,CardSuit.Spades);
		assertTrue(c.getHashcode() == 0, "HashCode");
	}
	
}
