
package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class TextInput{
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
	
			while (input.hasNext()) {
				String word = input.next();
				while (input.hasNext() && Character.toString(word.charAt(0)).matches("[^\\w]"))
					word = input.next();
				String[] wordSplit = word.split("[^\\w]");
				word = wordSplit[0];
				word = word.toLowerCase();
				
				if (prev == null) {
					prev = word;
				}
				else {
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
					prev = word;
				}
			}
			
			input.close();
		}
		
		catch (FileNotFoundException e) {
			System.err.println("File not found");
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
		if (k == 0)
			return "";
		Random rng = new Random(1000);
		double number = rng.nextInt()/1000;
		Vertex<String> v = graph.getVertex(seedWord);
		String randomText = seedWord + " ";
		for (int i = 1; i < k; i++) {
			if (v.getAdj().size() == 0) {
				v = graph.getVertex(seedWord);
				randomText += seedWord;
			}
			else {
				LinkedList<Edge<String>> adjList = v.getAdj();
				adjList.sort((o1, o2) -> o2.compare(o1, o2));
				Iterator<Edge<String>> iter = adjList.iterator();
				Edge<String> edge = iter.next();
				int weightSum = 0;
				while (iter.hasNext() && weightSum < number) {
					edge = iter.next();
					weightSum += edge.getWeight();
				}
				
				randomText += edge.getDest().getItem();
				v = edge.getDest();
			}
			
			if (i < k - 1)
				randomText += " ";
		}
		
		return randomText;
	}
	
	public String mostLikelyText(String seedWord, int k) {
		if (k == 0)
			return "";
		Vertex<String> v = graph.getVertex(seedWord);
		String text = seedWord + " ";
		for (int i = 1; i < k; i++) {
			if (v.getAdj().size() == 0) {
				v = graph.getVertex(seedWord);
				text += seedWord;
			}
			
			else {
				LinkedList<Edge<String>> adjList = v.getAdj();
				adjList.sort((o1, o2) -> o1.compare(o1, o2));
				Edge<String> edge = adjList.getFirst();
				
				text += edge.getDest().getItem();
				v = edge.getDest();
			}
			
			if (i < k - 1)
				text += " ";
		}
		return text;
	}
	
}
