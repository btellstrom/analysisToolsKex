package analysisTools.analysedPrograms;

public class QuickSortMONE {
	public static void sort(int[] array, int beg, int end) {

		int left = beg, right = end;
		int pivot = array[beg];

		while ((left) <= (right)) {

			while (array[left] < pivot) {
				left++ ;
			}

			while (array[right] > (pivot)) {
				right--;
			}

			if ((left)<= right) {
				swap(array, left, right);
				left++;
				right--;
			}	

		}
		if ((beg) < (right))
			sort(array, beg, right);
		if (end > left)
			sort(array, left, end);
	}

	private static void swap(int[] array, int i, int j) {
		int x = array[i];
		array[i] = array[j];
		array[j] = x;
	}

}