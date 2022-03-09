package maze;

import static org.junit.Assert.*;

import org.junit.Test;

public class MazeTest {
	
	@Test
	public void testBasics() {
		MazeRunner maze2 = new MazeRunner("maze2.txt");
		assertTrue("10 rows", maze2.getRowCount() == 10);
		assertTrue("10 columns", maze2.getColumnCount() == 10);
		System.out.println(maze2.toString());
		Coordinate a = new Coordinate(0,3);
		Coordinate b = new Coordinate(9,9);
		assertTrue("Start", maze2.getStart().equals(a));
		assertTrue("end", maze2.getEnd().equals(b));
		
		Coordinate c = new Coordinate(0,0);
		assertTrue("can visit 0,0", maze2.visitable(c));
		
		Coordinate d = new Coordinate(1,1);
		assertFalse("can't visit 1,1", maze2.visitable(d));
	}
	
	@Test
	public void testMarkAsVisited() {
		MazeRunner maze2 = new MazeRunner("maze2.txt");
		Coordinate c = new Coordinate(0,0);
		maze2.markAsVisited(c);
		assertFalse("marked now", maze2.visitable(c));
	}
	
	@Test
	public void testAtEnd() {
		MazeRunner maze2 = new MazeRunner("maze2.txt");
		Coordinate c = new Coordinate(9,9);
		assertTrue("at end", maze2.atEnd(c));
		Coordinate d = new Coordinate(9,10);
		assertFalse("not at end", maze2.atEnd(d));
	}
	
	
}
