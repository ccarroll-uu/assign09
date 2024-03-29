
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
}
