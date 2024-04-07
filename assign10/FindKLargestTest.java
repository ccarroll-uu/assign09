package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindKLargestTest {
	private ArrayList<String> list;
	private ArrayList<String> listEmpty;
	private Comparator<String> cmp;
	@BeforeEach
	void setUp() throws Exception {
		cmp = (str1, str2) -> ((Integer)str1.length()).compareTo(str2.length());
		listEmpty = new ArrayList<String>();
		list = new ArrayList<String>();
		list.add("apple");
		list.add("banana");
		list.add("cats");
		list.add("dog");
		list.add("elephant");
		list.add("faeries");
	}

	@Test
	void testFindKLargestHeapException() {
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, -1));
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 10));
	}
	
	@Test
	void testFindKLargestHeapEmtpy() {
		List<String> kLargest = FindKLargest.findKLargestHeap(listEmpty, 0);
		assertTrue(kLargest.isEmpty());
		listEmpty.add("purple");
		kLargest = FindKLargest.findKLargestHeap(listEmpty, 1);
		assertEquals(kLargest.get(1), "purple");
		
	}
	
	@Test
	void testFindKLargestHeap() {
		List<String> kLargest = FindKLargest.findKLargestHeap(list, 0);
		assertTrue(kLargest.isEmpty());
		
		kLargest = FindKLargest.findKLargestHeap(list, list.size());
		assertEquals(kLargest.get(0), "faeries");
		assertEquals(kLargest.get(1), "elephant");
		assertEquals(kLargest.get(2), "dog");
		assertEquals(kLargest.get(3), "cats");
		assertEquals(kLargest.get(4), "banana");
		assertEquals(kLargest.get(5), "apple");
		
		kLargest = FindKLargest.findKLargestHeap(list, 3);
		assertEquals(kLargest.get(0), "faeries");
		assertEquals(kLargest.get(1), "elephant");
		assertEquals(kLargest.get(2), "dog");
	}
	
	@Test
	void testFindKLargestHeapCmp() {
		List<String> kLargest = FindKLargest.findKLargestHeap(list, 0, cmp);
		assertTrue(kLargest.isEmpty());
		
		kLargest = FindKLargest.findKLargestHeap(list, list.size(), cmp);
		assertEquals(kLargest.get(0), "elephant");
		assertEquals(kLargest.get(1), "faeries");
		assertEquals(kLargest.get(2), "banana");
		assertEquals(kLargest.get(3), "apple");
		assertEquals(kLargest.get(4), "cats");
		assertEquals(kLargest.get(5), "dog");
		
		kLargest = FindKLargest.findKLargestHeap(list, 3, cmp);
		assertEquals(kLargest.get(0), "elephant");
		assertEquals(kLargest.get(1), "faeries");
		assertEquals(kLargest.get(2), "banana");
	}
}
