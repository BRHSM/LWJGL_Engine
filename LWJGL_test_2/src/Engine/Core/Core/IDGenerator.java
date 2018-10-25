package Engine.Core.Core;

import java.util.ArrayList;
import java.util.Random;

public class IDGenerator {
	
	public static final int MIN_VALUE = 1;
	public static final int MAX_VALUE = Integer.MAX_VALUE;
	
	private ArrayList<Integer> used;
	public IDGenerator() {
		used = new ArrayList<Integer>();
	}
	
	public int generateID() {
		Random rand = new Random();
		int tmp;
		do {
			tmp = rand.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE;
		} while (!used.contains(tmp));
		return tmp;
	}
}
