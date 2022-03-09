package maze;
/**
 * GridPoint class
 * @author nickrobins
 */
public class GridPoint implements Comparable<GridPoint> {
	/**
	 * GridType enumerated type used to specify a certain type of GridPoint.
	 */
	public enum GridType { 
		WALL ('X', '\u2588'),
		HALL ('B', '\u2591'),
		START ('S', 'S'),
		END ('E', 'E');
		private char text;
		private char display;
		private GridType (char text, char display) {
			this.text = text;
			this.display = display;
		}
		public static GridType findGridType(char c) {
			if (c == WALL.text)
				return WALL;
			else if (c == HALL.text)
				return HALL;
			else if (c == START.text)
				return START;
			else
				return END;
		}
	}
	public final GridType gridType;
	public final double cost;
	private boolean visited;
	/**
	 * 2-parameter constructor for GridPoint
	 * @param GridType type
	 * @param double cost
	 */
	public GridPoint (GridType type, double cost) {
		this.gridType = type;
		this.cost = cost;
		this.visited = false;
	}
	/**
	 * @return boolean; whether this GridPoint has been visited
	 */
	public boolean hasVisited() {
		return visited;
	}
	/**
	 * Marks a GridPoint as visited
	 */
	public void visit() {
		visited = true;
	}
	/**
	 * toString that returns the display value for the gridPoint's gridType
	 */
	public String toString() {
		return String.valueOf(this.gridType.display);
	}

	@Override
	public int compareTo(GridPoint p) {
		return (int) (this.cost - p.cost);
	}
	
	public GridType getTileType() {
		return gridType;
	}
	
	public double getCost() {
		return cost;
	}
}

