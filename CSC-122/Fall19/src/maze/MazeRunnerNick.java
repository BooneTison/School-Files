package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import maze.MinHeap;

import maze.GridPoint.GridType;

/**
 * MazeRunner class reads file input describing a maze and solves it.
 * @author nick robins
 *
 */
public class MazeRunnerNick {
	public class Option implements Comparable<Option> {
		private Coordinate c;
		private double cost;
		private Option via;
		Option(Coordinate c, Option via) {
			this.c = c;
			if (via != null)
				this.cost = c.cost + via.cost;
			this.via = via;
		}

		@Override
		public int compareTo(Option o) {
			return (int) (this.cost - o.cost);
		}
		public String toString() {
			return this.c.toString();
		}
		
		public Option getVia(){
			return via;
		}
		
		public Coordinate getCoordinate() {
			return c;
		}
	}
	private GridPoint[][] grid;
	private int rows;
	private int columns;
	private MinHeap<Option> options;
	private Coordinate startLocation;
	private Coordinate endLocation;
	private Option endOption;
	/**
	 * Constructor of a MazeRunner object
	 * Initializes the maze
	 * @param String fname
	 */
	public MazeRunnerNick (String fname) {
		try {
			initialize(fname);
		} catch (Exception ex) {
			System.err.println("Could not create a Maze\n"+ex);
			System.exit(0);
		}
	}
	/**
	 * Reads an input file to create a maze
	 * @param String fname
	 * @throws Exception FileNotFoundException
	 */
	public void initialize (String fname) throws FileNotFoundException {
		options = new MinHeap<Option>();
		Scanner sc = new Scanner(new File(fname));
		rows = sc.nextInt();
		columns = sc.nextInt();
		grid = new GridPoint[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new GridPoint(GridType.HALL, 1.00);
			}
		}
		int row = 0;
		int column = 0;
		double cost = 0.0;
		while (sc.hasNext()) {
			row = sc.nextInt();
			column = sc.nextInt();
			char gridLetter = sc.next().charAt(0);
			if (GridPoint.GridType.findGridType(gridLetter) == 
					GridPoint.GridType.HALL)
				cost = sc.nextDouble();
			else
				cost = 0.0;
			grid[row][column] = new GridPoint(GridType.findGridType(gridLetter), cost);
			if (grid[row][column].gridType == GridPoint.GridType.START)
				startLocation = new Coordinate(row, column, cost);
			else if (grid[row][column].gridType == 
					GridPoint.GridType.END)
				endLocation = new Coordinate(row, column, cost);
		}
		sc.close();
	}
	/**
	 * @param Coordinate c
	 * @return boolean whether Coordinate c has been visited
	 */
	public boolean visitable(Coordinate c) {
		GridPoint p = grid[c.ROW][c.COL];
		return p.gridType != GridPoint.GridType.WALL &&
				!p.hasVisited();
	}
	/**
	 * Marks Coordinate c as visited
	 * @param Coordinate c
	 */
	public void markAsVisited(Coordinate c) {
		GridPoint p = grid[c.ROW][c.COL];
		p.visit();
	}
	/**
	 * @param Coordinate c
	 * @return boolean whether c is the end of the maze
	 */
	public boolean atEnd(Coordinate c) {
		return c.equals(endLocation);
	}
	/**
	 * @return integer number of rows in the maze
	 */
	public int getRowCount() {
		return grid.length;
	}
	/**
	 * @return integer number of columns in the maze
	 */
	public int getColumnCount() {
		return grid[0].length;
	}
	/**
	 * @return the Coordinate at the start location
	 */
	public Coordinate getStart() {
		return startLocation;
	}
	/**
	 * @return the Coordinate at the end location
	 */
	public Coordinate getEnd() {
		return endLocation;
	}
	/**
	 * adds all possible moves from Coordinate c
	 * @param Coordinate c
	 */
	public void addOptionsFromHere(Option o) {
		Coordinate c = o.c;
		if (isLegalCoord(c.ROW - 1, c.COL))
			options.add(new Option(new Coordinate (c.ROW - 1, c.COL, grid[c.ROW - 1][c.COL].cost), o));
		if (isLegalCoord(c.ROW, c.COL + 1))
			options.add(new Option(new Coordinate (c.ROW, c.COL + 1, grid[c.ROW][c.COL + 1].cost), o));
		if (isLegalCoord(c.ROW + 1, c.COL))
			options.add(new Option(new Coordinate (c.ROW + 1, c.COL, grid[c.ROW + 1][c.COL].cost), o));
		if (isLegalCoord(c.ROW, c.COL - 1))
			options.add(new Option(new Coordinate (c.ROW, c.COL - 1, grid[c.ROW][c.COL - 1].cost), o));
	}
	/**
	 * Determines whether a given row and column describe
	 * a legal coordinate
	 * @param int row
	 * @param int col
	 * @return boolean whether coordinate is legal
	 */
	public boolean isLegalCoord(int row, int col) {
		Coordinate c = new Coordinate(row, col, 0.0);
		int low = 0;
		int rowHigh = rows - 1;
		int colHigh = columns - 1;
		if (low <= c.ROW && c.ROW <= rowHigh &&
				low <= c.COL && c.COL <= colHigh)
			return grid[c.ROW][c.COL].gridType != GridType.WALL &&
			!grid[c.ROW][c.COL].hasVisited();
		else
			return false;
	}
	/**
	 * @return String representation of the maze
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				s.append(grid[i][j].toString());
			}
			s.append("\n");
		}
		return s.toString();
	}
	public Option getBestPath() {
		return endOption;
	}
	/**
	 * Solves the maze
	 */
	public void solve() {
		Option position = new Option(startLocation, null);
		addOptionsFromHere(position);
		while (options.hasMore() && !position.c.equals(endLocation)) {
			position = options.getNext();
			markAsVisited(position.c);
			addOptionsFromHere(position);
		}
		if (position.c.equals(endLocation)) {
			System.out.println("Congrats on solving the problem");
			System.out.println(position.c);
			endOption = position;
		}
		else
			System.out.println("Couldn't solve it");
	}
	
	public GridPoint[][] getGrid() {
		return grid;
	}
	public static void main(String[] args) {
		String fname = "maze2.txt";
		MazeRunnerNick maze = new MazeRunnerNick(fname);
		System.out.println(maze.toString());
		maze.solve();
	}
}
