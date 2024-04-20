package assign11;

import java.util.LinkedList;


/**
 * Represents a vertex in a graph.
 * 
 * @author Erin Parker, Courtney Carroll, and Isabelle Cook
 * @version April 20, 2024
 */

public class Vertex <T extends Comparable<T>> {
	private T item;
	private LinkedList<Edge<T>> adj;
	
	/**
	 * Constructs a vertex containing the specified item.
	 * @param item - item of type T to be contained in the vertex.
	 */
	public Vertex (T item) {
		this.item = item;
		this.adj = new LinkedList<Edge<T>>();
	}
	
	/**
	 * Adds an edge from this vertex to the specified vertex.
	 * @param v - vertex to connect this vertex to with an edge.
	 */
	public void addEdge(Vertex<T> v, double weight) {
		adj.add(new Edge<T>(v, weight));
	}
	
	/**
	 * Gets the adjacency list for the vertex, listing the edges the vertex has.
	 * @return - adjacency list of edges.
	 */
	public LinkedList<Edge<T>> getAdj() {
		return adj;
	}
	
	/**
	 * Gets the data of type T stored in the vertex.
	 * @return
	 */
	public T getItem(){
		return this.item;
	}
}
