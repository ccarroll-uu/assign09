
package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
public class TextInput implements Comparator<Map.Entry<String, Integer>>{
	private HashMap<String, HashMap<String, Integer>> table;
	
	/**
	 * The private Node class creates nodes for word frequency.
	 */
	private class Node {
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
		HashMap<String, Integer> innerKeys = table.get(seedWord);
		String text = "";
		
		// If inner key list is empty (no next word)
		if (innerKeys == null) {
				return text;
			}
		
		// Reverse sort inner key list
		Set<Entry<String, Integer>> innerKeySet = innerKeys.entrySet();
		ArrayList<Entry<String, Integer>> innerKeyList = new ArrayList<Entry<String, Integer>>(innerKeySet);
		innerKeyList.sort((Entry<String, Integer> o1, Entry<String, Integer> o2) -> compare(o1, o2));
		
		// Add k number of words
		for (int i = 0; i < k; i++) {
			if (i >= innerKeyList.size())
				break;
			if (i > 0)
				text += " ";
			text += innerKeyList.get(i).getKey();	
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
		
		HashMap<String, Integer> innerKeys = table.get(seedWord);
		String randomText = seedWord;
		
		// Add k number of words
		for (int i = 1; i < k; i++) {
			double number = rng.nextDouble(1);
			// If inner key list is empty (no next word)
			if (innerKeys == null) {
				innerKeys = table.get(seedWord);
				randomText += " " + seedWord;
			}
			// Reverse sort inner key list
			else {
				Set<Entry<String, Integer>> innerKeySet = innerKeys.entrySet();
				ArrayList<Entry<String, Integer>> innerKeyList = new ArrayList<Entry<String, Integer>>(innerKeySet);
				innerKeyList.sort((Entry<String, Integer> o1, Entry<String, Integer> o2) -> compare(o1, o2));

				double weightSum = 0;
				// Use random double to determine which word to generate
				for (int j = 0; j < innerKeyList.size(); j++) {
					weightSum += innerKeyList.get(j).getValue()/ (double) innerKeys.size();
					if (weightSum > number) {
						randomText += " " + innerKeyList.get(j).getKey();
						innerKeys = table.get(innerKeyList.get(j).getKey());
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
		HashMap<String, Integer> innerKeys = table.get(seedWord);
		String text = seedWord;
		// Add k number of words
		for (int i = 1; i < k; i++) {
			// If inner key list is empty (no next word)
			if (innerKeys == null) {
				innerKeys = table.get(seedWord);
				text += " " + seedWord;
			}
			// Sort list and add next word with highest frequency
			else {
				Set<Entry<String, Integer>> innerKeySet = innerKeys.entrySet();
				ArrayList<Entry<String, Integer>> innerKeyList = new ArrayList<Entry<String, Integer>>(innerKeySet);
				innerKeyList.sort((Entry<String, Integer> o1, Entry<String, Integer> o2) -> compare(o1, o2));
				
				text += " " + innerKeyList.get(0).getKey();
				innerKeys = table.get(innerKeyList.get(0).getKey());
			}
		}
		return text;
	}


	/**
	 * Compares two map entries, first by value and second by key.
	 * 
	 * @param o1 - first map entry
	 * @param o2 - second map entry
	 * @return positive number if first entry > second entry; negative number if second  entry > first
	 * entry; if first and second entry are equal, returns 0.
	 */
	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if (o1.getValue() == o2.getValue()) {
			return o1.getKey().compareTo(o2.getKey());
		}
		return o2.getValue() - o1.getValue();
	}
	
}
