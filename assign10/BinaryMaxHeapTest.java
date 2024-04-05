package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
	}

}
