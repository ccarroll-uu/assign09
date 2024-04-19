package assign11;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * Represents a vertex in a graph.
 * 
 * @author Erin Parker, Courtney Carroll
 * @version March 12, 2024
 */

public class Vertex <T extends Comparable<T>> {
	private T item;
	private LinkedList<Edge<T>> adj;
	private int indegree;
	private boolean visited;
	private Vertex<T> cameFrom;
	
	/**
	 * Constructs a vertex containing the specified item.
	 * @param item - item of type T to be contained in the vertex.
	 */
	public Vertex (T item) {
		this.item = item;
		this.adj = new LinkedList<Edge<T>>();
		this.indegree = 0;
		this.visited = false;
		this.cameFrom = null;
	}
	
	/**
	 * Adds an edge from this vertex to the specified vertex.
	 * @param v - vertex to connect this vertex to with an edge.
	 */
	public void addEdge(Vertex<T> v, double weight) {
		adj.add(new Edge<T>(v, weight));
	}
	
	/**
	 * Sets the indegree of the vertex by either incrementing or decrementing by one.
	 * @param operation - String (either "add" or "subtract" telling whether to 
	 * add or subtract one from the current indegree.
	 * @throws IllegalArgumentException if operation is not either "add" or "subtract."
	 */
	public void setIndegree(String operation) throws IllegalArgumentException {
		if (!operation.equals("add") & !operation.equals("subtract"))
			throw new IllegalArgumentException();
		
		if (operation.equals("add"))
			this.indegree += 1;
		else
			this.indegree -= 1;
	}
	
	/**
	 * Gets the vertex's indegree.
	 * @return the vertex's indegree as an int.
	 */
	public int getIndegree() {
		return this.indegree;
	}
	
	/**
	 * Sets the "visited" attribute of the vertex as either true or false.
	 * @param wasVisited - boolean to set "visited" as.
	 */
	public void setVisited(boolean wasVisited) {
		if (wasVisited)
			this.visited = true;
		else
			this.visited = false;
	}
	
	/**
	 * Gets the "visited" status of the vertex.
	 * @return - boolean describing whether the vertex has been visited or not.
	 */
	public boolean getVisited() {
		return this.visited;
	}
	
	/**
	 * Gets the adjacency list for the vertex, listing the edges the vertex has.
	 * @return - adjacency list of edges.
	 */
	public LinkedList<Edge<T>> getAdj() {
		return adj;
	}
	
	/**
	 * Gets the vertex that this vertex came from (cameFrom status).
	 * @return - the vertex that this vertex came from.
	 */
	public Vertex<T> getCameFrom(){
		return this.cameFrom;
	}
	
	/**
	 * Sets the vertex's cameFrom attribute (the vertex that this vertex came from).
	 * @param v - the vertex that this vertex came from.
	 */
	public void setCameFrom(Vertex<T> v) {
		this.cameFrom = v;
	}
	
	/**
	 * Gets the data of type T stored in the vertex.
	 * @return
	 */
	public T getItem(){
		return this.item;
	}
	
	/**
	 * Returns an iterator that can be used over the adjacency list.
	 * @return - iterator.
	 */
	public Iterator<Edge<T>> edges(){
		return adj.iterator();
	}
}
