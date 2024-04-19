package assign11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph is not generic and assumes that a string name is stored at each vertex.
 * 
 * @author Erin Parker, Courtney Carroll
 * @version March 12, 2024
 */
public class Graph <T> {

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
	public void addEdge(T item1, T item2, int weight) {
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
		vertex2.setIndegree("add");
	}
	
	public Vertex<T> getVertex(T item) {
		return vertices.get(item);
	}
	
	
	/**
	 * Driver method for recursive depth-first search.
	 * @param current - current vertex
	 * @param goal - vertex to search for
	 * @return - a list containing the path from the original current vertex to the goal vertex,
	 * if one exists; otherwise, returns null.
	 */
	public boolean dfs(T start, T end) throws IllegalArgumentException{
		for (Vertex<T> v : vertices.values()) {
			v.setVisited(false);
		}
		return dfsRecursive(start, end);
	}
	
	/**
	 * Recursive function for depth-first search.
	 * @param current - current vertex.
	 * @param end - the target vertex.
	 * @return boolean describing whether the current vertex is connected to the
	 * goal vertex.
	 */
	public boolean dfsRecursive(T start, T end){
		Vertex<T> current = vertices.get(start);
		Vertex<T> goal = vertices.get(end);
		current.setVisited(true);
		for (Edge<T> e : current.getAdj()) {
			if (e.getDest().getItem().equals(end))
				return true;
			if (!e.getDest().getVisited()) {
				boolean getsToEnd = dfsRecursive(e.getDest().getItem(), goal.getItem());
				//boolean getsToEnd = dfsRecursive(e.getDest().getItem(), end.getItem());
				if (getsToEnd)
					return true;
			}
		}

		return false;
	}
	
	/**
	 * Performs breadth first search to find the shortest path from a start vertex 
	 * to an end vertex.
	 * @param start - the data contained in the vertex to start at.
	 * @param target - the data contained in the vertex to end at.
	 * @return - a List representing the shortest path between the two vertices, if
	 * one exists; otherwise, null.
	 * @throws IllegalArgumentException if there is no vertex in the graph with start 
	 * data, or if there is no vertex in the graph with target data. 
	 */
	public List<T> bfs(T start, T target) throws IllegalArgumentException {
		for (Vertex<T> v : vertices.values()) {
			v.setVisited(false);
		}
		
		if(vertices.get(start).getItem().equals(target)) {
			return makeOutput(vertices.get(start));
		}
		
		
		Vertex<T> current;
		LinkedList<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		queue.offer(vertices.get(start));
		vertices.get(start).setVisited(true);
		
		while (!queue.isEmpty()) {
			current = queue.poll();
			List<Edge<T>> currentEdges = current.getAdj();
			
			for (Edge<T> edge : currentEdges) {
				Vertex<T> dest = edge.getDest();
				
				if (!dest.getVisited()) {
					dest.setVisited(true);
					dest.setCameFrom(current);
					
					if (dest.getItem().equals(target)) {
						return makeOutput(dest);
					}
					queue.offer(dest);
				}
			}
		}
		
			return null;
	}
	
	/**
	 * Returns a list of the vertices sorted in topological order.
	 * @param sources - List of starting vertices
	 * @param destinations - List of vertices that have the matching source vertex as a source
	 * @return - topologically sorted List, if list is the same size as the number of 
	 * edges; otherwise, returns null (if there is a cycle in the graph).
	 */
	public List<T> topologicalSort(List<T> sources, List<T> destinations){
		LinkedList<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		HashMap<Vertex<T>, Integer> indegrees = new HashMap<Vertex<T>, Integer>();
		ArrayList<T> orderedList = new ArrayList<T>();
		
		for (Vertex<T> v : vertices.values()) {
			indegrees.put(v, v.getIndegree());
		}
		
		for (Vertex<T> v : vertices.values()) {
			if (indegrees.get(v) == 0) {
				//System.out.println(v.getItem());
				queue.offer(v);
			}
		}	
			
		while (!queue.isEmpty()) {
			Vertex<T> nextOrdered = queue.poll();
			orderedList.add(nextOrdered.getItem());
				
			for (Edge<T> edge : nextOrdered.getAdj()) {
				Vertex<T> neighbor = edge.getDest();
				Integer newValue = indegrees.get(neighbor) - 1;
				indegrees.put(neighbor, newValue);
				if (indegrees.get(neighbor) == 0) {
					//System.out.println(neighbor.getItem());
					queue.offer(neighbor);
				}
				
			}
		}
		
		if(orderedList.size() < vertices.values().size())
			return null;
		
		return orderedList;
	}
	
	/**
	 * Helper function to make the path by getting the vertex that the goal vertex came
	 * from, the vertex that vertex came from, and so forth.
	 * @param current - the current vertex being looked at (the parameter in this case
	 * is the goal vertex.
	 * @return - ArrayList of the path, in order, from the original start vertex to
	 * the goal vertex.
	 */
	private ArrayList<T> makeOutput(Vertex<T> current) {
		Stack<T> path = new Stack<T>();
		path.add(current.getItem());
		while (current.getCameFrom() != null) {
			current = current.getCameFrom();
			path.add(current.getItem());
		}
		
		ArrayList<T> output = new ArrayList<T>();
		while (!path.isEmpty())
			output.add(path.pop());
		
		return(output);
	}
	
	public Set<T> getVertices(){
		return vertices.keySet();
	}
	
	
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex<T> v : vertices.values()) {
			// for every edge
			Iterator<Edge<T>> edges = v.edges();
			while(edges.hasNext()) 
				//dot.append("\t\"" + v.getName() + "\" -> \"" + edges.next() + "\"\n");
				dot.append("\t\"" + v + "\" -> \"" + edges.next() + "\"\n");
			
		}
		
		return dot.toString() + "}";
	}

	

}
