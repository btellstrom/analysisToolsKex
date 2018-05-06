package blackbox;

import java.util.Random;

public class PonePerturb {
	private static Random randomizer = new Random();
	
	public static int randomize(int x) {
		if (randomizer.nextDouble() < 0.5) {
			return x + 1;
		}
		else {
			return x;
		}
	}
}