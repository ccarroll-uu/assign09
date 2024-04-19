
package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class TextInput {
	private Hashtable<String, Hashtable<String, Integer>> table;
	private Graph<String> graph;
	
	public TextInput(File file) {
		table = readFromFile(file);
		graph = makeGraph(table);
	}
	
	
	/**
	 * Private method that stores frequency of words and their connections from text file.
	 * 
	 * @param file - given file
	 * @return hash table storing the frequency of words their connections
	 */
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
	
	private Graph<String> makeGraph(Hashtable<String, Hashtable<String, Integer>> table) {
		Graph<String> graph = new Graph<String>();
		Enumeration<String> keys = table.keys();
		for (int i = 0; i < table.size(); i++) {
			String key = keys.nextElement();
			Enumeration<String> innerKeys = table.get(key).keys();
			for(int j = 0; j < table.get(key).size(); j++) {
				String innerKey = innerKeys.nextElement();
				int count = table.get(key).get(innerKey);
				int weight = count / table.get(key).size();
				graph.addEdge(key, innerKey, weight);
			}
		}
		return graph;	
	}
	
	public String randomText(String seedWord, int k) {
		Random rng = new Random(1000);
		double number = rng.nextInt()/1000;
		Vertex<String> v = graph.getVertex(seedWord);
		for (int i = 0; i < k; i++) {
			LinkedList<Edge<String>> adjList = v.getAdj();
			adjList.sort((o1, o2) -> o2.getWeight() - o1.getWeight());
			Iterator<Edge<String>> iter = adjList.iterator();
			
			Edge<String> edge = iter.next();
			int weightSum = edge.getWeight();
			while (weightSum < number) {
				edge = iter.next();
				weightSum += edge.getWeight();
			}
		}
		
	}
}
