package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class TextInput<T> {

	private Hashtable<T, Hashtable<T, Integer>> table;
	
	public TextInput(File file) {
		table = readFromFile(file);
	}
	
	// Check Java LinkedList
	private Hashtable<T, Hashtable<T, Integer>> readFromFile(File file){
		Hashtable<T, Hashtable<T, Integer>> table = new Hashtable<T, Hashtable<T, Integer>>();
		
		try {
			Scanner input = new Scanner(file);
			
			while (input.hasNext()) {
				String word = input.next();
				word.toLowerCase();
				if (!Character.toString(word.charAt(0)).matches("[^\\w]")) {
					String[] wordSplit = word.split("[^\\w]");
					word = wordSplit[0];
				}
				
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		
	}
}
