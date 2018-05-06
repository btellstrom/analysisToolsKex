package analysisTools.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import quicksort.*;
import bubblesort.*;
import heapsort.*;
import insertionsort.*;
import mergesort.*;
import oracle.Oracle;
import selectionsort.*;
import shellsort.*;

public class SpeedAnalysis {
	private int length;
	
	public SpeedAnalysis(int listLength) {
		length = listLength;
	}
	public SpeedAnalysis() {
		this(10000);
	}
	
	/**
	 * Analyzes the time it takes to sort a set number of randomly generated lists, and 
	 * then returns the list as well as the time it took to sort it.
	 * @param numberOfIterations the number of lists to be sorted
	 * @return a list of objects of the class DataClass
	 */

	
	public ArrayList<DataClass> runComparison(int numberOfIterations){
		int[] randomList = new int[length];
		
		long time;
		
		ArrayList<DataClass> analysis = new ArrayList<DataClass>(numberOfIterations);
		for(int i = 0; i < numberOfIterations; i++) {
			randomList = CreateInts.giveInts(length);

			time = timeToSort(randomList);
		
			analysis.add(new DataClass(time, randomList));
		}
		return analysis;
	}
	
	/**
	 * Takes a sorted list of objects of the DataClass class, where the sorting is based 
	 * on time to sort each list in the given DataClass object. Takes the last 10% of 
	 * the lists and compares average time to sort those lists.
	 * @param sortedList the list of DataClass objects
	 * @param nrOfIterations the number of times to sort each list
	 * @return a list of DataClass objects with average time and list.
	 * @throws IOException 
	 */
	public ArrayList<DataClass> runDeepAnalysis(List<DataClass> sortedList){
		ArrayList<DataClass> ret = new ArrayList<DataClass>(sortedList.size());
		
		for(int i = 0; i < sortedList.size(); i++) {
			ret.add(new DataClass(timeToSort(sortedList.get(i).getList()), sortedList.get(i).getList()));
		}
		return ret;
		
	}
	
	public ArrayList<int[]> getSortedByOriginal(ArrayList<int[]> lists, long[] times){
		ArrayList<DataClass> pairSorting = new ArrayList<DataClass>(times.length);
		ArrayList<int[]> ret = new ArrayList<int[]>(times.length);
		for(int i = 0; i < times.length; i++) {
			pairSorting.add(new DataClass(times[i], lists.get(i)));
		}
		Collections.sort(pairSorting);
		
		for(int i = 0; i < times.length; i++) {
			ret.add(pairSorting.get(i).getList());
		}
		return ret;
	}
	
