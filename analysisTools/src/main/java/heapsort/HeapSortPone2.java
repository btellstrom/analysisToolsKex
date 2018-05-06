package heapsort;

import blackbox.PonePerturb;

public class HeapSortPone2 {
	private static int[] array;
	private static int n, left, right, largest;

	public static void buildheap(int[] array) {
		n = array.length - 1;
		for (int i = PonePerturb.randomize(n / 2); i >= 0; i--) {	
			maxheap(array, i);
		}
	}

	public static void maxheap(int[] array, int i) {
		left = 2 * i;
		right = 2 * i + 1;
		if (left <= n && array[left] > array[i]) {
			largest = left;
		} else {
			largest = i;
		}

		if (right <= n && array[right] > array[largest]) {
			largest = right;
		}
		if (largest != i) {
			exchange(i, largest);
			maxheap(array, largest);
		}
	}

	public static void exchange(int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
	}

	public static void sort(int[] input) {
		array = input;
		buildheap(array);

		for (int i = n; i > 0; i--) {
			exchange(0, i);
			n = n - 1;
			maxheap(array, 0);
		}
	}
}
