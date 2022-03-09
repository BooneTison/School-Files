package card;

import hash.HashTable;

/**
 * Basic playing card class 
 * @author Boone Tison      
 * Date: 9/19/2019
 */

public class Card {
public enum CardSuit{Spades, Hearts, Clubs, Diamonds};
	
	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	
	private final CardSuit suit;
	private final int rank;
	
	/**
	 * Basic constructor
	 * Checks for valid values
	 * @param rank
	 * @param suit
	 */
	public Card (int rank, CardSuit suit) {
		if (rank < ACE || rank > KING) {
			rank = 2;
		}
		if (suit.ordinal() < 0 || suit.ordinal() > 3) {
			suit = CardSuit.Spades;
		}
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * Returns rank
	 * @return
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Returns value
	 * @return
	 */
	public int getValue() {
		return rank;
	}
	
	/**
	 * Returns suit
	 * @return
	 */
	public CardSuit getSuit() {
		return suit;
	}
	
	/**
	 * Checks if cards
	 * are equal
	 */
	public boolean equals(Object obj) {
		//null instanceof Object will always return false
		 	if (!(obj instanceof Card))
		 		return false;
		 	if (obj == this) 	//always equal to self
		 		return true;
		 	return this.suit == ((Card) obj).suit
		 		&& this.rank == ((Card) obj).rank;
	   }
	
	/**
	 * Convert card into
	 * String 
	 */
	public String toString() {
		return rankAsString() + suitAsString();
	}
	
	/**
	 * Returns rank as String
	 * Number as normal
	 * 1st letter for face cards
	 * @return
	 */
	public String rankAsString() {
	      if (rank == ACE) {
	    	  return "A";
	      }
	      else if (rank == JACK) {
	    	  return "J";
	      }
	      else if (rank == QUEEN) {
	    	  return "Q";
	      }
	      else if (rank == KING) {
	    	  return "K";
	      }
	      else {
	    	  return Integer.toString(rank);  //return integer as string if 2-10
	      }
	   }
	
	/**
	 * Returns suit as string
	 * using unicode symbols
	 * @return
	 */
	public String suitAsString() {
		 switch ( suit ) {
         case Spades:   return "\u2660"; // actual visual representations
         case Hearts:   return "\u2665";
         case Diamonds: return "\u2666";
         case Clubs:    return "\u2663";
      }
      return null;  //should never get here
	}
	

	public int getHashcode() {
		return 0;
	}
}
