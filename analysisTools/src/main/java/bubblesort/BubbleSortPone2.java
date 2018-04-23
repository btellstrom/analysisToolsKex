package bubblesort;

public class BubbleSortPone2 {

	public static void sort(int[] array) {
		
		int arraylength = array.length;
		int swap = 0;
		for (int i = 0; i+1 < arraylength; i++) { //PONE
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