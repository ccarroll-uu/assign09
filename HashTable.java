package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V>{
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int capacity;
	private int size;
	
	public HashTable() {
		capacity = 10;
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
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		for (int i = 0; i < list.size(); i++) {
			MapEntry<K, V> entry = list.get(i);
			if (entry.getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		int index = value.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		for (int i = 0; i < list.size(); i++) {
			MapEntry<K, V> entry = list.get(i);
			if (entry.getValue().equals(value))
				return true;
		}
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> entries = new ArrayList<MapEntry<K, V>>(size);
		for (int i = 0; i < table.size(); i++) {
			LinkedList<MapEntry<K, V>> list = table.get(i);
			for (int j = 0; j < list.size(); j++)
				entries.add(list.get(j));
		}
		return entries;
	}

	@Override
	public V get(K key) {
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		for (int i = 0; i < list.size(); i++) {
			MapEntry<K, V> entry = list.get(i);
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
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
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		Iterator<MapEntry<K, V>> iter = list.iterator();
		while(iter.hasNext()) {
			MapEntry<K, V> entry = iter.next();
			V toReturn = entry.getValue();
			if (entry.getKey().equals(key)) {
				entry.setValue(value);
				return toReturn;	
			}	
		}
		list.addFirst(new MapEntry<K,V>(key, value));
		
		size++;
		if (size / capacity > 10)
			this.grow();
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
			int index = entries.get(i).getKey().hashCode() % capacity;
			tempTable.get(index).add(entries.get(i));
		}
			
		table = tempTable;
	}

	
	@Override
	public V remove(K key) {
		int index = key.hashCode() % capacity;
		LinkedList<MapEntry<K, V>> list = table.get(index);
		Iterator<MapEntry<K, V>> iter = list.iterator();
		while(iter.hasNext()) {
			MapEntry<K, V> entry = iter.next();
			V toReturn = entry.getValue();
			if (entry.getKey().equals(key)) {
				iter.remove();
				return toReturn;	
			}	
		}
		size--;
		return null;	
	}

	@Override
	public int size() {
		return size;
	}

}
