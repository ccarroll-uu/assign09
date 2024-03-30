package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for testing the HashTable class.
 * @author Isabelle Cook and Courtney Carroll
 * @version March 29, 2024
 */
class HashTableTest {
	private HashTable<String, Integer> emptyHashStr;
	private HashTable<String, Integer> hashStr;
	private HashTable<Integer, Integer> hashInt;
	private HashTable<StudentBadHash, Integer> hashBad;
	private HashTable<StudentMediumHash, Integer> hashMedium;
	private HashTable<StudentGoodHash, Integer> hashGood;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyHashStr = new HashTable<String, Integer>();
		hashStr = new HashTable<String, Integer>();
		hashStr.put("A", 1);
		hashStr.put("B", 2);
		hashStr.put("C", 3);
		hashStr.put("D", 4);
		hashStr.put("E", 5);
		hashStr.put("F", 6);
		hashStr.put("G", 7);
		hashStr.put("H", 8);
		hashStr.put("I", 9);
		hashStr.put("J", 10);
		
		hashInt = new HashTable<Integer, Integer>();
		for (int i = 0; i < 21; i++) {
			hashInt.put(i, i);
		}
		
		hashBad = new HashTable<StudentBadHash, Integer>();
		hashBad.put(new StudentBadHash(1019999, "Alan", "Turing"), 1);
		hashBad.put(new StudentBadHash(1004203, "Ada", "Lovelace"), 2);
		hashBad.put(new StudentBadHash(1010661, "Edsger", "Dijkstra"), 3);
		hashBad.put(new StudentBadHash(1019941, "Grace", "Hopper"), 4);
		
		hashMedium = new HashTable<StudentMediumHash, Integer>();
		hashMedium.put(new StudentMediumHash(1019999, "Alan", "Turing"), 1);
		hashMedium.put(new StudentMediumHash(1004203, "Ada", "Lovelace"), 2);
		hashMedium.put(new StudentMediumHash(1010661, "Edsger", "Dijkstra"), 3);
		hashMedium.put(new StudentMediumHash(1019941, "Grace", "Hopper"), 4);
		
