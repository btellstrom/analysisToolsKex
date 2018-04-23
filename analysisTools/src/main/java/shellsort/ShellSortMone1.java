package shellsort;

public class ShellSortMone1 {
	public static void sort(int array[]) {
		int i, j, gap, temp;

		gap = (array.length-1) / 2;			//PONE

		while (gap > 0) {
			i = gap;

			while (i < array.length) {
				temp = array[i];

				for (j = i; (j >= gap) && (array[j - gap] > temp); j -= gap) {
					array[j] = array[j - gap];
				}
				array[j] = temp;

				i++;
			}

			gap = gap / 2;
		}
	}
}