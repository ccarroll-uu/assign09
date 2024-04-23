package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * This class takes in a text file and generates random text similar to the text in
 * the file.
 * 
 * @author Isabelle Cook and Courtney Carroll
 * @version April 23, 2024
 */
public class TextInput {
	private HashMap<String, ArrayList<Node>> table;
	
	/**
	 * The private Node class creates nodes for word frequency.
	 */
	private class Node implements Comparator<Node>{
		public String data;
		public int frequency;
		
		/**
		 * Creates a Node that stores data and frequency.
		 * 
		 * @param data - data in node
		 * @param leftChild - left child node
		 * @param rightChild - right child node
		 * @param parent - parent node
		 */
		public Node(String data, int frequency) {
			this.data = data;
			this.frequency = frequency;
		}

		/**
		 * Compares two Nodes, first by frequency and then by data.
		 * 
		 * @param o1 - first Node
		 * @param o2 - second Node
		 * @return positive number if first Node > second Node; negative number if second Node > first
		 * Node; if first and second Node are equal, returns 0.
		 */
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.frequency == o2.frequency) {
				return o1.data.compareTo(o2.data);
			}
			return o2.frequency - o1.frequency;
		}
		
		public boolean equals(Object o) {
			if (!(o instanceof Node)) {
	            return false;
	        }
			
			Node other = (Node) o;
			return this.data.equals(other.data) && (this.frequency == other.frequency);
		}
	}
	
	/**
	 * Constructor for the TextInput class.
	 * 
	 * @param file - given file.
	 */
	public TextInput(File file) {
		table = readFromFile(file);
	}
	
	
	/**
	 * Private method that stores frequency of words and their connections from text file.
	 * 
	 * @param file - given file
	 * @return hash table storing the frequency of words their connections
	 */
	private HashMap<String, ArrayList<Node>> readFromFile(File file){
		HashMap<String, ArrayList<Node>> table = new HashMap<String, ArrayList<Node>>();
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
					ArrayList<Node> nodeList = new ArrayList<Node>();
					nodeList.add(new Node(word, 1));
					table.put(prev, nodeList);
					
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
		ArrayList<Node> nodeList = table.get(seedWord);
		String text = "";
		
		// If inner key list is empty (no next word)
		if (nodeList == null) {
				return text;
			}
		
		// Reverse sort inner key list
		nodeList.sort((o1, o2) -> o1.compare(o1, o2));
		
		// Add k number of words
		for (int i = 0; i < k; i++) {
			if (i >= nodeList.size())
				break;
			if (i > 0)
				text += " ";
			text += nodeList.get(i).data;	
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
		
		ArrayList<Node> nodeList = table.get(seedWord);
		String randomText = seedWord;
		
		
		// Add k number of words
		for (int i = 1; i < k; i++) {
			double number = rng.nextDouble(1);
			// If inner key list is empty (no next word)
			if (nodeList == null) {
				nodeList = table.get(seedWord);
				randomText += " " + seedWord;
			}
			// Reverse sort inner key list
			else {
				double weightSum = 0;
				// Use random double to determine which word to generate
				for (int j = 0; j < nodeList.size(); j++) {
					weightSum += nodeList.get(j).frequency/ (double)nodeList.size();
					if (weightSum > number) {
						randomText += " " + nodeList.get(j).data;
						nodeList = table.get(nodeList.get(j).data);
						break;
					}	
				}
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
		ArrayList<Node> nodeList = table.get(seedWord);
		String text = seedWord;
		// Add k number of words
		for (int i = 1; i < k; i++) {
			// If inner key list is empty (no next word)
			if (nodeList == null) {
				nodeList = table.get(seedWord);
				text += " " + seedWord;
			}
			// Sort list and add next word with highest frequency
			else {
				nodeList.sort((o1, o2) -> o1.compare(o1, o2));
				text += " " + nodeList.get(0).data;
				nodeList = table.get(nodeList.get(0).data);
			}
		}
		return text;
	}

	
}
