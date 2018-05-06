package selectionsort;

import blackbox.MonePerturb;

public class SelectionSortMone2 {

	public static void sort(int[] array) {

		for (int i = 0; i < array.length - MonePerturb.randomize(1); i++) {
			int index = i;
			
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[index]) {
					index = j;
				}
			}

			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		
		}
	}
}