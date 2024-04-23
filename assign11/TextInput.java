package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * This class takes in a text file and generates random text similar to the text in
 * the file.
 * 
 * @author Isabelle Cook and Courtney Carroll
 * @version April 20, 2024
 */
public class TextInput{
	private HashMap<String, HashMap<String, Integer>> table;
	private Graph<String> graph;
	
	/**
	 * Constructor for the TextInput class.
	 * 
	 * @param file - given file.
	 */
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
	private HashMap<String, HashMap<String, Integer>> readFromFile(File file){
		HashMap<String, HashMap<String, Integer>> table = new HashMap<String, HashMap<String, Integer>>();
		String prev = null;
		try {
			Scanner input = new Scanner(file);
	
			// Go through words in text file
			while (input.hasNext()) {
				String word = input.next();
				// Check if word starts with invalid character
				while (input.hasNext() && Character.toString(word.charAt(0)).matches("[^\\w]"))
					word = input.next();
				// Remove invalid characters
				String[] wordSplit = word.split("[^\\w]");
				word = wordSplit[0];
				word = word.toLowerCase();
				
				if (prev == null) {
					prev = word;
				}
				else {
					// Check if word and word after are contained in table
					if (table.containsKey(prev)) {
						HashMap<String, Integer> innerTable = table.get(prev);
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
					
					// Add word and empty inner table to table
					else {
						HashMap<String, Integer> innerTable = new HashMap<String, Integer>();
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
	
	/**
	 * Creates a graph from the given HashMap.
	 * 
	 * @param table - given HashMap
	 * @return a Graph with unique words from the input file.
	 */
	private Graph<String> makeGraph(HashMap<String, HashMap<String, Integer>> table) {
		Graph<String> graph = new Graph<String>();
		Set<String> keys = table.keySet();
		Iterator<String> iter = keys.iterator();
		// Go through all of words in table
		for (int i = 0; i < table.size(); i++) {
			String key = iter.next();
			Set<String> innerKeys = table.get(key).keySet();
			Iterator<String> innerIter = innerKeys.iterator();
			// Go through all of words in inner table
			for(int j = 0; j < table.get(key).size(); j++) {
				String innerKey = innerIter.next();
				// Calculate edge weight
				int count = table.get(key).get(innerKey);
				double weight = count / (double) table.get(key).size();
				// Add edge with word in table going to word in inner table
				graph.addEdge(key, innerKey, weight);
			}
		}
		return graph;	
	}
	
	/**
	 * Generates random text based off the given seed word and word frequency.
	 * The output does not include the seed word. If there are no words connected to the
	 * current word, the function stops generating words.
	 * 
	 * @param seedWord - given seed word
	 * @param k - number of random words to generate
	 * @return - String of randomly generated words
	 */
	public String kMostProbableWords(String seedWord, int k) {
		if (k == 0)
			return "";
		
		Vertex<String> v = graph.getVertex(seedWord);
		String text = "";
		
		// If adjacency list is empty (no next word)
		if (v.getAdj().size() == 0) {
				return text;
			}
		
		// Reverse sort adjacency list
		ArrayList<Edge<String>> adjList = v.getAdj();
		adjList.sort((o1, o2) -> o2.compare(o1, o2));
		Iterator<Edge<String>> iter = adjList.iterator();
		
		// Add k number of words
		for (int i = 0; i < k; i++) {
			if (i >= adjList.size())
				break;
			Edge<String> edge = iter.next();
			
			if (i > 0)
				text += " ";
			text += edge.getDest().getItem();	
		}
		
		return text;
	}
	
	/**
	 * Generates random text based off the given seed word and word frequency.
	 * 
	 * @param seedWord - given seed word
	 * @param k - number of random words to generate
	 * @return - String of randomly generated words
	 */
	public String randomText(String seedWord, int k) {
		if (k == 0)
			return "";
		Random rng = new Random();
		
		Vertex<String> v = graph.getVertex(seedWord);
		String randomText = seedWord;
		
		// Add k number of words
		for (int i = 1; i < k; i++) {
			double number = rng.nextDouble(1);
			// If adjacency list is empty (no next word)
			if (v.getAdj().size() == 0) {
				v = graph.getVertex(seedWord);
				randomText += " " + seedWord;
			}
			// Reverse sort adjacency list
			else {
				ArrayList<Edge<String>> adjList = v.getAdj();
				adjList.sort((o1, o2) -> o2.compare(o1, o2));
				Iterator<Edge<String>> iter = adjList.iterator();
				Edge<String> edge = iter.next();
				double weightSum = edge.getWeight();
				// Use random double to determine which word to generate
				while (iter.hasNext() && weightSum < number) {
					edge = iter.next();
					weightSum += edge.getWeight();
				}
				
				randomText += " " + edge.getDest().getItem();
				v = edge.getDest();
			}	
		}
		
		return randomText;
	}
	
	/**
	 * Generates text based off a seed word. The next word is the word that most
	 * frequently follows the given word. If there is more than one word that most
	 * frequently follows the given word, the word that is first lexicographically
	 * is used. If there is no next word, the seed word is used as the next word.
	 * 
	 * @param seedWord - given seed word. Must be a word within the input file.
	 * @param k - number of words to generate
	 * @return a String of generated words based off frequency
	 */
	public String mostLikelyText(String seedWord, int k) {
		if (k == 0)
			return "";
		Vertex<String> v = graph.getVertex(seedWord);
		String text = seedWord;
		// Add k number of words
		for (int i = 1; i < k; i++) {
			// If adjacency list is empty (no next word)
			if (v.getAdj().size() == 0) {
				v = graph.getVertex(seedWord);
				text += " " + seedWord;
			}
			// Sort list and add next word with highest frequency
			else {
				ArrayList<Edge<String>> adjList = v.getAdj();
				adjList.sort((o1, o2) -> o1.compare(o1, o2));
				Edge<String> edge = adjList.get(0);
				
				text += " " + edge.getDest().getItem();
				v = edge.getDest();
			}
		}
		return text;
	}
	
}
