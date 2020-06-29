package utils;

/**
 * Class for representing couple of coordinates as objects
 * @author 20027017 & 20031485
 *
 */
public class Coordinates {
	private int row;
	private int column;
	
	/**
	 * Constructor for a Coordinates object
	 * @param row The row coordinate
	 * @param column The column coordinate
	 */
	public Coordinates(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Getter for the row of the Coordinates object
	 * @return Integer representing the row of the Coordinates object
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Getter for the column of the Coordinates object
	 * @return Integer representing the column of the Coordinates object
	 */
	public int getColumn() {
		return this.column;
	}
}
