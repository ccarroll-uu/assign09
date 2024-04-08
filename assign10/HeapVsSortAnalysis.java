package assign10;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class HeapVsSortAnalysis extends TimerTemplate {
private ArrayList<Integer> numbers;
private ArrayList<ArrayList<Integer>> numbersList;
private Random rnd;
	
	private final static int TIMES_TO_LOOP = 1;   
	private final static int NSTART = 5;
	private final static int NINCREMENT = 5;
	private final static int NSTEPS = 6;

	public HeapVsSortAnalysis(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
		rnd = new Random();
		numbersList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < NSTEPS; i++) {
			numbers = new ArrayList<Integer>();
			for (int j = 0; j < i*NINCREMENT + NSTART; j++)
				numbers.add(rnd.nextInt(100));
			numbersList.add(numbers);
		}
	}
	

	@Override
	protected void setup(int n) {
		numbers = numbersList.get(n/NINCREMENT - 1);
		
	}

	@Override
	protected void timingIteration(int n) {
		List<Integer> newNumbers = FindKLargest.findKLargestHeap(numbers, rnd.nextInt(n));
		System.out.print(numbers.toString() +  ", " + newNumbers.toString());
	}
	

	@Override
	protected void compensationIteration(int n) {
			rnd.nextInt(n);
	}

	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("00000E00");

		System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2\t\tT(N)/N^3\t\tT(N)/N^4");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		
		int[] problemSizes = new int[NSTEPS];
		problemSizes[0] = NSTART;
		for(int i = 1; i < NSTEPS; i++)
			problemSizes[i] = problemSizes[i - 1] + NINCREMENT;
		
		HeapVsSortAnalysis checker = new HeapVsSortAnalysis(problemSizes, TIMES_TO_LOOP);
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
