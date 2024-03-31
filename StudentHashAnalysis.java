
package assign09;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class StudentHashAnalysis extends TimerTemplate{
	private HashTable<StudentGoodHash, Integer> hash;
	
	private final static int TIMES_TO_LOOP = 1;   
	private final static int NSTART = 100000;
	private final static int NINCREMENT = 100000;
	private final static int NSTEPS = 20;

	public StudentHashAnalysis(int[] problemSizes, int timesToLoop) {
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
	private StudentGoodHash generateStudent() {
		Random rnd = new Random();
		int uid = rnd.nextInt(10000000);
		String firstName = generateName();
		String lastName = generateName();
		return new StudentGoodHash(uid, firstName, lastName);
	}
	
	private String generateName() {
		String name = "";
		Random rnd = new Random();
		for (int i = 0; i < 5; i++)
			name += (char)(rnd.nextInt(65, 91));
		return name;
	}
	

	@Override
	protected void setup(int n) {
		hash = new HashTable<StudentGoodHash, Integer>();

	}

	@Override
	protected void timingIteration(int n) {
		for (int i = 0; i < n; i++) {
			hash.put(generateStudent(), 1);
		}
		System.out.print("Collisions: " + hash.getCollisions() + " ,");
	}

	@Override
	protected void compensationIteration(int n) {
		generateStudent();
		System.out.print("Collisions: " + hash.getCollisions() + " ,");
	}

	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("00000E00");

		System.out.println("\nN\t|  T(N)/1\tT(N)/logN\tT(N)/N\t\tT(N)/N^2\t\tT(N)/N^3\t\tT(N)/N^4");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		
		int[] problemSizes = new int[NSTEPS];
		problemSizes[0] = NSTART;
		for(int i = 1; i < NSTEPS; i++)
			problemSizes[i] = problemSizes[i - 1] + NINCREMENT;
		
		StudentHashAnalysis checker = new StudentHashAnalysis(problemSizes, TIMES_TO_LOOP);
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

