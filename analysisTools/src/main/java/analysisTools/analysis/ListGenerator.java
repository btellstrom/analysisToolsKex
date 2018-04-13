package analysisTools.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ListGenerator {
	
	private static DescendingOrder order = new DescendingOrder();
	
	public static int[] generateList(int length, int index) {
		int[] list = new int[length];
		int[] ret = new int[length];
		Random rand = new Random();
		
		for(int i = 0;i < length; i++) {
			list[i] = rand.nextInt();
		}
		ret = sortList(list, index);
		return ret;
	}
	
	private static int[] sortList(int[] list, int i) {
		int[] ret = new int[list.length];
		if (i == 0) {
			ret = sortListDescendingOrder(list);
		}
		else if(i == 1) {
			ret = sortWorstCaseMiddle(list);
		}
		else if(i ==2) {
			for (int j = 1; j<list.length; j++) {
				ret[j] = 1;
			}
		}
		else {
			ret = list;
		}
		return ret;
	}
	
	
	public static class DescendingOrder implements Comparator<Integer> {
		public int compare(Integer a, Integer b) {
			return b.compareTo(a);
		}
	}
	private static int[] sortListDescendingOrder(int[] list) {
		int[] ret = new int[list.length];
		Integer[] sorted = Arrays.stream(list).boxed().toArray( Integer[]::new );
		Arrays.sort(sorted, order);
		for (int i = 0; i<list.length; i++) {
			ret[i] = sorted[i].intValue();
		}
		return ret;
	}
	private static int[] sortWorstCaseMiddle (int[] list) {
		int[] ret = new int[list.length];
		ArrayList<Integer> sorted = new ArrayList<Integer>(list.length);
		for (int i = 0; i < list.length; i++) {
			sorted.add(list[i]);
		}
		Collections.sort(sorted);
		for (int i = 1; i <list.length; i++) {
			ret[i] = sorted.get(i).intValue();
		}
		int largest = ret[list.length-1];
		ret[list.length/2] = largest;
		return ret;
	}
}
