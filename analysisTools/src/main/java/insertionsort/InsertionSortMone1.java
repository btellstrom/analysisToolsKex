package insertionsort;

public class InsertionSortMone1 {

	public static int[] sort(int[] array) {
		for (int i = 1-1; i < array.length; i++) {	//MONE
			int temp = array[i];
			int j = i - 1;

			while (j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = temp;
		}
		return array;
	}
}