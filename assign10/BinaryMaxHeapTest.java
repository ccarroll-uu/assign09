
package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This class tests the BinaryMaxHeap class.
 * 
 * @author Isabelle Cook, Courtney Carroll
 * @version April 6, 2024
 */
class BinaryMaxHeapTest {
	BinaryMaxHeap<String> heap1;
	BinaryMaxHeap<String> heap2;
	BinaryMaxHeap<String> heap3;
	BinaryMaxHeap<String> heap4;

	@BeforeEach
	void setUp() throws Exception {
		Comparator<String> cmp = (str1, str2) -> ((Integer)str1.length()).compareTo(str2.length());
		ArrayList<String> list = new ArrayList<String>();
		list.add("apple");
		list.add("banana");
		list.add("cat");
		list.add("dog");
		list.add("elephant");
		list.add("fox");
		
		heap1 = new BinaryMaxHeap<String>();
		heap2 = new BinaryMaxHeap<String>(cmp);
		heap3 = new BinaryMaxHeap<String>(list);
		heap4 = new BinaryMaxHeap<String>(list, cmp);
	}
	
	@Test
	void testSize() {
		assertEquals(heap1.size(), 0);
		assertEquals(heap2.size(), 0);
		assertEquals(heap3.size(), 6);
		assertEquals(heap4.size(), 6);
	}
	
	@Test
	void testPeekException() {
		assertThrows(NoSuchElementException.class, () -> heap1.peek());
		assertThrows(NoSuchElementException.class, () -> heap2.peek());
	}
	
	@Test
	void testPeek() {
		assertEquals(heap3.peek(), "fox");
		assertEquals(heap4.peek(), "elephant");
	}
	
	@Test
	void testToArrayEmpty() {
		assertEquals(heap1.toArray().length, 0);
		assertEquals(heap2.toArray().length, 0);
	}
	
	@Test
	void testToArray() {
		assertEquals(heap3.size(), 6);
		Object[] list = heap3.toArray();
		assertEquals(list[0], "fox");
		assertEquals(list[1], "elephant");
		assertEquals(list[2], "cat");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "banana");
		assertEquals(list[5], "apple");
		
		assertEquals(heap4.size(), 6);
		list = heap4.toArray();
		assertEquals(list[0], "elephant");
		assertEquals(list[1], "banana");
		assertEquals(list[2], "fox");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "apple");
		assertEquals(list[5], "cat");
	}

	@Test
	void testAddEmpty() {
		assertTrue(heap1.size() == 0);
		heap1.add("blue");
		assertEquals(heap1.peek(), "blue");
		assertTrue(heap1.size() == 1);
		heap1.add("candy");
		assertEquals(heap1.peek(), "candy");
		assertTrue(heap1.size() == 2);
	}
	
	@Test
	void testAddEmptyCmp() {
		assertTrue(heap2.size() == 0);
		heap2.add("blue");
		assertEquals(heap2.peek(), "blue");
		assertTrue(heap2.size() == 1);
		heap2.add("candy");
		assertEquals(heap2.peek(), "candy");
		assertTrue(heap2.size() == 2);
	}
	
	@Test
	void testAdd() {
		heap3.add("purple");
		assertEquals(heap3.size(), 7);
		Object[] list = heap3.toArray();
		assertEquals(list[0], "purple");
		assertEquals(list[1], "elephant");
		assertEquals(list[2], "fox");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "banana");
		assertEquals(list[5], "apple");
		assertEquals(list[6], "cat");
		
	}
	
	@Test
	void testAddCmp() {
		heap4.add("purple");
		assertEquals(heap4.size(), 7);
		Object[] list = heap4.toArray();
		assertEquals(list[0], "elephant");
		assertEquals(list[1], "banana");
		assertEquals(list[2], "purple");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "apple");
		assertEquals(list[5], "cat");
		assertEquals(list[6], "fox");
	}
	
	@Test
	void testExtractMaxException() {
		assertThrows(NoSuchElementException.class, () -> heap1.extractMax());
		assertThrows(NoSuchElementException.class, () -> heap2.extractMax());
	}
	
	@Test
	void testExtractMaxEmpty() {
		assertTrue(heap1.size() == 0);
		heap1.add("candy");
		assertEquals(heap1.extractMax(), "candy");
		assertTrue(heap1.size() == 0);
		
		assertTrue(heap2.size() == 0);
		heap2.add("blue");
		assertEquals(heap2.extractMax(), "blue");
		assertTrue(heap2.size() == 0);
	}
	
	@Test
	void testExtractMax() {
		assertEquals(heap3.extractMax(), "fox");
		assertEquals(heap3.size(), 5);
		Object[] list = heap3.toArray();
		assertEquals(list[0], "elephant");
		assertEquals(list[1], "dog");
		assertEquals(list[2], "cat");
		assertEquals(list[3], "apple");
		assertEquals(list[4], "banana");
	}
	
	@Test
	void testExtractMaxCmp() {
		assertEquals(heap4.extractMax(), "elephant");
		assertEquals(heap4.size(), 5);
		Object[] list = heap4.toArray();
		assertEquals(list[0], "banana");
		assertEquals(list[1], "apple");
		assertEquals(list[2], "fox");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "cat");
	}
	
	@Test
	void testIsEmptyTrue() {
		assertTrue(heap1.isEmpty());
		assertTrue(heap2.isEmpty());
	}
	
	@Test
	void testIsEmptyFalse() {
		assertFalse(heap3.isEmpty());
		assertFalse(heap4.isEmpty());
	}
	
	@Test
	void testClear() {
		heap3.clear();
		assertTrue(heap3.isEmpty());
	}
	
	
}

