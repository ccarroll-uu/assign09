
package assign10;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * This class builds a binary max heap.
 * 
 * @param <E> - type of item to be stored in heap
 * 
 * @author Isabelle Cook and Courtney Carroll
 * @version April 4, 2024
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E>{
	private E[] arr;
	private int size;
	private Comparator<? super E> cmp;
	
	/**
	 * Constructor for an empty binary max heap of size 10. Items are
	 * sorted using natural ordering.
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		arr = (E[]) new Object[10];
		size = 0;
		innerCompare(null);
	}
	
	/**
	 * Constructor for an empty binary max heap of size 10. Items are
	 * sorted using comparator specified ordering.
	 * 
	 * @param cmp - given comparator
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		arr = (E[]) new Object[10];
		size = 0;
		innerCompare(cmp);
	}

	/**
	 * Constructor for binary max heap of items in list. Items are
	 * sorted using natural ordering.
	 * 
	 * @param list - given list
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		size = list.size();
		innerCompare(null);
		buildHeap(list);
	}
	
	/**
	 * Constructor for binary max heap of items in list. Items are
	 * sorted using comparator specified ordering.
	 * 
	 * @param list - given list
	 * @param cmp - given comparator
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		size = list.size();
		innerCompare(cmp);
		buildHeap(list);
	}
	
	/**
	 * Private method that builds binary max heap backing array using given list.
	 * 
	 * @param list - given list
	 */
	@SuppressWarnings("unchecked")
	private void buildHeap(List<? extends E> list) {
		arr = (E[]) new Object[list.size()];
		Iterator<? extends E> iter = list.iterator();
		for(int i = 0; i < list.size(); i++)
			arr[i] = iter.next();
		for (int i = (list.size()-1)/2; i > 0; i--)
			percolateDown(arr, i);
	}
	
	/**
	 * Private method that moves item through heap to satisfy
	 * heap order property.
	 * 
	 * @param arr - backing array
	 * @param i - index of item in heap
	 */
	private void percolateUp(E[] arr, int i) {
		while(i != 0 &&  cmp.compare(arr[(i+1)/2 - 1], arr[i]) < 0) {
			swap(arr, i, (i+1)/2 - 1);
			//i = (i+1)/2 - 1;
			i--;
		}
	}
	
	/**
	 * Private method that moves item through heap to satisfy
	 * heap order property.
	 * 
	 * @param arr - backing array
	 * @param i - index of item in heap
	 */
	private void percolateDown(E[] arr, int i) {
		while(2*i+1 < size || 2*i+2 < size) {
			if (2*i + 2 > size) {
				swap(arr, i, 2*i+1);
				i = 2*i+2;
			}
			else if (cmp.compare(arr[2*i+1], arr[2*i+2]) < 0) {
				swap(arr, i, 2*i+2);
				i = 2*i+1;
			}
			else {
				swap(arr, i, 2*i+1);
				i = 2*i+2;
			} 
		}
	}
	
	/**
	 * Private method that determines whether Comparable or Comparator is used.
	 * 
	 * @param cmp - given comparator
	 */
	@SuppressWarnings("unchecked")
	private void innerCompare(Comparator<? super E> cmp) {
		if (cmp == null) {
			this.cmp = (o1, o2) -> ((Comparable<? super E>)o1).compareTo(o2);
		}
		else {
			this.cmp = cmp;	
		}
	}
	
	/**
	 * Private method that swaps two items in array.
	 * 
	 * @param arr - given array
	 * @param i1 - index of first item
	 * @param i2 - index of second item
	 */
	private void swap(E[] arr, int i1, int i2) {
		E temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	/**
	 * Adds item to heap in correct position.
	 * 
	 * @param item - given item
	 */
	@Override
	public void add(E item) {
		arr[size] = item;
		percolateUp(arr, size);
		size++;
	}

	/**
	 * Returns max item of heap.
	 * 
	 * @return max item
	 * @throws NoSuchElementException if heap is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return arr[0];
	}

	/**
	 * Returns and deletes max item of heap.
	 * 
	 * @return max item 
	 * @throws NoSuchElementException if heap is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		
		swap(arr, 0, size - 1);
		percolateDown(arr, 0);
		E temp = arr[size - 1];
		arr[size - 1] = null;
		size--;
		return temp;
	}

	/**
	 * Returns number of items in heap.
	 * 
	 * @returns number of items
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Determines if heap is empty.
	 * 
	 * @returns true if heap is empty and false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Empties heap.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		arr = (E[]) new Object[size];
		size = 0;
	}

	/**
	 * Returns an Object array of the items in the heap, ordered in the
	 * same way as they are in the backing array.
	 * 
	 * @return Object array of heap items
	 */
	@Override
	public Object[] toArray() {
		Object[] outArr = new Object[size];
		for (int i = 0; i < size; i++)
			outArr[i] = arr[i];
		return outArr;
	}

}
