/*
   File: Board.java
   Author: Caleb Bitting
   Date: 09/19/2020
   Board Class
*/

import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;

public class Board {
	
	private Cell[][] contents;
	public static final int Size = 9;
	
	// constructors
	public Board() {
		contents = new Cell[Board.Size][Board.Size];
		this.populateContents();
	}
	
	// accessors and mutators
	/**
	 * @return 2D array of the Board contents
	 */
	public Cell[][] getContents() {
		return contents;
	}
	
	/**
	 * @return number of rows
	 */
	public int getRows() {
		return contents.length;
	}
	
	/**
	 * @return number of columns
	 */
	public int getCols() {
		return contents[0].length;
	}
	
	/**
	 * @param r: int representing row
	 * @param c: int representing column
	 * @return Cell at the targeted location
	 */
	public Cell getCell(int r, int c) {
		return contents[r][c];
	}
	
	/**
	 * @param r: int representing row
	 * @param c: int represeting column
	 * @param v: int representing value to set
	 */
	public void setCell(int r, int c, int v) {
		contents[r][c].setValue(v);
	}
	
	/**
	 * @param r: int representing row
	 * @param c: int representing column
	 * @param v: int representing value
	 * @param l: boolean representing locked status
	 */
	public void setCell(int r, int c, int v, boolean l) {
		contents[r][c].setValue(v);
		contents[r][c].setLocked(l);
	}
	
	// quasi-accessor methods	
	/**
	 * @param r: int representing row
	 * @param c: int representing column
	 * @return boolean if targeted cell is zero
	 */
	public boolean isZero(int r, int c) {
		if (contents[r][c].getValue() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param r: int representing row
	 * @param c: int representing column
	 * @return boolean of the targeted cell's locked status
	 */
	public boolean isLocked(int r, int c) {
		return contents[r][c].isLocked();
	}
	
	/**
	 * @return int that counts the number of locked cells in the board
	 */
	public int numLocked() {
		int counter = 0;
		// add one to a counter for each cell in the board if it is locked
		for (Cell[] row : contents) {
			for (Cell cell : row) {
				if (cell.isLocked()) {
					counter++;
				}
			} // cell is GC'ed
		} // row is GC'ed
		return counter;
	}
	
	// utility methods
	/**
	 * Generate an actual cell object at each of the memory references in the contents array
	 */
	private void populateContents() {
		for (int r = 0; r < Board.Size; r++) {
			for (int c = 0; c < Board.Size; c++) {
				contents[r][c] = new Cell(r, c, 0);
			} // c is GC'ed
		} // r is GC'ed
	}
	
	/**
	 * @param filename: string representing the path to a board to read
	 */
	public void read(String filename) {
	    try {
	    	// set up objects
	    	ArrayList<Integer> fileContents = new ArrayList<Integer>();
	    	FileReader reader = new FileReader(filename);
	    	BufferedReader bufferedReader = new BufferedReader(reader);

	    	// go through each line
	    	String line = bufferedReader.readLine();
	    	while (line != null) {
	    		// split by one or more spaces
	    		String[] temp = line.split("[ ]+");
	    		for (String thing : temp) {
	    			// as long as the constituent split part is not an empty string
	    			if (!thing.equals("")) {
	    				// turn it into an integer and add it to the ArrayList
	    				int tempInt = Integer.parseInt(thing);
	    				fileContents.add(tempInt);
	    			} // tempInt is GC'ed
	    		} // thing is GC'ed
	    		line = bufferedReader.readLine();
	    		
	    	} // temp is GC'ed
	    	bufferedReader.close();
	    	// reshape the array list into a 9x9 array
	    	int[][] reshapedContents = this.reshapeRead(fileContents, Board.Size, Board.Size); 
	    	// actually update the values in the board
	    	for (int r = 0; r < Board.Size; r++) {
	    		for (int c = 0; c < Board.Size; c++) {
	    			// get cell that we're pointing at
	    			Cell temp = contents[r][c];
	    			// get it's value before updating it from read
	    			int preRead = temp.getValue();
	    			// update from read file
	    			temp.setValue(reshapedContents[r][c]);
	    			// get value after update
	    			int postRead = temp.getValue();
	    			// if they changes (i.e. if the value wasn't zero and is part of the given values) lock the cell
	    			if (preRead != postRead) {
	    				temp.setLocked(true);
	    			}
	    		} // c, temp, preRead, and postRead are GC'ed
	    	} // r is GC'ed
	    } // line, fileContents, reader, bufferedReader are GC'ed
	    catch(FileNotFoundException ex) {
	      System.out.println("Board.read():: unable to open file " + filename );
	    }
	    catch(IOException ex) {
	      System.out.println("Board.read():: error reading file " + filename);
	    }

	    return;
	  }
	
	/**
	 * @param arrLst: the ArrayList to shape into a 2D array
	 * @param rows: number of rows in final array
	 * @param cols: number of columns in final array
	 * @return 2D array with the same data as arrLst
	 */
	private int[][] reshapeRead(ArrayList<Integer> arrLst, int rows, int cols) {
		// instantiate return object
		int[][] toReturn  = new int[rows][cols];
		
		for (int i = 0; i < arrLst.size(); i++) {
			toReturn[i/cols][i%cols] = arrLst.get(i);		// integer division gives the row number and the modulo operator gives the column number
		} // i is GC'ed
		
		return toReturn;
	}
	
	/**
	 * @param g: Graphics object
	 * @param scale: int for scale
	 */
	public void draw(Graphics g, int scale) {
		// call the draw method on each Cell on the board 
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				contents[r][c].draw(g, (c+1)*scale, (r+1)*scale, scale);
			} // c is GC'ed
		} // r is GC'ed
	}
		
