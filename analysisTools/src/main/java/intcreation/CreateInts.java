package intcreation;

import java.util.Random;

public class CreateInts {

	public int[] giveInts(int numberOfInts) {
		int[] thelist = new int[numberOfInts];
		Random random = new Random();

		for (int i=0; i<numberOfInts; i++) {
			thelist[i]=random.nextInt();
		}
		return thelist;
	}

}