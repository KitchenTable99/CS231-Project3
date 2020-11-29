import java.awt.Graphics;

/*
   File: Cell.java
   Author: Caleb Bitting
   Date: 09/14/2020
   Cell Class for Sudoku solver
*/

public class Cell {
	
	private int row, col, value;
	private boolean locked;
	
	// constructors
	public Cell() {
		row = 0;
		col = 0;
		value = 0;
		locked = false;
	}
	
	public Cell(int row, int col, int value) {
		this.row = row;
		this.col = col;
		this.value = value;
		locked = false;
	}
	
	public Cell(int row, int col, int value, boolean locked) {
		this.row = row;
		this.col = col;
		this.value = value;
		this.locked = locked;
	}
	
	// accessors and mutators
	/**
	 * @return int representing row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return int representing column 
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * @return int representing value 
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return boolean representing the locked status
	 */
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * @param r: int representing the goal row
	 */
	public void setRow(int r) {
		this.row = r;
	}
	
	/**
	 * @param c: int representing the goal column
	 */
	public void setCol(int c) {
		this.col = c;
	}
	
	/**
	 * @param v: int representing the goal value
	 */
	public void setValue(int v) {
		this.value = v;
	}
	
	/**
	 * @param l: int representing the goal locked status
	 */
	public void setLocked(boolean l) {
		this.locked = l;
	}
	
	// utility methods
	/**
	 * @param g: Graphics object
	 * @param x0: int for x-offset
	 * @param y0: int for y-offset
	 * @param scale: int for scale
	 */
	public void draw(Graphics g, int x0, int y0, int scale) {
		char[] temp = new char[2];
		temp[0] = (char)('0' + this.value);
		temp[1] = '0';
		g.drawChars(temp, 0, 1, x0, y0);
	} //temp is GC'ed
	
	// ursurper methdos
	public Cell clone() {
		Cell toReturn = new Cell();
		toReturn.setCol(this.col);
		toReturn.setRow(this.row);
		toReturn.setValue(this.value);
		toReturn.setLocked(this.locked);
		
		return toReturn;
	}
	
	public String toString() {
		return Integer.toString(value);
	}

}
