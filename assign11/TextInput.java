package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

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
					table.put(prev);
				}
				
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		
	}
}
