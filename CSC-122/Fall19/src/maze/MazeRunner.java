package maze;
/**
 * Given a input file of a maze
 * Solve it
 * @author Boone Tison
 * Date: 10/31/2019
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import adt.Stack;
import maze.GridPoint;
import maze.GridPoint.GridType;

public class MazeRunner {
	private static int numRows;
	private static int numColumns;
	private GridPoint[][] grid;
	private static Coordinate startLocation;
	private static Coordinate endLocation;
	private static Stack<Coordinate> unexplored;
	
	/**
	 * Default constructor 
	 * @param fname
	 */
	public MazeRunner (String fname) {
		try {
			initialize(fname);
		} catch (Exception ex) {
			System.err.println("Could not create a Maze\n" + ex);
			System.exit(0);
		}
	}
	
	/**
	 * Reads through file and assigns
	 * types to coordinates listed
	 * @param fname
	 * @throws FileNotFoundException
	 */
	public void initialize(String fname) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fname));
		numRows = sc.nextInt();
		numColumns = sc.nextInt();
		grid = new GridPoint[numRows][numColumns];
		for (int i = 0; i < numRows; i++) { // assigns baseline values as blank
			for (int j = 0; j < numColumns; j++) {
				grid[i][j] = new GridPoint('B',1.0);
			}
		}
		
		while (sc.hasNext()) {
			int row = sc.nextInt();
			int column = sc.nextInt();
			char type = sc.next().charAt(0);
			double cost = 0; // default cost if not set
			if (type == 'B')
				cost = sc.nextDouble();
			grid[row][column] = new GridPoint(type,cost);
			if (type == 'S') 
				startLocation = new Coordinate(row,column);
			else if (type == 'E') 
				endLocation = new Coordinate(row,column);
		}
		sc.close();
	}
	
	
	
	/**
	 * @return String of entire maze 
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				s += grid[j][i].toString();
			}
			s += "\n";
		}
		return s;
	}
	
	/**
	 * @param c
	 * @return boolean of if coordinate in maze
	 * is wall and been visited before
	 */
	public boolean visitable(Coordinate c) {
		GridPoint pc = grid[c.ROW][c.COLUMN];
		if (pc.getTileType() != GridType.Wall && pc.hasVisited() == false)
			return true;
		return false;
	}
	
	/**
	 * Mark the grid point as visited
	 * Prints out each time for debugging
	 * @param c
	 */
	public void markAsVisited(Coordinate c) {
		grid[c.ROW][c.COLUMN].visit();
		//System.out.println(grid[c.ROW][c.COLUMN].toString() + " at " + c.ROW +
			//	"," + c.COLUMN + " visited");
	}
	
	/**
	 * @param c
	 * @return boolean if at end of maze
	 */
	public boolean atEnd(Coordinate c) {
		if (c.equals(endLocation))
			return true;
		return false;
	}
	
	/**
	 * @return number of rows
	 */
	public int getRowCount() {
		return numRows;
	}
	
	/**
	 * @return number of columns
	 */
	public int getColumnCount() {
		return numColumns;
	}
	
	/**
	 * @return coordinate of start
	 */
	public Coordinate getStart() {
		return startLocation;
	}
	
	/**
	 * @return coordinate of end
	 */
	public Coordinate getEnd() {
		return endLocation;
	}
	
	/**
	 * Checks if north, east, south, and west
	 * of coordinate can be visited and adds
	 * those to unexplored stack
	 * @param c
	 */
	public void addOptionsFromHere(Coordinate c) {
		Coordinate pc;
		pc = new Coordinate(c.ROW,c.COLUMN+1);
		if (isLegal(pc) && visitable(pc)) 
			unexplored.add(pc);
		
		pc = new Coordinate(c.ROW+1,c.COLUMN);
		if (isLegal(pc) && visitable(pc)) 
			unexplored.add(pc);
		
		pc = new Coordinate(c.ROW,c.COLUMN-1);
		if (isLegal(pc) && visitable(pc)) 
			unexplored.add(pc);
		
		pc = new Coordinate(c.ROW-1,c.COLUMN);
		if (isLegal(pc) && visitable(pc)) 
			unexplored.add(pc);
	}
	
	/**
	 * @param c
	 * @return boolean if coordinate is inside the index
	 * of the grid
	 */
	public boolean isLegal(Coordinate c) {
		if (c.ROW < 0 || c.COLUMN < 0)
			return false;
		else if (c.ROW >= numRows || c.COLUMN >= numColumns)
			return false;
		return true;
	}
	
	public GridPoint[][] getGrid() {
		return grid;
	}
	
	/**
	 * Runs the maze in entirety
	 * Finds the end from start
	 * @param args
	 */
	public static void main(String[] args) {
		MazeRunner maze2 = new MazeRunner("maze2.txt");
		Coordinate currentPos = startLocation;
		unexplored = new Stack<Coordinate>();
		System.out.println(maze2.toString());
		maze2.markAsVisited(currentPos);
		maze2.addOptionsFromHere(currentPos);
		
		while (unexplored.hasMore() && !maze2.atEnd(currentPos)) {
			currentPos = unexplored.getNext();
			maze2.markAsVisited(currentPos);
			maze2.addOptionsFromHere(currentPos);
		}
		if (maze2.atEnd(currentPos))
			System.out.println("Congrats on solving the maze");
		else
			System.out.println("Maze is not solvable");
	}
}


