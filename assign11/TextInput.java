package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

import assign07.Vertex;

public class TextInput {

	private Hashtable<String, Hashtable<String, Integer>> table;
	
	public TextInput(File file) {
		table = readFromFile(file);
	}
	
	
	
	// Check Java LinkedList
	private Hashtable<String, Hashtable<String, Integer>> readFromFile(File file){
		Hashtable<String, Hashtable<String, Integer>> table = new Hashtable<String, Hashtable<String, Integer>>();
		String prev = null;
		try {
			Scanner input = new Scanner(file);
			if (input.hasNext())
				prev = input.next();
			
			while (input.hasNext()) {
				String word = input.next();
				word.toLowerCase();
				if (!Character.toString(word.charAt(0)).matches("[^\\w]")) {
					String[] wordSplit = word.split("[^\\w]");
					word = wordSplit[0];
				}
				else
					break;
				
				if (table.containsKey(prev)) {
					Hashtable<String, Integer> innerTable = table.get(prev);
					if (innerTable.containsKey(word)) {
						int count = innerTable.get(word);
						innerTable.put(word, count++);
						table.put(prev, innerTable);
					}
					else {
						innerTable.put(word, 1);
						table.put(prev, innerTable);
					}
				}
				
				else {
					Hashtable<String, Integer> innerTable = new Hashtable<String, Integer>();
					innerTable.put(word, 1);
					table.put(prev, innerTable);
				}
				
			}
			input.close();
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		return table;
	}
	
	private makeGraph(Hashtable<String, Hashtable<String, Integer>> table) {
		
	}
	
	public void addEdge(String prev, String word) {
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
		vertex1.addEdge(vertex2);
		vertex2.setIndegree("add");
	}
}