	/**
	 * @param r: int for the target row
	 * @param c: int for the target column
	 * @param v: int for the goal value
	 * @return boolean representing if the goal value is valid in the target location
	 */
	public boolean validValue(int r, int c, int v) {
		// check row
		for (Cell cell : contents[r]) {
			if (cell.getValue() == v && cell.getCol() != c) {
				return false;
			}
		} // cell is GC'ed
		
		// check column
		for (int row = 0; row < Board.Size; row++) {
			if (contents[row][c].getValue() == v && row != r) {
				return false;
			}
		} // row is GC'ed
		
		// check square
		int bigRowCoord = r/3;
		int bigColCoord = c/3;     // this will change a 9x9 grid into a 3x3 grid
		for (int i = 3*bigRowCoord; i < 3*(bigRowCoord + 1); i++) {
			for (int j = 3*bigColCoord; j < 3*(bigColCoord + 1); j++) {
				if (contents[i][j].getValue() == v && !(i == r && j == c)) {
					return false;
				}
			} // j is GC'ed
		} // i is GC'ed
		return true;
	} // bigRowCoord and bigColCoord are GC'ed
	
	/**
	 * @return boolean representing if the entire board is a valid solution
	 */
	public boolean isSolved() {
		boolean toReturn = true;
		
		// for each cell
		for (int r = 0; r < Board.Size; r++) {
			for (int c = 0; c < Board.Size; c++) {
				// if that cell is not a zero
				// any cell that is a zero shouldn't be checked in order to facilitate JUnit tests for the constructor
				// the solve method is checked in a way such that this blind-eye is not relevant (the solved board is stored in a different field that is null if no solution present)
				if (contents[r][c].getValue() != 0) {
					// check it across all the relevant ones
					boolean cellValid = this.validValue(r, c, contents[r][c].getValue());
					if (cellValid == false) {
						toReturn = false;
					}
				} // cellValid is GC'ed
			} // c is GC'ed
		} // r is GC'ed
		
		return toReturn;
	}

	
	// usurper methods
	public String toString() {
		String toReturn = "";
		// for each cell
		for (int r = 0; r < Board.Size; r++) {
			for (int c = 0; c < Board.Size; c++) {
				// add some padding
				toReturn += " ";
				toReturn += contents[r][c].toString();
				toReturn += " ";
				// if at the end of the 3x3 block
				if (c%3 == 2) {
					toReturn += "  ";
				}
			} // c is GC'ed
			toReturn += "\n";
			// if at the end of the 3x3 block
			if (r%3 == 2) {
				toReturn += "\n";
			}
		} // r is GC'ed
		
		return toReturn;
	}
	
	public Board clone() {
		Board toReturn = new Board();
		// for each cell
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				// get the value and copy it to the new board
				int temp = contents[r][c].getValue();
				toReturn.setCell(r, c, temp);
			} // temp and c are GC'ed
		} // r is GC'ed
		return toReturn;
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		String path = "../test/" + args[0];
		board.read(path);
		//Board board = new Board();
		System.out.println(board);
	} // board and path are GC'ed

}
