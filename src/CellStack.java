/*
   File: CellStack.java
   Author: Caleb Bitting
   Date: 09/18/2020
   CellStack Class for Sudoku solver
*/

public class CellStack {
	
	private Cell[] arr;
	private int top, capacity;
	
	// constructors
	public CellStack() {
		capacity = 10;
		arr = new Cell[capacity];
		top = -1;
	}
	
	public CellStack(int c) {
		capacity = c;
		arr = new Cell[c];
		top = -1;
	}
	
	// mutators and accessors
	/**
	 * @return int for max capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * @return int for the current top pointer
	 */
	public int getTop() {
		return top;
	}
	
	/**
	 * @return array of cells that represent the contents of the Stack
	 */
	public Cell[] getContents() {
		return arr;
	}
	
	// quasi-accessor methods
	/**
	 * @return boolean representing the emptiness of the stack
	 */
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return an int for the number of places in the array that have an actual object not a null
	 */
	public int size() {
		int counter = 0;
		// only count the non-null items in arr
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				counter++;
			}
		} // i is GC'ed
		return counter;
	}
	
	// utility methods
	/**
	 * @param c: the cell to push onto the stack
	 */
	public void push(Cell c) {
		// if the stack is full
		if (top == capacity - 1) {
			// double the capacity
			this.capacity = this.capacity*2;
			Cell[] temp = new Cell[capacity];
			// copy over all the old values
			for (int i = 0; i < arr.length; i++) {
				temp[i] = arr[i];
			} // i is GC'ed
			// overwrite the old, too-small contents
			arr = temp;
		} // temp is GC'ed
		
		arr[++top] = c;
	}
	
	/**
	 * @return cell for the top cell on the Stack
	 */
	public Cell pop() {
		// if stack is empty
		if (top == -1) {
			return null;
		}
		Cell toReturn = arr[top--];
		
		// if stack is at 1/3 capacity, half the size
		if (top <= capacity/3) {
			// make a smaller contents array
			this.capacity = capacity/2;
			Cell[] temp = new Cell[capacity];
			// only copy over the stuff that fits in temp
			for (int i = 0; i < temp.length; i++) {
				temp[i] = arr[i];
			} // i is GC'ed
			arr = temp;
		} // temp is GC'ed
		
		return toReturn;
	}
	
}
