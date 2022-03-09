package adt;
/**
 * Testing the stack class
 * @author Boone Tison
 * Date: September 26, 2019
 */

import static org.junit.Assert.*;
import card.Card;
import org.junit.Test;

public class StackTest {
	
	@Test
	public void testConstructor() {
		Stack<String> def = new Stack<String>();
		assertTrue("Empty stack", def.isEmpty() == true);
		Stack<Card> def2 = new Stack<Card>();
		assertTrue("Empty stack", def2.isEmpty() == true);
	}
	
	@Test
	public void testPush() throws IllegalOperationOnStackException{
		Stack<String> def = new Stack<String>();
		def.push("Apple");
		assertTrue("Top moved, is now apple", def.peek() == "Apple");
		assertTrue("Size is 1", def.getSize() == 1);
	}
	
	@Test 
	public void testPeek() throws IllegalOperationOnStackException {
		Stack<String> def = new Stack<String>();
		for (int i = 0; i < 10; i++) 
			def.push("Apple");
		assertTrue("Full of apples", def.peek() == "Apple");
	}

	@Test
	public void testPop() throws IllegalOperationOnStackException{
		Stack<String> def = new Stack<String>();
		for (int i = 0; i < 10; i++) 
			def.push("Apple");
		assertTrue("Top is apple", def.pop() == "Apple");
		assertTrue("Size is one less", def.getSize() == 9);
	}
	
	@Test
	public void testGetSize() {
		Stack<String> def = new Stack<String>();
		for (int i = 0; i < 5; i++) 
			def.push("Apple");
		assertTrue("Size is 5", def.getSize() == 5);
	}
	
	@Test 
	public void testIsEmpty() throws IllegalOperationOnStackException {
		Stack<String> def = new Stack<String>();
		assertTrue("Empty stack", def.isEmpty() == true);
		def.push("A");
		assertTrue("Empty after string added", def.isEmpty() == false);
		def.pop();
		assertTrue("Empty after string removed", def.isEmpty() == true);
	}
	
	@Test
	public void testToString() {
		Stack<String> def = new Stack<String>(3);
		def.push("a");
		def.push("b");
		String test = def.toString();
		assertTrue("To String", test.equals("a, b, "));
	}
	
	@Test
	public void testResizeStack() throws IllegalOperationOnStackException {
		Stack<String> def = new Stack<String>(1);
		def.push("a");
		def.push("b");
		assertTrue("Resized", def.peek().equals("b"));
		assertTrue("Resized", def.getSize() == 2);
	}
	
	@Test (expected = IllegalOperationOnStackException.class)
	public void testException() throws IllegalOperationOnStackException {
		Stack<String> def = new Stack<String>();
		def.peek();
		def.pop();
	}
}
