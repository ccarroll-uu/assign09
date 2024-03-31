package assign09;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;


public class HashMapAnalysis extends TimerTemplate {
private HashTable<String, Integer> hash;
private HashMap<String, Integer> map;
	
	private final static int TIMES_TO_LOOP = 1000;   
	private final static int NSTART = 10000;
	private final static int NINCREMENT = 10000;
	private final static int NSTEPS = 20;

	public HashMapAnalysis(int[] problemSizes, int timesToLoop) {
		super(problemSizes, timesToLoop);
	}
	
//	private HashTable<StudentBadHash, Integer> generateHashRandom(int size) {
//		ArrayList<Integer> list = new ArrayList<Integer>(size);
//	    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
//	    for (int i = 0; i < size; i++) {
//	    	list.add(i);
//	    }
//	    Collections.shuffle(list);
//	    for (int i = 0; i < size; i++) {
//	    	bst.add(list.get(i));
//	    }
//		return bst;
//	}
	
	private String generateName() {
		String name = "";
		Random rnd = new Random();
		for (int i = 0; i < 10; i++)
			name += (char)(rnd.nextInt(65, 91));
		return name;
	}
	

	@Override
	protected void setup(int n) {
		hash = new HashTable<String, Integer>();
		map = new HashMap<String, Integer>();

	}

	@Override
	protected void timingIteration(int n) {
		for (int i = 0; i < n; i++) {
			//hash.put(generateName(), 1);
			map.put(generateName(), 1);
		}
	}

	@Override
	protected void compensationIteration(int n) {
		generateName();
	}

	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("00000E00");

		System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2\t\tT(N)/N^3\t\tT(N)/N^4");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		
		int[] problemSizes = new int[NSTEPS];
		problemSizes[0] = NSTART;
		for(int i = 1; i < NSTEPS; i++)
			problemSizes[i] = problemSizes[i - 1] + NINCREMENT;
		
		HashMapAnalysis checker = new HashMapAnalysis(problemSizes, TIMES_TO_LOOP);
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
