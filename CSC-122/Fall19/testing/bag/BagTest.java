package bag;

import static org.junit.Assert.*;

import org.junit.Test;

import card.Card;
import card.Card.CardSuit;

public class BagTest {
	@Test
	public void testBasics() {
		Bag<Card> bc = new Bag<Card>();
		assertTrue ("Empty Bag", bc.isEmpty());
		assertTrue ("Count Empty Bag", bc.getCurrentSize()==0);
	}
	
	@Test
	public void testAdd() {
		Bag<Card> bc = new Bag<Card>();
		Card ace = new Card(Card.ACE,CardSuit.Hearts);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==0);
		assertFalse ("Contains item", bc.contains(ace));
		bc.add(ace);
		
		assertFalse ("Not empty Bag", bc.isEmpty());
		assertTrue ("Count single Bag", bc.getCurrentSize()==1);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==1);
		assertTrue ("Contains item", bc.contains(ace));
	}
	
	@Test
	public void testRemoveItem() {
		Bag<Card> bc = new Bag<Card>();
		Card ace = new Card(Card.ACE,CardSuit.Hearts);
		Card ace2 = new Card(Card.ACE,CardSuit.Hearts);
		Card two = new Card(2,CardSuit.Hearts);
		bc.add(ace);
		bc.add(two);
		bc.add(ace2);
		assertTrue ("Count single Bag", bc.getCurrentSize()==3);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==2);
		assertTrue ("Find single item", bc.getFrequencyOf(two)==1);
		assertTrue ("Contains item", bc.contains(ace));
		assertTrue ("Contains item", bc.contains(two));
		
		bc.remove(ace);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==1);
		assertTrue ("Contains item", bc.contains(ace));
		bc.remove(ace);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==0);
		assertFalse ("No longer contains item", bc.contains(ace));
		
		assertTrue ("Count single Bag", bc.getCurrentSize()==1);
		bc.remove(ace);
		assertTrue ("Count single Bag", bc.getCurrentSize()==1);
		
		bc.remove(two);
		assertTrue ("Count single Bag", bc.getCurrentSize()==0);
		assertTrue ("Find single item", bc.getFrequencyOf(two)==0);
		assertFalse ("No longer contains item", bc.contains(two));
		
		assertFalse ("No longer  item", bc.contains(ace));
		assertFalse ("No longer  item", bc.contains(two));
	}
	
	@Test
	public void testSpecialRemove() {
		Bag<Card> bc = new Bag<Card>();
		Card ace = new Card(Card.ACE,CardSuit.Hearts);
		Card two = new Card(2,CardSuit.Hearts);

		bc.add(ace);
		bc.add(ace);
		bc.add(two);
		bc.add(ace);
		bc.add(ace);
		bc.remove(two);
		assertTrue ("Count 4 Bag", bc.getCurrentSize()==4);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==4);
		assertFalse ("No longer contains item", bc.contains(two));
		
		bc.clear();
		assertTrue ("Empty again",bc.isEmpty());
	}
	
	@Test
	public void testOverfull() {
		Bag<Card> bc = new Bag<Card>();
		Card ace = new Card(Card.ACE,CardSuit.Hearts);
		Card two = new Card(2,CardSuit.Hearts);
		for (int i=0; i<10; i++) {
			bc.add(ace);
			bc.add(two);
		}
		assertTrue ("Count big Bag", bc.getCurrentSize()==20);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==10);
		assertTrue ("Find single item", bc.getFrequencyOf(two)==10);
	
		int i = 0;
		while (bc.remove(two)){
			i++;
		}
		assertTrue ("remove all twos",i==10);
		assertTrue ("Find single item", bc.getFrequencyOf(ace)==10);
		assertTrue ("Find single item", bc.getFrequencyOf(two)==0);	
		assertFalse ("Find single item", bc.contains(two));	
		
		bc.add(two);
		assertTrue ("Find single item", bc.getFrequencyOf(two)==1);	
		assertTrue ("Find single item", bc.contains(two));	
	}
	
	@Test
	public void testPercentFull() {
		Bag<Card> bc = new Bag<Card>();
		Card ace = new Card(Card.ACE,CardSuit.Hearts);
		assertTrue ("0/10", bc.percentFull() == 0);
		bc.add(ace);
		assertTrue ("1/10", bc.percentFull() == .1);
	}
	
	@Test
	public void testRemoveRandom() {
		Bag<Card> bc = new Bag<Card>();
		Card ace = new Card(Card.ACE,CardSuit.Hearts);
		Card two = new Card(2,CardSuit.Hearts);
		bc.add(ace);
		bc.add(two);
		bc.add(ace);
		assertTrue ("Remove top", bc.remove().equals(ace));
		assertTrue ("Remove top", bc.remove().equals(two));
		assertTrue ("Remove top", bc.remove().equals(ace));
		assertTrue ("Remove top", bc.remove() == null);
		
	}

	
}
