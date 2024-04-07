package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertEquals(heap3.size(), 6);
		Object[] list = heap3.toArray();
		assertEquals(list[0], "fox");
		assertEquals(list[1], "elephant");
		assertEquals(list[2], "cat");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "banana");
		assertEquals(list[5], "apple");
		
		heap3.add("purple");
		assertEquals(heap3.size(), 7);
		list = heap3.toArray();
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
		assertEquals(heap4.size(), 6);
		Object[] list = heap4.toArray();
		assertEquals(list[0], "elephant");
		assertEquals(list[1], "banana");
		assertEquals(list[2], "fox");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "apple");
		assertEquals(list[5], "cat");
		
		heap4.add("purple");
		assertEquals(heap4.size(), 7);
		list = heap4.toArray();
		assertEquals(list[0], "elephant");
		assertEquals(list[1], "banana");
		assertEquals(list[2], "purple");
		assertEquals(list[3], "dog");
		assertEquals(list[4], "apple");
		assertEquals(list[5], "cat");
		assertEquals(list[6], "fox");
	}

}
