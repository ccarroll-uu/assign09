package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {
	private HashTable<String, Integer> emptyHashStr;
	private HashTable<String, Integer> hashStr;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyHashStr = new HashTable<String, Integer>();
		hashStr = new HashTable<String, Integer>();
	}
	
	@Test
	void testClearEmpty() {
		assertEquals(emptyHashStr.size(), 0);
		emptyHashStr.clear();
		assertEquals(emptyHashStr.size(), 0);
	}
	
	@Test
	void testPutEmpty() {
		assertNull(emptyHashStr.put("A", 1));
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(emptyHashStr.isEmpty());
	}
	
	@Test
	void testSizeEmpty() {
		assertEquals(emptyHashStr.size(), 0);
	}

}

