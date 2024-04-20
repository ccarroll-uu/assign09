package comprehensive;

import java.io.File;

/**
 * This class takes in 4 arguments from the command line. The first specifies the 
 * input file name and the path to that file. The second specifies the seed word.
 * The third specifies k, or how many words to generate from the text file. The fourth
 * argument is optional; if it is given as "one", only the most probable text is generated.
 * If it is "all" or the argument is not provided, it generates random words based on
 * all probabilities.
 * 
 * @author Isabelle Cook and Courtney Carroll
 * @version April 20, 2024
 */
public class TextGenerator {

	public static void main(String[] args) {
		String fileName = args[0];
		String seedWord = args[1];
		int k = Integer.parseInt(args[2]);
		String outputType = null;
		
		if (args.length == 4) {
			outputType = args[3];
		}
		
		TextInput input = new TextInput(new File(fileName));
		
		if (outputType == null || outputType == "all") {
			System.out.println(input.randomText(seedWord, k));
		}
		else {
			System.out.println(input.mostLikelyText(seedWord, k));
		}
		
		

	}

}
