package analysisTools.analysis;

import java.util.Random;

public class CreateInts {
	private static Random random = new Random();

	public static int[] giveInts(int numberOfInts) {
		int[] thelist = new int[numberOfInts];
		

		for (int i=0; i<numberOfInts; i++) {
			thelist[i]=random.nextInt();
		}
		return thelist;
	}

}