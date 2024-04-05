package assign10;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E>{
	private E[] arr;
	private int size;
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		arr = (E[]) new Object[10];
		size = 0;
		// add inner compare?
	}
	
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		arr = (E[]) new Object[10];
		size = 0;
		// add inner compare?
	}

	public BinaryMaxHeap(List<? extends E> list) {
		buildHeap(list);
		size = list.size();
		// add inner compare?
	}
	
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		buildHeap(list);
		size = list.size();
		// add inner compare?
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
	
	private void percolateUp() {
		
	}
	
	private void percolateDown(E[] arr, int index, int size) {
		
	}
	
	private int innerCompare() {
		
	}
	
	@Override
	public void add(E item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}