	/**
	 * Measures the time it takes to sort a list.
	 * @param oList the list to sort.
	 * @return the time taken to sort oList.
	 * @throws IOException 
	 */
	public long timeToSort(int[] oList){
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		
		startTime = System.nanoTime();
		for( int j = 1; j < 5000; j++ ) {
			
			int[] list = new int[oList.length];
			//System.arraycopy(list, 0, oList, 0,  oList.length);
			for(int i = 0; i < oList.length; i++) {
				list[i] = oList[i];
			}

			QuickSort.sort(list, 0, list.length-1);
		}
		endTime = System.nanoTime();
		
		return endTime - startTime;
	}
	public long timeToSort(int[] oList, String algorithm){
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		
		startTime = System.nanoTime();
		for( int j = 1; j < 5000; j++ ) {
			
			int[] list = new int[oList.length];
			//System.arraycopy(list, 0, oList, 0,  oList.length);
			for(int i = 0; i < oList.length; i++) {
				list[i] = oList[i];
			} //QuickSort first. This loop will have constant time for the timemeasurement... Hmm, one if-statement more
			if(algorithm.equals("QuickSort")) {
				QuickSort.sort(list, 0, list.length-1);
				//Mone-algorithms
			}else if(algorithm.equals("QuickSortMone1")) {
				QuickSortMone1.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone2")) {
				QuickSortMone2.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone3")) {
				QuickSortMone3.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone4")) {
				QuickSortMone4.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone5")) {
				QuickSortMone6.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone6")) {
				QuickSortMone7.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone7")) {
				QuickSortMone9.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone8")) {
				QuickSortMone14.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone9")) {
				QuickSortMone15.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortMone0")) {
				QuickSortMone16.sort(list, 0, list.length-1);
				//Pone-algorithms
			}else if(algorithm.equals("QuickSortPone1")) {
				QuickSortPone1.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone2")) {
				QuickSortPone2.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone3")) {
				QuickSortPone3.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone4")) {
				QuickSortPone4.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone5")) {
				QuickSortPone5.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone6")) {
				QuickSortPone6.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone7")) {
				QuickSortPone7.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone8")) {
				QuickSortPone8.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone9")) {
				QuickSortPone10.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone10")) {
				QuickSortPone12.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone11")) {
				QuickSortPone17.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone12")) {
				QuickSortPone18.sort(list, 0, list.length-1);
			}else if(algorithm.equals("QuickSortPone13")) {
				QuickSortPone19.sort(list, 0, list.length-1);
			}
			
			// BubbleSort
			else if(algorithm.equals("BubbleSort")) {
				BubbleSort.sort(list);
				//Mone algorithms
			}else if(algorithm.equals("BubbleSortMone1")) {
				BubbleSortMone1.sort(list);
			}else if(algorithm.equals("BubbleSortMone2")) {
				BubbleSortMone3.sort(list);
			}else if(algorithm.equals("BubbleSortMone3")) {
				BubbleSortMone6.sort(list);
			}else if(algorithm.equals("BubbleSortMone4")) {
				BubbleSortMone7.sort(list);
				//Pone algorithms
			}else if(algorithm.equals("BubbleSortPone1")) {
				BubbleSortPone1.sort(list);
			}else if(algorithm.equals("BubbleSortPone2")) {
				BubbleSortPone2.sort(list);
			}else if(algorithm.equals("BubbleSortPone3")) {
				BubbleSortPone3.sort(list);
			}else if(algorithm.equals("BubbleSortPone4")) {
				BubbleSortPone5.sort(list);
			}else if(algorithm.equals("BubbleSortPone5")) {
				BubbleSortPone6.sort(list);
			}
			
			
			//heapsort
			else if(algorithm.equals("HeapSort")) {
				HeapSort.sort(list);
				//Mone algorithms
			}else if(algorithm.equals("HeapSortMone1")) {
				HeapSortMone1.sort(list);
			}else if(algorithm.equals("HeapSortMone2")) {
				HeapSortMone2.sort(list);
			}else if(algorithm.equals("HeapSortMone3")) {
				HeapSortMone4.sort(list);
			}else if(algorithm.equals("HeapSortMone4")) {
				HeapSortMone5.sort(list);
			}else if(algorithm.equals("HeapSortMone5")) {
				HeapSortMone6.sort(list);
			}else if(algorithm.equals("HeapSortMone6")) {
				HeapSortMone7.sort(list);
			}else if(algorithm.equals("HeapSortMone7")) {
				HeapSortMone8.sort(list);
			}else if(algorithm.equals("HeapSortMone8")) {
				HeapSortMone10.sort(list);
				//Pone algorithms
			}else if(algorithm.equals("HeapSortPone1")) {
				HeapSortPone1.sort(list);
			}else if(algorithm.equals("HeapSortPone2")) {
				HeapSortPone2.sort(list);
			}else if(algorithm.equals("HeapSortPone3")) {
				HeapSortPone5.sort(list);
			}else if(algorithm.equals("HeapSortPone4")) {
				HeapSortPone6.sort(list);
			}else if(algorithm.equals("HeapSortPone5")) {
				HeapSortPone7.sort(list);
			}else if(algorithm.equals("HeapSortPone6")) {
				HeapSortPone8.sort(list);
			}else if(algorithm.equals("HeapSortPone7")) {
				HeapSortPone10.sort(list);
			}
			
			//Insertion sort
			else if(algorithm.equals("InsertionSort")) {
				InsertionSort.sort(list);
				//Mone algorithms
			}else if(algorithm.equals("InsertionSortMone1")) {
				InsertionSortMone1.sort(list);
			}else if(algorithm.equals("InsertionSortMone2")) {
				InsertionSortMone5.sort(list);
				//Pone algorithms
			}else if(algorithm.equals("InsertionSortPone1")) {
				InsertionSortPone2.sort(list);
			}else if(algorithm.equals("InsertionSortMone2")) {
				InsertionSortPone3.sort(list);
			}
			
			//merge sort
			else if(algorithm.equals("MergeSort")) {
				MergeSort.sort(list);
				//Mone algorithms
			}else if(algorithm.equals("MergeSortMone1")) {
				MergeSortMone3.sort(list);
			}else if(algorithm.equals("MergeSortMone2")) {
				MergeSortMone5.sort(list);
			}else if(algorithm.equals("MergeSortMone3")) {
				MergeSortMone11.sort(list);
			}else if(algorithm.equals("MergeSortMone4")) {
				MergeSortMone12.sort(list);
				// Pone algorithms
			}else if(algorithm.equals("MergeSortPone1")) {
				MergeSortPone4.sort(list);
			}else if(algorithm.equals("MergeSortPone2")) {
				MergeSortPone10.sort(list);
			}else if(algorithm.equals("MergeSortPone3")) {
				MergeSortPone13.sort(list);
			}else if(algorithm.equals("MergeSortPone4")) {
				MergeSortPone14.sort(list);
			}
			
			//Selection sort
			else if(algorithm.equals("SelectionSort")) {
				SelectionSort.sort(list);
				//Mone algorithms
			}else if(algorithm.equals("SelectionSortMone1")) {
				SelectionSortMone1.sort(list);
			}else if(algorithm.equals("SelectionSortMone2")) {
				SelectionSortMone2.sort(list);
			}else if(algorithm.equals("SelectionSortMone3")) {
				SelectionSortMone4.sort(list);
			}else if(algorithm.equals("SelectionSortMone4")) {
				SelectionSortMone5.sort(list);
			}else if(algorithm.equals("SelectionSortMone5")) {
				SelectionSortMone6.sort(list);
			}else if(algorithm.equals("SelectionSortMone6")) {
				SelectionSortMone8.sort(list);
			}else if(algorithm.equals("SelectionSortMone7")) {
				SelectionSortMone9.sort(list);
				//Pone algorithms
			}else if(algorithm.equals("SelectionSortPone1")) {
				SelectionSortPone1.sort(list);
			}else if(algorithm.equals("SelectionSortPone2")) {
				SelectionSortPone2.sort(list);
			}else if(algorithm.equals("SelectionSortPone3")) {
				SelectionSortPone5.sort(list);
			}else if(algorithm.equals("SelectionSortPone4")) {
				SelectionSortPone6.sort(list);
			}
			
			//Shell sort
			else if(algorithm.equals("ShellSort")) {
				ShellSort.sort(list);
				//Mone algorithms
			}else if(algorithm.equals("ShellSortMone1")) {
				ShellSortMone1.sort(list);
			}else if(algorithm.equals("ShellSortMone2")) {
				ShellSortMone2.sort(list);
			}else if(algorithm.equals("ShellSortMone3")) {
				ShellSortMone3.sort(list);
			}else if(algorithm.equals("ShellSortMone4")) {
				ShellSortMone5.sort(list);
			}else if(algorithm.equals("ShellSortMone5")) {
				ShellSortMone7.sort(list);
			}else if(algorithm.equals("ShellSortMone6")) {
				ShellSortMone8.sort(list);
				//Pone algorithms
			}else if(algorithm.equals("ShellSortPone1")) {
				ShellSortPone1.sort(list);
			}else if(algorithm.equals("ShellSortPone2")) {
				ShellSortPone2.sort(list);
			}else if(algorithm.equals("ShellSortPone3")) {
				ShellSortPone3.sort(list);
			}else if(algorithm.equals("ShellSortPone4")) {
				ShellSortPone5.sort(list);
			}else if(algorithm.equals("ShellSortPone5")) {
				ShellSortPone6.sort(list);
			}
			
		}
		endTime = System.nanoTime();
		
		return endTime - startTime;
		
	}
	
}
