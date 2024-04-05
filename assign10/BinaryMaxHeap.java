
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
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		arr = (E[]) new Object[10];
		size = 0;
		innerCompare(cmp);
	}

	public BinaryMaxHeap(List<? extends E> list) {
		buildHeap(list);
		size = list.size();
		innerCompare(null);
	}
	
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		buildHeap(list);
		size = list.size();
		innerCompare(cmp);
	}
	
	@SuppressWarnings("unchecked")
	private void buildHeap(List<? extends E> list) {
		arr = (E[]) new Object[list.size()];
		Iterator<? extends E> iter = list.iterator();
		for(int i = 0; i < list.size(); i++)
			arr[i] = iter.next();
		for (int i = (arr.length-1)/2; i > 0; i--)
			percolateDown(arr, i, arr.length);
	}
	
	private void percolateUp(E[] arr, int i, int size) {
		E temp = arr[i];
		while(cmp.compare(temp, arr[(i+1)/2 - 1]) < 0) {
			if(cmp.compare(arr[i], arr[(i+1)/2 - 1]) > 0) {
				swap(arr, i, (i+1)/2 - 1);
				i = (i+1)/2 - 1;
			}
		}
	}
	
	private void percolateDown(E[] arr, int i, int size) {
		while(2*i+2 < size) {
			if(cmp.compare(arr[i], arr[2*i+1]) < 0) {
				swap(arr, i, 2*i+1);
				i = 2*i+1;
//				arr[i] = arr[2*i+1];
//				arr[2*i+1] = temp;
			}
			else {
				swap(arr, i, 2*i+2);
				i = 2*i+2;
//				arr[i] = arr[2*i+2];
//				arr[2*i+2] = temp;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void innerCompare(Comparator<? super E> cmp) {
		if (cmp == null) {
			this.cmp = (o1, o2) -> ((Comparable<? super E>)o1).compareTo(o2);
		}
		else {
			this.cmp = cmp;	
		}
	}
	
	private void swap(E[] arr, int i1, int i2) {
		E temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	@Override
	public void add(E item) {
		arr[size] = item;
		percolateUp(arr, size, size);
		size++;
	}

	@Override
	public E peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return arr[0];
	}

	@Override
	public E extractMax() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		
		swap(arr, 0, size - 1);
		percolateDown(arr, 0, size);
		E temp = arr[size - 1];
		arr[size - 1] = null;
		size--;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		arr = (E[]) new Object[size];
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] outArr = new Object[size];
		for (int i = 0; i < size; i++)
			outArr[i] = arr[i];
		return outArr;
	}

}

