package assign11;

import java.util.Comparator;

/**
 * Represents an edge in a graph.
 * 
 * @author Erin Parker, Courtney Carroll
 * @version March 12, 2024
 */
public class Edge <T extends Comparable<T>> implements Comparator<Edge<T>>{
	private Vertex<T> dest;
	private double weight;
	
	/**
	 * Constructs an edge.
	 * @param dest - the vertex that is the destination of the edge.
	 */
	public Edge(Vertex<T> dest, double weight) {
		this.dest = dest;
		this.weight = weight;
	}
	
	/**
	 * Gets the vertex that is the destination of the edge.
	 * @return - vertex that is the destination of the edge.
	 */
	public Vertex<T> getDest(){
		return this.dest;
	}
	
	public double getWeight() {
		return this.weight;
	}

	@Override
	public int compare(Edge<T> o1, Edge<T> o2) {
		if (o1.weight - o2.weight == 0)
			return o1.getDest().getItem().compareTo(o2.getDest().getItem());
		return (int) ((o1.weight - o2.weight) * 1000);
	}
}
