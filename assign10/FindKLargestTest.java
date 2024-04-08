package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the methods from FindKLargest class.
 * 
 * @author Isabelle Cook and Courtney Carroll
 * @version April 8, 2024
 */
class FindKLargestTest {
	private ArrayList<String> list;
	private ArrayList<String> listEmpty;
	private Comparator<String> cmp;
	private ArrayList<Integer> listInt;
	private Comparator<Integer> cmp2;
	
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
		
		cmp2 = (int1, int2) -> ((int2.compareTo(int1)));
		listInt = new ArrayList<Integer>();
		listInt.add(3);
		listInt.add(-2);
		listInt.add(8);
		listInt.add(0);
		listInt.add(1);
		listInt.add(-5);
		listInt.add(10);
	}

	@Test
	void testFindKLargestHeapException() {
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, -1));
		assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 10));
	}
	
	@Test
	void testFindKLargestHeapEmpty() {
		List<String> kLargest = FindKLargest.findKLargestHeap(listEmpty, 0);
		assertTrue(kLargest.isEmpty());
		listEmpty.add("purple");
		kLargest = FindKLargest.findKLargestHeap(listEmpty, 1);
		assertEquals(kLargest.get(0), "purple");
		
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
	
	@Test
	void testFindKLargestSort() {
		List<String> kLargest = FindKLargest.findKLargestSort(list, 0);
		assertTrue(kLargest.isEmpty());
		
		kLargest = FindKLargest.findKLargestSort(list, list.size());
		assertEquals(kLargest.get(0), "faeries");
		assertEquals(kLargest.get(1), "elephant");
		assertEquals(kLargest.get(2), "dog");
		assertEquals(kLargest.get(3), "cats");
		assertEquals(kLargest.get(4), "banana");
		assertEquals(kLargest.get(5), "apple");
		
		kLargest = FindKLargest.findKLargestSort(list, 3);
		assertEquals(kLargest.get(0), "faeries");
		assertEquals(kLargest.get(1), "elephant");
		assertEquals(kLargest.get(2), "dog");
	}
	
	@Test
	void testFindKLargestSortCmp() {
		List<String> kLargest = FindKLargest.findKLargestSort(list, 0, cmp);
		assertTrue(kLargest.isEmpty());
		
		kLargest = FindKLargest.findKLargestSort(list, list.size(), cmp);
		assertEquals(kLargest.get(0), "elephant");
		assertEquals(kLargest.get(1), "faeries");
		assertEquals(kLargest.get(2), "banana");
		assertEquals(kLargest.get(3), "apple");
		assertEquals(kLargest.get(4), "cats");
		assertEquals(kLargest.get(5), "dog");
		
		kLargest = FindKLargest.findKLargestSort(list, 3, cmp);
		assertEquals(kLargest.get(0), "elephant");
		assertEquals(kLargest.get(1), "faeries");
		assertEquals(kLargest.get(2), "banana");
	}
	@Test
	void testFindKLargestHeapCmpInt() {
		List<Integer> kLargest = FindKLargest.findKLargestHeap(listInt, 0, cmp2);
		assertTrue(kLargest.isEmpty());
		
		kLargest = FindKLargest.findKLargestHeap(listInt, listInt.size(), cmp2);
		assertEquals(kLargest.get(0), -5);
		assertEquals(kLargest.get(1), -2);
		assertEquals(kLargest.get(2), 0);
		assertEquals(kLargest.get(3), 1);
		assertEquals(kLargest.get(4), 3);
		assertEquals(kLargest.get(5), 8);
		assertEquals(kLargest.get(6), 10);
		
		kLargest = FindKLargest.findKLargestHeap(listInt, 3, cmp2);
		assertEquals(kLargest.get(0), -5);
		assertEquals(kLargest.get(1), -2);
		assertEquals(kLargest.get(2), 0);
	}
}
