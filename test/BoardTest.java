import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

	Board board;
	Cell cell;
	
	@BeforeEach
	void init() {
		board = new Board();
		cell = new Cell();
	}
	
	@Test
	void basicsTest() {
		assertEquals(board.getCols(), 9);
		assertEquals(board.getRows(), 9);
		assertEquals(board.getCell(4, 3).getValue(), cell.getValue());
		assertFalse(board.isLocked(4, 3));
	} // board and cell are GC'ed
	
	@Test
	void complexConstructorTest() {
		board.setCell(3, 2, 2);
		assertEquals(board.getCell(3, 2).getValue(), 2);
		board.setCell(4, 5, 3, true);
		assertTrue(board.isLocked(4, 5));
		assertEquals(board.numLocked(), 1);
		assertTrue(board.isSolved());
	} // board and cell are GC'ed
	
	@Test
	void readTest() {
		board.read("/Users/cbitting/eclipse-workspace/Project03/test/board_nsp_10.txt");
		assertEquals(board.getCell(2, 0).getValue(), 9);
		assertEquals(board.numLocked(), 10);
	} // board and cell are GC'ed
	
	@Test
	void valueTest() {
		// test rows
		board.setCell(1, 1, 2);
		assertFalse(board.validValue(1, 3, 2));
		assertTrue(board.validValue(1, 3, 5));
		// test cols
		board.setCell(4, 5, 3);
		assertFalse(board.validValue(0, 5, 3));
		assertTrue(board.validValue(0, 5, 1));
		// test square
		board.setCell(7, 8, 9);
		assertFalse(board.validValue(6, 7, 9));
		assertTrue(board.validValue(6, 7, 1));
	} // board and cell are GC'ed
	
	@Test
	void solvedTest() {
		board.read("/Users/cbitting/eclipse-workspace/Project03/test/board_nsp_10_solved.txt");
		assertTrue(board.isSolved());
	} // board and cell are GC'ed

}
