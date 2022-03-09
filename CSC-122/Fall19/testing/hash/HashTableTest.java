package hash;

import static org.junit.Assert.*;

import org.junit.Test;

import card.Card;

public class HashTableTest {
	
	@Test
	public void testBasics() {
		HashTable<Card> h = new HashTable<Card>();
		assertTrue(h.getLength() == 31);
		assertTrue(h.getSize() == 0);
	}
}
