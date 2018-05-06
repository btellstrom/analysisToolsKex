package bubblesort;

import blackbox.PonePerturb;

public class BubbleSortPone1 {

	public static void sort(int[] array) {
		
		int arraylength = array.length;
		int swap = PonePerturb.randomize(0);
		for (int i = 0; i < arraylength; i++) {
			for (int j = 1; j < (arraylength - i); j++) {
				if (array[j - 1] > array[j]) {
					swap = array[j - 1];
					array[j - 1] = array[j];
					array[j] = swap;
				}
			}
		}
	}
}