package bubblesort;

import blackbox.MonePerturb;

public class BubbleSortMone7 {
	
	public static void sort(int[] array) {
		
		int arraylength = array.length;
		int swap = 0;
		for (int i = 0; i < arraylength; i++) {
			for (int j = 1; j < (arraylength - i); j++) {
				if (array[j - 1] > MonePerturb.randomize(array[j])) {
					swap = array[j - 1];
					array[j - 1] = array[j];
					array[j] = swap;
				}
			}
		}
	}
}
