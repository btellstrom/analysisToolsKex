package selectionsort;

import blackbox.MonePerturb;

public class SelectionSortMone6 {

	public static void sort(int[] array) {

		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			
			for (int j = MonePerturb.randomize(i + 1); j < array.length; j++) {
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