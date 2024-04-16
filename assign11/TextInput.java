package assign11;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class TextInput<T> {

	private Hashtable<T, Hashtable<T, Integer>> table;
	
	public TextInput(File file) {
		table = readFromFile(file);
	}
	
	// Check Java LinkedList
	private Hashtable<T, LinkedList<T>> readFromFile(File file){
		Hashtable<T, Hashtable<T, Integer>> table = new Hashtable<T, Hashtable<T, Integer>>();
		
		try {
			Scanner input = new Scanner(file);
		}
		
		catch {
			
		}
	}
}

