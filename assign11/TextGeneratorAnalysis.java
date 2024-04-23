package comprehensive;

import java.io.File;
import java.text.DecimalFormat;

public class TextGeneratorAnalysis extends TimerTemplate {
	private TextInput input;
	
	private final static int TIMES_TO_LOOP = 1000;   
	private final static int NSTART = 1;
	private final static int NINCREMENT = 200;
	private final static int NSTEPS = 20;

	public TextGeneratorAnalysis(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
		input = new TextInput(new File("src/comprehensive/CMC.txt"), 100000);
	}
	

	@Override
	protected void setup(int n) {
		
	}

	@Override
	protected void timingIteration(int n) {
		input.kMostProbableWords("the", n);
	}

	@Override
	protected void compensationIteration(int n) {
	}

	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("00000E00");

		System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2\t\tT(N)/N^3\t\tT(N)/N^4");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		
		int[] problemSizes = new int[NSTEPS];
		problemSizes[0] = NSTART;
		for(int i = 1; i < NSTEPS; i++)
			problemSizes[i] = problemSizes[i - 1] + NINCREMENT;
		
		TextGeneratorAnalysis checker = new TextGeneratorAnalysis(problemSizes, TIMES_TO_LOOP);
		var results = checker.run();
		
		for(var result : results) {
			int N = result.n();
			double time = result.avgNanoSecs();
			System.out.print(N + "\t|  ");
			
			System.out.println(formatter.format(time) + "\t" + 
					formatter.format(time / (Math.log10(N) / Math.log10(2))) + "\t" + 
					formatter.format(time / N) + "\t" + 
					formatter.format((time / N) / N));
					formatter.format(((time / N) / N) / N);
					formatter.format((((time / N) / N) / N) / N);
		}	
		
		for(var result : results) {
			double time = result.avgNanoSecs();
			System.out.print(time + ", ");
		}	
	}
}
