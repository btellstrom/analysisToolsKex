package insertionsort;

public class InsertionSortPone2 {

	public static int[] sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j = i - 1;

			while (j >= 0 && array[j]+1 > temp) {	//PONE
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = temp;
		}
		return array;
	}
}