		hashGood = new HashTable<StudentGoodHash, Integer>();
		hashGood.put(new StudentGoodHash(1019999, "Alan", "Turing"), 1);
		hashGood.put(new StudentGoodHash(1004203, "Ada", "Lovelace"), 2);
		hashGood.put(new StudentGoodHash(1010661, "Edsger", "Dijkstra"), 3);
		hashGood.put(new StudentGoodHash(1019941, "Grace", "Hopper"), 4);
		
		
	}
	
	// These test an empty HashTable with String keys and Integer Values.
	
	@Test
	void testClearEmpty() {
		assertEquals(emptyHashStr.size(), 0);
		emptyHashStr.clear();
		assertEquals(emptyHashStr.size(), 0);
	}
	
	@Test
	void testPutEmpty() {
		assertNull(emptyHashStr.put("A", 1));
		assertEquals(emptyHashStr.size(), 1);
		assertEquals(emptyHashStr.put("A", 2), 1);
		assertEquals(emptyHashStr.size(), 1);
	}
	
	@Test
	void testContainsKeyEmpty() {
		assertFalse(emptyHashStr.containsKey("A"));
		emptyHashStr.put("A", 1);
		assertTrue(emptyHashStr.containsKey("A"));
	}
	
	@Test
	void testContainsValueEmpty() {
		assertFalse(emptyHashStr.containsValue(1));
		emptyHashStr.put("A", 1);
		assertTrue(emptyHashStr.containsValue(1));
	}
	
	@Test 
	void testEntriesEmpty(){
		List<MapEntry<String, Integer>> entries = emptyHashStr.entries();
		assertTrue(entries.isEmpty());
		emptyHashStr.put("A", 1);
		entries = emptyHashStr.entries();
		assertEquals(entries.get(0).getKey(), "A");
		assertEquals(entries.get(0).getValue(), 1);
	}
	
	@Test
	void testGetEmpty() {
		assertNull(emptyHashStr.get("A"));
		emptyHashStr.put("A", 1);
		assertEquals(emptyHashStr.get("A"), 1);
	}
	
	@Test
	void testRemoveEmpty() {
		assertNull(emptyHashStr.remove("A"));
		emptyHashStr.put("A", 1);
		assertEquals(emptyHashStr.size(), 1);
		assertEquals(emptyHashStr.remove("A"), 1);
		assertEquals(emptyHashStr.size(), 0);
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(emptyHashStr.isEmpty());
		emptyHashStr.put("A", 1);
		assertFalse(emptyHashStr.isEmpty());
	}
	
	@Test
	void testSizeEmpty() {
		assertEquals(emptyHashStr.size(), 0);
	}
	
	// These test a HashTable with String keys and Integer values.
	
	@Test
	void testClear() {
		assertEquals(hashStr.size(), 10);
		hashStr.clear();
		assertEquals(hashStr.size(), 0);
	}
	
	@Test
	void testPut() {
		assertNull(hashStr.put("Z", 1));
		assertEquals(hashStr.size(), 11);
		assertEquals(hashStr.put("Z", 2), 1);
		assertEquals(hashStr.size(), 11);
	}
	
	@Test
	void testContainsKey() {
		assertFalse(hashStr.containsKey("Z"));
		assertTrue(hashStr.containsKey("A"));
	}
	
	@Test 
	void testEntries(){
		List<MapEntry<String, Integer>> entries = hashStr.entries();
		entries = hashStr.entries();
		assertEquals(entries.size(), 10);
	}
	
	@Test
	void testGet() {
		assertNull(hashStr.get("Z"));
		assertEquals(hashStr.get("A"), 1);
	}
	
	@Test
	void testRemove() {
		assertNull(hashStr.remove("Z"));
		assertEquals(hashStr.size(), 10);
		assertEquals(hashStr.remove("A"), 1);
		assertEquals(hashStr.size(), 9);
	}
	
	// These test a HashTable with Integer keys and Integer values.
	
	@Test
	void testClearInt() {
		assertEquals(hashInt.size(), 21);
		hashInt.clear();
		assertEquals(hashInt.size(), 0);
	}
	
	@Test
	void testPutInt() {
		assertNull(hashInt.put(100, 1));
		assertEquals(hashInt.size(), 22);
		assertEquals(hashInt.put(100, 2), 1);
		assertEquals(hashInt.size(), 22);
	}
	
	@Test
	void testContainsKeyInt() {
		assertFalse(hashInt.containsKey(100));
		assertTrue(hashInt.containsKey(15));
	}
	
	@Test 
	void testEntriesInt(){
		List<MapEntry<Integer, Integer>> entries = hashInt.entries();
		entries = hashInt.entries();
		assertEquals(entries.size(), 21);
	}
	
	@Test
	void testGetInt() {
		assertNull(hashInt.get(100));
		assertEquals(hashInt.get(15), 15);
	}
	
	@Test
	void testRemoveInt() {
		assertNull(hashInt.remove(100));
		assertEquals(hashInt.size(), 21);
		assertEquals(hashInt.remove(15), 15);
		assertEquals(hashInt.size(), 20);
	}
	
	// These test a HashTable with StudentBadHash keys and Integers values.
		
		@Test
		void testPutBad() {
			assertNull(hashBad.put(new StudentBadHash(1234, "A", "B"), 1));
			assertEquals(hashBad.size(), 5);
			assertEquals(hashBad.put(new StudentBadHash(1234, "A", "B"), 2), 1);
			assertEquals(hashBad.size(), 5);
		}
		
		@Test
		void testContainsKeyBad() {
			assertFalse(hashBad.containsKey(new StudentBadHash(1234, "A", "B")));
			assertTrue(hashBad.containsKey(new StudentBadHash(1019999, "Alan", "Turing")));
		}
		
		@Test
		void testGetBad() {
			assertNull(hashBad.get(new StudentBadHash(1234, "A", "B")));
			assertEquals(hashBad.get(new StudentBadHash(1019999, "Alan", "Turing")), 1);
		}
		
		@Test
		void testRemoveBad() {
			assertNull(hashBad.remove(new StudentBadHash(1234, "A", "B")));
			assertEquals(hashBad.size(), 4);
			assertEquals(hashBad.remove(new StudentBadHash(1019999, "Alan", "Turing")), 1);
			assertEquals(hashBad.size(), 3);
		}
		
		
		// These test a HashTable with StudentMediumHash keys and Integers values.
		
		@Test
		void testPutMedium() {
			assertNull(hashMedium.put(new StudentMediumHash(1234, "A", "B"), 1));
			assertEquals(hashMedium.size(), 5);
			assertEquals(hashMedium.put(new StudentMediumHash(1234, "A", "B"), 2), 1);
			assertEquals(hashMedium.size(), 5);
		}
				
		@Test
		void testContainsKeyMedium() {
			assertFalse(hashMedium.containsKey(new StudentMediumHash(1234, "A", "B")));
			assertTrue(hashMedium.containsKey(new StudentMediumHash(1019999, "Alan", "Turing")));
		}
				
		@Test
		void testGetMedium() {
			assertNull(hashMedium.get(new StudentMediumHash(1234, "A", "B")));
			assertEquals(hashMedium.get(new StudentMediumHash(1019999, "Alan", "Turing")), 1);
		}
				
		@Test
		void testRemoveMedium() {
			assertNull(hashMedium.remove(new StudentMediumHash(1234, "A", "B")));
			assertEquals(hashMedium.size(), 4);
			assertEquals(hashMedium.remove(new StudentMediumHash(1019999, "Alan", "Turing")), 1);
			assertEquals(hashMedium.size(), 3);
		}
		
		
		// These test a HashTable with StudentGoodHash keys and Integers values.

		@Test
		void testPutGood() {
			assertNull(hashGood.put(new StudentGoodHash(1234, "A", "B"), 1));
			assertEquals(hashGood.size(), 5);
			assertEquals(hashGood.put(new StudentGoodHash(1234, "A", "B"), 2), 1);
			assertEquals(hashGood.size(), 5);
		}
				
		@Test
		void testContainsKeyGood() {
			assertFalse(hashGood.containsKey(new StudentGoodHash(1234, "A", "B")));
			assertTrue(hashGood.containsKey(new StudentGoodHash(1019999, "Alan", "Turing")));
		}
				
		@Test
		void testGetGood() {
			assertNull(hashGood.get(new StudentGoodHash(1234, "A", "B")));
			assertEquals(hashGood.get(new StudentGoodHash(1019999, "Alan", "Turing")), 1);
		}
				
		@Test
		void testRemoveGood() {
			assertNull(hashGood.remove(new StudentGoodHash(1234, "A", "B")));
			assertEquals(hashGood.size(), 4);
			assertEquals(hashGood.remove(new StudentGoodHash(1019999, "Alan", "Turing")), 1);
			assertEquals(hashGood.size(), 3);
		}	
				
}
