package adt;

import static org.junit.Assert.*;
import org.junit.Test;
import card.Card;
import card.Card.CardSuit;

public class SetTest {
	
	@Test
	public void testBasics() {
		Set<Card> bc = new Set<Card>();
		assertTrue ("Empty Bag", bc.isEmpty());
		assertTrue ("Count Empty Bag", bc.getCurrentSize()==0);
	}
	
	@Test
	public void testAdd() {
		Set<Card> bc = new Set<Card>();
		Card ace = new Card(Card.ACE,CardSuit.Hearts);
		Card two = new Card(2,CardSuit.Hearts);
		Card three = new Card(3,CardSuit.Hearts);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==0);
		assertFalse ("Contains item", bc.contains(ace));
		bc.add(ace);
		
		assertFalse ("Not empty Bag", bc.isEmpty());
		assertTrue ("Count single Bag", bc.getCurrentSize()==1);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==1);
		assertTrue ("Contains item", bc.contains(ace));
		
		bc.add(ace);
		bc.add(ace);
		bc.add(two);
		bc.add(three);
		bc.add(two);
		bc.add(three);
		assertTrue ("Count single Bag", bc.getCurrentSize()==3);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==1);
		assertTrue ("Contains item", bc.contains(ace));
		assertTrue ("Find single item", bc.getFrequencyOf(two)==1);
		assertTrue ("Contains item", bc.contains(two));
		assertTrue ("Find single item", bc.getFrequencyOf(three)==1);
		assertTrue ("Contains item", bc.contains(three));
	}
	
	
}
