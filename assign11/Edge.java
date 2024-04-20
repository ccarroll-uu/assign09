package assign11;

import java.util.Comparator;

/**
 * Represents an edge in a graph.
 * 
 * @author Erin Parker, Courtney Carroll, Isabelle Cook
 * @version April 20, 2024
 */
public class Edge <T extends Comparable<T>> implements Comparator<Edge<T>>{
	private Vertex<T> dest;
	private double weight;
	
	/**
	 * Constructs an edge.
	 * 
	 * @param dest - the vertex that is the destination of the edge.
	 */
	public Edge(Vertex<T> dest, double weight) {
		this.dest = dest;
		this.weight = weight;
	}
	
	/**
	 * Gets the vertex that is the destination of the edge.
	 * 
	 * @return - vertex that is the destination of the edge.
	 */
	public Vertex<T> getDest(){
		return this.dest;
	}
	
	/**
	 * Returns edge weight.
	 * 
	 * @return edge weight
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * Compares two edges by weight, and then lexographically.
	 * 
	 * @param o1 - first edge
	 * @param o2 - second edge
	 * @return positive number if edge weight 1 > edge weight 2; negative number if
	 * edge weight 2 > edge weight 1.
	 */
	@Override
	public int compare(Edge<T> o1, Edge<T> o2) {
		if (o1.weight - o2.weight == 0)
			return o1.getDest().getItem().compareTo(o2.getDest().getItem());
		return (int) ((o1.weight - o2.weight) * 1000);
	}
}
