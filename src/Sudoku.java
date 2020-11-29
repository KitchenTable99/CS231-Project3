/*
   File: Sudoku.java
   Author: Caleb Bitting
   Date: 09/19/2020
   Sudoku Solver
*/

import java.util.Random;

public class Sudoku {
	
	private Board board, solved;
	private LandscapeDisplay scape;
	private Random rand;
	
	// constructors
	public Sudoku() {
		board = new Board();
		scape = new LandscapeDisplay(board, 30);
	}
	
	public Sudoku(int v) {
		board = new Board();
		scape = new LandscapeDisplay(board, 30);
		rand = new Random();
		int success = 0;
		
		while (success != v) {
			int r = rand.nextInt(9);
			int c = rand.nextInt(9);
			if (board.isZero(r, c)) {
				int value = 0;
				while (value == 0 | !(board.validValue(r, c, value))) {
					value = rand.nextInt(10);
				}
				board.setCell(r, c, value, true);
				success++;
			}
			
		} // r, c, value are GC'ed
		
	} // success is GC'ed
	
	// accessors
	/**
	 * @return a Board object—the internal board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * @param delay: int representing the ms delay between screen refreshes
	 */
	public void solve(int delay) {
		// loop over the board
		for (int r = 0; r < Board.Size; r++) {
			for (int c = 0; c < Board.Size; c++) {
				
				// first zero-cell
				// if the board is completely solved and represents a valid solution, this method will never enter the if statement. This fact is the reason the algorithm works.
				if (this.board.getCell(r, c).getValue() == 0) {
					// try every number
					for (int v = 1; v < 10; v++) {
						// start with the lowest number
						if (this.board.validValue(r, c, v)) {
							if( delay > 0 ) {
						        try {
						            Thread.sleep(delay);
						        }
						        catch(InterruptedException ex) {
						            System.out.println("Interrupted");
						        }
						        scape.repaint();
						    }
							scape.repaint();					
							// set cell to valid value
							this.board.setCell(r, c, v);
							// call solve
							this.solve(delay);
							this.board.setCell(r, c, 0);
						}
					} // v is GC'ed
					return;
				}
			} // c is GC'ed
		} // r is GC'ed
		this.solved = this.board.clone();			// store the solved board before the recursivity resets it to zeroes
		return;
		
	}
	
	// usurper methods
	public String toString() {
		return board.toString();
	}
	
	public static void main(String[] args) {
		
		Sudoku sudoku;
		
		for (int i = 0; i < 5; i++) {
			sudoku = new Sudoku(20);
			long start = System.currentTimeMillis();
			sudoku.solve(0);
			long end = System.currentTimeMillis();
			boolean solved = true;
			if (sudoku.solved == null) {
				solved = false;
			}
			System.out.println("Board: " + Boolean.toString(solved));
			System.out.println("Time: " + Long.toString(end-start));
			System.out.println();
		} // i, start, sudoku, end, and solved are GC'ed
		
	}

}
