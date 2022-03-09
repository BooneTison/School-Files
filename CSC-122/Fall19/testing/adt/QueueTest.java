package adt;
/**
 * Test queue class
 * @author Boone Tison
 * Date: 10/20/2019
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class QueueTest {
	
	@Test
	public void testBasics() {
		Queue<String> tester = new Queue<String>();
		assertTrue("Created empty queue", tester.isEmpty());
		assertTrue("no items", tester.getSize() == 0);
	}
	
	@Test
	public void testEnQueue() throws ContainerFullException {
		Queue<String> tester = new Queue<String>();
		tester.enqueue("A");
		tester.enqueue("B");
		tester.enqueue("C");
		assertTrue("Size of 1", tester.getSize() == 3);
		assertFalse("Not empty", tester.isEmpty());
	}
	
	@Test
	public void testDeQueue() throws ContainerFullException, ContainerEmptyException {
		Queue<String> tester = new Queue<String>();
		tester.enqueue("A");
		tester.enqueue("B");
		assertTrue("Remove A", tester.dequeue().equals("A"));
		assertTrue("one item", tester.getSize() == 1);
		assertTrue("Remove B", tester.dequeue().equals("B"));
		assertTrue("Empty now", tester.isEmpty());
		assertTrue("no items", tester.getSize() == 0);
	}
	
	@Test
	public void testPeek() throws ContainerFullException {
		Queue<String> tester = new Queue<String>();
		tester.enqueue("A");
		tester.enqueue("B");
		assertTrue("peek the A", tester.peek().equals("A"));
	}
	
	@Test
	public void testToString() throws ContainerFullException {
		Queue<String> tester = new Queue<String>();
		tester.enqueue("A");
		tester.enqueue("B");
		assertTrue("to string", tester.toString().equals("Queue: AB"));
	}
	
	@Test (expected = ContainerFullException.class)
	public void testFullQueue() throws ContainerFullException {
		Queue<String> tester = new Queue<String>();
		for (int i = 0; i < 100; i++)
			tester.enqueue("A");
	}
	
	@Test (expected = ContainerEmptyException.class)
	public void testEmptyQueue() throws ContainerEmptyException {
		Queue<String> tester = new Queue<String>();
		tester.dequeue();
	}
}
