package selectionsort;

import blackbox.PonePerturb;

public class SelectionSortPone1 {

	public static void sort(int[] array) {

		for (int i = 0; i < PonePerturb.randomize(array.length) - 1; i++) {
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