package assign10;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;


public class PriorityQueueAnalysis extends TimerTemplate {
private BinaryMaxHeap<Integer> heap;
private Random rnd;
	
	private final static int TIMES_TO_LOOP = 10;   
	private final static int NSTART = 10;
	private final static int NINCREMENT = 10;
	private final static int NSTEPS = 20;

	public PriorityQueueAnalysis(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
	}
	

	@Override
	protected void setup(int n) {
		heap = new BinaryMaxHeap<Integer>();
		rnd = new Random();
	}

	@Override
	protected void timingIteration(int n) {
		for (int i = 0; i < n; i++) {
			heap.add(rnd.nextInt());
		}
		for (int i = 0; i < n; i++) {
			heap.extractMax();
		}
		heap.clear();
	}

	@Override
	protected void compensationIteration(int n) {
		for (int i = 0; i < n; i++) {
			rnd.nextInt();
		}
		heap.clear();
	}

	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("00000E00");

		System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2\t\tT(N)/N^3\t\tT(N)/N^4");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		
		int[] problemSizes = new int[NSTEPS];
		problemSizes[0] = NSTART;
		for(int i = 1; i < NSTEPS; i++)
			problemSizes[i] = problemSizes[i - 1] + NINCREMENT;
		
		PriorityQueueAnalysis checker = new PriorityQueueAnalysis(problemSizes, TIMES_TO_LOOP);
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
