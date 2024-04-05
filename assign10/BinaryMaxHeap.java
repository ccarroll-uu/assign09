package assign10;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E>{
	private E[] arr;
	private int size;
	private Comparator<? super E> cmp;
	
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
		innerCompare(cmp);
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
	
	private void percolateUp() {
		
	}
	
	private void percolateDown(E[] arr, int index, int size) {
		E temp = arr[size - 1];
		arr[size - 1] = arr[0];
		arr[0] = temp;
		
		
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

