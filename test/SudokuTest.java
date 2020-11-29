import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTest {

	Sudoku basic, sudoku;
	
	@BeforeEach
	void init() {
		basic = new Sudoku();
		sudoku = new Sudoku(10);
		
	}
	
	@Test
	void constructorTest() {
		assertEquals(basic.getBoard().numLocked(), 0);
		assertEquals(sudoku.getBoard().numLocked(), 10);
	} // basic and sudoku are GC'ed
	
	@Test
	void validTest() {
		assertTrue(sudoku.getBoard().isSolved());
	} // basic and sudoku are GC'ed

}
