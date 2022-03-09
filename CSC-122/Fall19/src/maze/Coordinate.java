package maze;

public class Coordinate implements Comparable<Coordinate> {
	public final int ROW;
	public final int COL;
	public final double cost;
	public Coordinate(int row, int col, double cost) {
		this.ROW = row;
		this.COL = col;
		this.cost = cost;
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		} else if (obj == this) {
			return true;
		} else {
			return this.ROW == ((Coordinate)obj).ROW &&
					this.COL == ((Coordinate)obj).COL;
		}
	}
	public String toString() {
		return this.ROW + ", " + this.COL;
	}

	@Override
	public int compareTo(Coordinate c) {
		return (int) (this.cost - c.cost);
	}
} 
