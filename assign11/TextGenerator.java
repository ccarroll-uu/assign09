
package assign11;

import java.io.File;

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
