import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellStackTest {

	CellStack basic, stack;
	Cell temp;
	
	@BeforeEach
	void init() {
		basic = new CellStack();
		stack = new CellStack(5);
		temp = new Cell();
	}
	
	@Test
	void constructorTest() {
		assertEquals(basic.getCapacity(), 10);
		assertEquals(basic.getTop(), -1);
		assertEquals(stack.getCapacity(), 5);
		assertEquals(stack.getTop(), -1);
	} // basic, stack, and temp are GC'ed
	
	@Test
	void pushTest() {
		stack.push(temp);
		assertEquals(stack.getTop(), 0);
		for (int i = 0; i < 5; i++) {
			stack.push(temp);
		} // i is GC'ed
		assertEquals(stack.getCapacity(), 10);
		assertEquals(stack.getContents().length, 10);
		for (int i = 0; i < 10; i++) {
			stack.push(temp);
		} // i is GC'ed
		assertEquals(stack.getCapacity(), 20);
		
	} // basic, stack, and temp are GC'ed
	
	@Test
	void popTest() {
		// if empty returns null
		assertEquals(stack.pop(), null);

		// fill stack
		for (int i = 0; i < 4; i++) {
			stack.push(temp);
		} // i is GC'ed
		
		// return top value
		assertEquals(temp, stack.pop());
		
		// test that array shrinks
		for (int i = 0; i < 8; i++) {
			stack.push(temp);		// makes the array grow (currently at 11 cells)
		} // i is GC'ed
		// array will shrink at <= capacity/3 which is 6 cells in the stack
		for (int i = 0; i < 5; i++) {
			stack.pop();  			// the last pop should shrink the cell
		} // i is GC'ed
		assertEquals(stack.getCapacity(), 10);
	} // basic, stack, and temp are GC'ed
	
	@Test
	void sizeTest() {
		stack.push(temp);
		assertEquals(stack.size(), 1);
	} // basic, stack, and temp are GC'ed
	
	@Test
	void emptyTest() {
		assertTrue(stack.isEmpty());
		stack.push(temp);
		assertFalse(stack.isEmpty());
	} // basic, stack, and temp are GC'ed
	
}
