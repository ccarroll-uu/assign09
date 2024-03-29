
package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Generic class creates HashTable with MapEntries with keys and values
 * that utilizes separate chaining for collisions.
 * 
 * @param <K> - data type of keys
 * @param <V> - data type of values
 * 
 * @author Isabelle Cook and Courtney Carroll
 * @version March 29, 2024
 */
public class HashTable<K, V> implements Map<K, V>{
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int capacity;
	private int size;
	
	/**
	 * Creates an empty HashTable of capacity of one.
	 */
	public HashTable() {
		capacity = 1;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>(capacity);
		for (int i = 0; i < capacity; i++) {
		   table.add(new LinkedList<MapEntry<K, V>>());
		}
	}
	
	/**
	 * Clears map entries in table
	 */
	@Override
	public void clear() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < capacity; i++) {
		   table.add(new LinkedList<MapEntry<K, V>>());
		}
		size = 0;
	}

	/**
	 * Checks if table contains given key.
	 * 
	 * @param key - given key
	 * @return returns true if table contains key and false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		Iterator<MapEntry<K, V>> iter = list.iterator();
		// Checking if key exists
		while (iter.hasNext()) {
			MapEntry<K, V> entry = iter.next();
			if (entry.getKey().equals(key))
				return true;
		}
		return false;
	}

	/**
	 * Checks if table contains given value.
	 * 
	 * @param value - given vale
	 * @return returns true if table contains value and false otherwise
	 */
	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < table.size(); i++) {
			LinkedList<MapEntry<K, V>> list = table.get(i);
			Iterator<MapEntry<K, V>> iter = list.iterator();
			// Checking if value exists
			while (iter.hasNext()) {
				MapEntry<K, V> entry = iter.next();
				if (entry.getValue().equals(value))
					return true;
			}
		}
		return false;
	}

	/**
	 * Creates a list of map entries in table.
	 * 
	 * @return list of map entries
	 */
	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> entries = new ArrayList<MapEntry<K, V>>(size);
		// Adding entries for table to list
		for (int i = 0; i < table.size(); i++) {
			LinkedList<MapEntry<K, V>> list = table.get(i);
			Iterator<MapEntry<K, V>> iter = list.iterator();
			while (iter.hasNext())
				entries.add(iter.next());
		}
		return entries;
	}

	/**
	 * Gets value of given key if it is in table.
	 * 
	 * @param key - given key
	 * @return value of given key if it exists in table and null otherwise
	 */
	@Override
	public V get(K key) {
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		// Checking if value exists
		for (int i = 0; i < list.size(); i++) {
			MapEntry<K, V> entry = list.get(i);
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
		return null;
	}

	/**
	 * Checks if table is empty.
	 * 
	 * @return true if table is empty and false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Adds map entry with key and value if key does not exist. If key exists,
	 * the value for that key is overridden.
	 * 
	 * @param key - given key
	 * @param value - given value
	 * @return original value if key is overridden and null otherwise
	 */
	@Override
	public V put(K key, V value) {
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		Iterator<MapEntry<K, V>> iter = list.iterator();
		// Checking if key exists and overriding value
		while(iter.hasNext()) {
			MapEntry<K, V> entry = iter.next();
			V toReturn = entry.getValue();
			if (entry.getKey().equals(key)) {
				entry.setValue(value);
				return toReturn;	
			}	
		}
		// Adding entry if key does not exist
		list.addFirst(new MapEntry<K,V>(key, value));
		
		size++;
		// Growing backing array if lambda > 10
		if (size / capacity > 10)
			this.grow();
		return null;
	}
	
	/**
	 * Private method that doubles backing array if lambda > 10.
	 */
	private void grow() {
		// Doubling capacity and storing entries
		capacity *= 2;
		ArrayList<LinkedList<MapEntry<K, V>>> tempTable = new ArrayList<LinkedList<MapEntry<K, V>>>(capacity);
		List<MapEntry<K, V>> entries = this.entries();
		for (int i = 0; i < capacity; i++) {
			tempTable.add(new LinkedList<MapEntry<K, V>>());
		}
		
		// Rehashing and inserting entries
		for (int i = 0; i < entries.size(); i++) {
			int index = entries.get(i).getKey().hashCode() % capacity;
			tempTable.get(index).add(entries.get(i));
		}
		
		// Resetting table with new capacity
		table = tempTable;
	}

	/**
	 * Removes entry with given key from table if it exists.
	 * 
	 * @param key - given key
	 * @return value of key removed and null otherwise
	 */
	@Override
	public V remove(K key) {
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		Iterator<MapEntry<K, V>> iter = list.iterator();
		// Checking if key is in table and removing it
		while(iter.hasNext()) {
			MapEntry<K, V> entry = iter.next();
			V toReturn = entry.getValue();
			if (entry.getKey().equals(key)) {
				iter.remove();
				size--;
				return toReturn;	
			}	
		}
		return null;	
	}

	/**
	 * Returns number of entries in table.
	 * 
	 * @return number of entries
	 */
	@Override
	public int size() {
		return size;
	}
}
