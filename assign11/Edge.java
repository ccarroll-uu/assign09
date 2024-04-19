package assign11;

/**
 * Represents an edge in a graph.
 * 
 * @author Erin Parker, Courtney Carroll
 * @version March 12, 2024
 */
public class Edge<T> {
	private Vertex<T> dest;
	
	/**
	 * Constructs an edge.
	 * @param dest - the vertex that is the destination of the edge.
	 */
	public Edge(Vertex<T> dest) {
		this.dest = dest;
	}
	
	/**
	 * Gets the vertex that is the destination of the edge.
	 * @return - vertex that is the destination of the edge.
	 */
	public Vertex<T> getDest(){
		return this.dest;
	}
}
