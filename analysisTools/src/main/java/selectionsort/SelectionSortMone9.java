package selectionsort;

import blackbox.MonePerturb;

public class SelectionSortMone9 {

	public static void sort(int[] array) {

		for (int i = 0; i < array.length - 1; i++) {
			int index = i;
			
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < MonePerturb.randomize(array[index])) {	
					index = j;
				}
			}

			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		
		}
	}
}