package mergesort;

import blackbox.MonePerturb;

public class MergeSortMone12 {

    public static void sort(int[] array) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        splitArray(array, leftIndex, rightIndex);
    }

    private static void splitArray(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
                int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            splitArray(array, leftIndex, middleIndex);
            splitArray(array, middleIndex + 1, rightIndex);
            mergeSort(array, leftIndex, middleIndex, rightIndex);
        }
    }

    private static void mergeSort(int[] array, int leftIndex, int middleIndex, int rightIndex) {
        int[] buffer = new int[array.length];

        int k = leftIndex;
        int m = middleIndex + 1;

        for (int i = leftIndex; i <= rightIndex; i++) {
            buffer[i] = array[i];
        }

        for (int i = leftIndex; i <= rightIndex; i++) {
            if (k <= middleIndex && m <= rightIndex) {
                if (buffer[k] < MonePerturb.randomize(buffer[m])) {
                    array[i] = buffer[k];
                    k++;
                } else {
                    array[i] = buffer[m];
                    m++;
                }
            } else if (k <= middleIndex) {
                array[i] = buffer[k];
                k++;
            } else {
                array[i] = buffer[m];
                m++;
            }
        }
    }

}
