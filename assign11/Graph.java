package assign11;

import java.util.HashMap;
import java.util.Set;


/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph is not generic and assumes that a string name is stored at each vertex.
 * 
 * @author Erin Parker, Courtney Carroll
 * @version March 12, 2024
 */
public class Graph <T extends Comparable<T>> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<T, Vertex<T>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<T, Vertex<T>>();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(T item1, T item2, double weight) {
		Vertex<T> vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(item1))
			vertex1 = vertices.get(item1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<T>(item1);
			vertices.put(item1, vertex1);
		}

		Vertex<T> vertex2;
		if(vertices.containsKey(item2))
			vertex2 = vertices.get(item2);
		else {
			vertex2 = new Vertex<T>(item2);
			vertices.put(item2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2, weight);
	}
	
	/**
	 * Gets vertex holding given item.
	 * 
	 * @param item - given item
	 * @return vertex with given item
	 */
	public Vertex<T> getVertex(T item) {
		return vertices.get(item);
	}
	
	/**
	 * Returns all vertices in the graph.
	 * 
	 * @return set of unique vertices
	 */
	public Set<T> getVertices(){
		return vertices.keySet();
	}
}
