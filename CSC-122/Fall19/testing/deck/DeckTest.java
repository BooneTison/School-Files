package deck;
/**
 * Testing the Deck.java class and its methods
 * @author Boone Tison
 * Date: 09/16/2019
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import card.Card;
import card.Card.CardSuit;


class DeckTest {
	
	/**
	 * Test the constructor
	 * Ensure the last card
	 * is the king of diamonds
	 */
	@Test
	public void testConstructor() {
		Deck d = new Deck();
		assertTrue(d.getTopCard().getSuit() == CardSuit.Diamonds, "Last suit");
		assertTrue(d.getTopCard().getRank() == 13, "Last Value");
	}
	
	/**
	 * Deal cards from top
	 * Make sure cards are being 
	 * dealt in right order
	 */
	@Test
	public void testDeal() {
		Deck d = new Deck();
		assertTrue(d.deal().getSuit() == CardSuit.Diamonds, "52");
		assertTrue(d.deal().getRank() == 13, "51");
		assertTrue(d.deal().getSuit() == CardSuit.Hearts, "50");
		assertTrue(d.deal().getRank() == 13, "49");
		assertTrue(d.deal().getSuit() == CardSuit.Diamonds, "48");
		assertTrue(d.deal().getRank() == 12, "47");
	}
	
	/**
	 * Try and deal
	 * more cards than
	 * there are in deck
	 */
	@Test
	public void testDealTooMany() {
		Deck d = new Deck();
		for (int i = 0; i < 52; i++) {
			d.deal();
		}
		assertTrue(d.getTopCard() == null, "Null Card");
	}
	
	/**
	 * Test the shuffle method
	 * Ensure last card is no longer
	 * King of diamonds
	 */
	@Test
	public void testShuffle() {
		Deck d = new Deck();
		d.shuffle();
		assertTrue(d.getTopCard().getRank() != 13 || d.deal().getRank() != 13);
	}
	
	/**
	 * Make sure no duplicate 
	 * cards in deck
	 */
	@Test
	public void testDuplicate() {
		Deck d = new Deck();
		for (int i=0; i < 52; i++) {
			assertTrue(addCardOK(d,d.deal()));
		}
	}
	
	private Card[] testPile = new Card[52];
	//Utility method inside DeckTest class to check cards for duplicate values
	//then adds the card to the pile of already checked Cards
	//returns true if Card is unique; false if duplicate
	private boolean addCardOK(Deck d, Card c) {
		for (int i=0; i < 52; i++) {
			if (c.equals (testPile[i])) {
				return false; //found duplicate
			}
		}
		testPile[51] = c; //add this card to the pile for checking
		return true;
	}
	
	@Test
	public void testToString() {
		Deck d = new Deck();
		System.out.println(d.toString());
	}
}
