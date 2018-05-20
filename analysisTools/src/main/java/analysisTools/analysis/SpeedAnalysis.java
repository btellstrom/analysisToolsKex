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

			time = timeToSort(randomList, 1);
		
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
			ret.add(new DataClass(timeToSort(sortedList.get(i).getList(), 1), sortedList.get(i).getList()));
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
	public long timeToSort(int[] oList, int nrOfSorts){
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		
		startTime = System.nanoTime();
		for( int j = 1; j < nrOfSorts; j++ ) {
			
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
	public long timeToSort(int[] oList, String algorithm, int nrOfSorts){
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		//QuickSort first. This loop will have constant time for the timemeasurement... Hmm, one if-statement more
		if(algorithm.equals("QuickSort")) {	
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSort.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
			//Mone-algorithms
		}else if(algorithm.equals("QuickSortMone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone1.sort(list, 0, list.length-1);	
			}
			
			endTime = System.nanoTime();

		}else if(algorithm.equals("QuickSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone2.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone3.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone4")) {
			startTime = System.nanoTime();
		for( int j = 1; j < nrOfSorts; j++ ) {
			
			int[] list = new int[oList.length];
			//System.arraycopy(list, 0, oList, 0,  oList.length);
			for(int i = 0; i < oList.length; i++) {
				list[i] = oList[i];
			}
			QuickSortMone4.sort(list, 0, list.length-1);
		}
		
		endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone5")) {
			startTime = System.nanoTime();
		for( int j = 1; j < nrOfSorts; j++ ) {
			
			int[] list = new int[oList.length];
			//System.arraycopy(list, 0, oList, 0,  oList.length);
			for(int i = 0; i < oList.length; i++) {
				list[i] = oList[i];
			}
			QuickSortMone6.sort(list, 0, list.length-1);
		}
		
		endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone6")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone7.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone7")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone9.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone8")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone14.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone9")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone15.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortMone0")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortMone16.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
			//Pone-algorithms
		}else if(algorithm.equals("QuickSortPone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone1.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone2.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone3.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone4.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone5")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone5.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone6")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone6.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone7")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone7.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone8")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone8.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone9")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone10.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone10")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone12.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone11")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone17.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone12")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone18.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("QuickSortPone13")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				QuickSortPone19.sort(list, 0, list.length-1);
			}
			
			endTime = System.nanoTime();
		}
		
		// BubbleSort
		else if(algorithm.equals("BubbleSort")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSort.sort(list);
			}
			
			endTime = System.nanoTime();
			//Mone algorithms
		}else if(algorithm.equals("BubbleSortMone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortMone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("BubbleSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortMone3.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("BubbleSortMone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortMone6.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("BubbleSortMone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortMone7.sort(list);
			}
			
			endTime = System.nanoTime();
			//Pone algorithms
		}else if(algorithm.equals("BubbleSortPone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortPone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("BubbleSortPone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortPone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("BubbleSortPone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortPone3.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("BubbleSortPone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortPone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("BubbleSortPone5")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				BubbleSortPone6.sort(list);
			}
			
			endTime = System.nanoTime();
		}
		
		
		//heapsort
		else if(algorithm.equals("HeapSort")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSort.sort(list);
			}
			
			endTime = System.nanoTime();
			//Mone algorithms
		}else if(algorithm.equals("HeapSortMone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortMone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone4.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortMone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortMone5")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone6.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortMone6")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone7.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortMone7")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone8.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortMone8")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortMone10.sort(list);
			}
			
			endTime = System.nanoTime();
			//Pone algorithms
		}else if(algorithm.equals("HeapSortPone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortPone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortPone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortPone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortPone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortPone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortPone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortPone6.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortPone5")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortPone7.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortPone6")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortPone8.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("HeapSortPone7")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				HeapSortPone10.sort(list);
			}
			
			endTime = System.nanoTime();
		}
		
		//Insertion sort
		else if(algorithm.equals("InsertionSort")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				InsertionSort.sort(list);
			}
			
			endTime = System.nanoTime();
			//Mone algorithms
		}else if(algorithm.equals("InsertionSortMone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				InsertionSortMone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("InsertionSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				InsertionSortMone5.sort(list);
			}
			
			endTime = System.nanoTime();
			//Pone algorithms
		}else if(algorithm.equals("InsertionSortPone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				InsertionSortPone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("InsertionSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				InsertionSortPone3.sort(list);
			}
			
			endTime = System.nanoTime();
		}
		
		//merge sort
		else if(algorithm.equals("MergeSort")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSort.sort(list);
			}
			
			endTime = System.nanoTime();
			//Mone algorithms
		}else if(algorithm.equals("MergeSortMone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortMone3.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("MergeSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortMone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("MergeSortMone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortMone11.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("MergeSortMone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortMone12.sort(list);
			}
			
			endTime = System.nanoTime();
			// Pone algorithms
		}else if(algorithm.equals("MergeSortPone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortPone4.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("MergeSortPone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortPone10.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("MergeSortPone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortPone13.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("MergeSortPone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				MergeSortPone14.sort(list);
			}
			
			endTime = System.nanoTime();
		}
		
		//Selection sort
		else if(algorithm.equals("SelectionSort")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSort.sort(list);
			}
			
			endTime = System.nanoTime();
			//Mone algorithms
		}else if(algorithm.equals("SelectionSortMone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortMone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortMone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortMone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortMone4.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortMone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortMone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortMone5")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortMone6.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortMone6")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortMone8.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortMone7")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortMone9.sort(list);
			}
			
			endTime = System.nanoTime();
			//Pone algorithms
		}else if(algorithm.equals("SelectionSortPone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortPone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortPone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortPone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortPone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortPone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("SelectionSortPone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				SelectionSortPone6.sort(list);
			}
			
			endTime = System.nanoTime();
		}
					
		//Shell sort
		else if(algorithm.equals("ShellSort")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSort.sort(list);
			}
			
			endTime = System.nanoTime();
			//Mone algorithms
		}else if(algorithm.equals("ShellSortMone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortMone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortMone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortMone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortMone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortMone3.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortMone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortMone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortMone5")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortMone7.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortMone6")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortMone8.sort(list);
			}
			
			endTime = System.nanoTime();
			//Pone algorithms
		}else if(algorithm.equals("ShellSortPone1")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortPone1.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortPone2")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortPone2.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortPone3")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortPone3.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortPone4")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortPone5.sort(list);
			}
			
			endTime = System.nanoTime();
		}else if(algorithm.equals("ShellSortPone5")) {
			startTime = System.nanoTime();
			for( int j = 1; j < nrOfSorts; j++ ) {
				
				int[] list = new int[oList.length];
				//System.arraycopy(list, 0, oList, 0,  oList.length);
				for(int i = 0; i < oList.length; i++) {
					list[i] = oList[i];
				}
				ShellSortPone6.sort(list);
			}
			
			endTime = System.nanoTime();
		}

		
		return endTime - startTime;
		
	}
	
}
