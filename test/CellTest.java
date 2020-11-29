import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {

	Cell basic, cell, cell2;
	
	@BeforeEach
	void init() {
		basic = new Cell();
		cell = new Cell(1, 2, 3);
		cell2 = new Cell(2, 3, 4, true);
	}
	
	@Test
	void constructorTest() {
		// tests for empty constructor
		assertEquals(basic.getRow(), 0);
		assertEquals(basic.getCol(), 0);
		assertEquals(basic.getValue(), 0);
		assertFalse(basic.isLocked());
		// tests for second constructor
		assertEquals(cell.getRow(), 1);
		assertEquals(cell.getCol(), 2);
		assertEquals(cell.getValue(), 3);
		assertFalse(cell.isLocked());
		// tests for full constructor
		assertEquals(cell2.getRow(), 2);
		assertEquals(cell2.getCol(), 3);
		assertEquals(cell2.getValue(), 4);
		assertTrue(cell2.isLocked());
	} // basic, cell, and cell2 are GC'ed
	
	@Test
	void cloneTest() {
		Cell temp  = cell.clone();
		assertEquals(temp.getRow(), cell.getRow());
		assertEquals(temp.getCol(), cell.getCol());
		assertEquals(temp.getValue(), cell.getValue());
		assertEquals(temp.isLocked(), cell.isLocked());
	} // basic, cell, and cell2 are GC'ed

}
