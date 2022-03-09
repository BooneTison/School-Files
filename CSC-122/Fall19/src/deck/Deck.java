package deck;
/**
 * Creates a deck of cards
 * @author Boone Tison
 * Date: 09/19/2019
 */
import card.Card;
import card.Card.CardSuit;

public class Deck {
	public final int DECK_SIZE = 52;
	private int topOfDeck = DECK_SIZE-1;
	Card placeholder = new Card(1,CardSuit.Spades);
	Card[] myDeck = new Card[DECK_SIZE];
	
	/**
	 * Deck constructor
	 * Creates standard deck
	 */
	public Deck() {
		int count = 0;
		for (CardSuit suit: CardSuit.values( )) {
			for (int j = 1; j <= DECK_SIZE/13; j++) {
				myDeck[count] = new Card(j,suit);
				count++;
			}
		}
	}
	
	/**
	 * Shuffles the cards
	 * 100 times
	 */
	public void shuffle() {
		for (int i = 0; i < 100; i++) {
			int first = (int)(Math.random() * 52);
			int second = (int)(Math.random() * 52);
			placeholder = myDeck[first];
			myDeck[first] = myDeck[second];
			myDeck[second] = placeholder;
		}
	}
	
	/**
	 * Deals the top card
	 * @return
	 */
	public Card deal() {
		Card c = myDeck[topOfDeck];
		myDeck[topOfDeck] = null;
		topOfDeck -=1;
		return c;
	}
	
	/**
	 * Returns a string
	 * of the deck
	 * uses card to string for 
	 * each card
	 * @return
	 */
	public String toString() {
		String str = "";
		int count = 1;
		for (Card c: myDeck) {
			str += c.toString() + ", ";
			count++;
			if (count % 13 == 0) 
				str += "\n";
		}	
		return str;
	}
	
	/**
	 * Returns the top card
	 * @return
	 */
	public Card getTopCard() {
		if (topOfDeck == -1)
			return null;
		else
			return myDeck[topOfDeck];
	}
	

}

