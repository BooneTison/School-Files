package adt;

import static org.junit.Assert.*;
import org.junit.Test;

public class LinkedListTest {
	
	@Test
	public void testBasics() {
		LinkedList<Integer> llist = new LinkedList<Integer>();
		int x = llist.size();
		assertTrue("basic empty size", x==0);
		assertTrue("is empty", llist.isEmpty());
	}
	
	@Test
	public void testAdd() {
		LinkedList<Integer> llist = new LinkedList<Integer>();
		llist.add(5);
		int x = llist.size();
		assertTrue("basic add item", x==1);
		assertFalse("is empty", llist.isEmpty());
	}
	
	@Test
	public void testToString() {
		LinkedList<Integer> llist = new LinkedList<Integer>();
		llist.add(5);
		llist.add(6);
		llist.add(7);
		String s = llist.toString();
		assertTrue("to string", s.equals("7,6,5,"));
	}
	
	@Test
	public void testAddAtPosition() {
		LinkedList<Integer> llist = new LinkedList<Integer>();
		llist.add(1,5); // empty
		llist.add(-1,6); // negative
		llist.add(5,7); // more than number of entries
		llist.add(1,8); // add in middle
		assertTrue("size of 4", llist.size() == 4);
		String s = llist.toString();
		assertTrue("to string", s.equals("6,5,8,7,"));
	}
}
