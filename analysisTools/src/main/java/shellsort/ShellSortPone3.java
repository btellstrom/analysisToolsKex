package shellsort;

import blackbox.PonePerturb;

public class ShellSortPone3 {
	public static void sort(int array[]) {
		int i, j, gap, temp;

		gap = PonePerturb.randomize(array.length / 2);

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