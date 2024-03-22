package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V>{
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int capacity;
	private int lambda;
	private int size;
	
	public HashTable() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < capacity; i++) {
		   table.add(new LinkedList<MapEntry<K, V>>());
		}	
	}
	
	@Override
	public void clear() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < capacity; i++) {
		   table.add(new LinkedList<MapEntry<K, V>>());
		}
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void grow() {
		ArrayList<LinkedList<MapEntry<K, V>>> tempTable = new ArrayList<LinkedList<MapEntry<K, V>>>();
		capacity *= 2;
		List<MapEntry<K, V>> entries = this.entries();
		
		for (int i = 0; i < capacity; i++) {
			tempTable.add(new LinkedList<MapEntry<K, V>>());
		}
		
		for (int i = 0; i < entries.size(); i++) {
			int index = entries.get(i).getKey().hashCode();
			tempTable.get(index).add(entries.get(i));
		}
			
		table = tempTable;
	}

	
	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

}
