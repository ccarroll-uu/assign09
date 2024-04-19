
package assign11;

import java.util.Comparator;

/**
 * Represents an edge in a graph.
 * 
 * @author Erin Parker, Courtney Carroll
 * @version March 12, 2024
 */
public class Edge<T> implements Comparator<Edge<T>>{
	private Vertex<T> dest;
	private int weight;
	
	/**
	 * Constructs an edge.
	 * @param dest - the vertex that is the destination of the edge.
	 */
	public Edge(Vertex<T> dest, int weight) {
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
	
	public int getWeight() {
		return this.weight;
	}

	@Override
	public int compare(Edge<T> o1, Edge<T> o2) {
		if (o2.weight - o1.weight == 0)
			return 
		return 0;
	}
}
