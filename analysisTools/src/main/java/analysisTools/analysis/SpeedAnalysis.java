package analysisTools.analysis;

import java.util.ArrayList;
import java.util.List;

import analysisTools.analysedPrograms.*;

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

	
	public ArrayList<DataClass> runComparison(int numberOfIterations) {
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
	 */
	public ArrayList<DataClass> runDeepAnalysis(List<DataClass> sortedList, int nrOfIterations){
		ArrayList<DataClass> ret = new ArrayList<DataClass>(sortedList.size());
		long[] times = new long[nrOfIterations];
		long sum;
		
		for(int i = 0; i < sortedList.size(); i++) {
			sum = 0;
			for(int j = 0; j < nrOfIterations; j++) {
				times[j] = timeToSort(sortedList.get(i).getList());
				sum += times[j];
			}
			ret.add(new DataClass(sum/nrOfIterations, sortedList.get(i).getList()));
		}
		return ret;
	}
	/**
	 * Measures the time it takes to sort a list.
	 * @param oList the list to sort.
	 * @return the time taken to sort oList.
	 */
	public long timeToSort(int[] oList) {
		int[] list = new int[oList.length];
		//System.arraycopy(list, 0, oList, 0,  oList.length);
		for(int i = 0; i < oList.length; i++) {
			list[i] = oList[i];
		}
		
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		
		startTime = System.nanoTime();
		QuickSortOriginal.sort(list, 0, list.length-1);
		endTime = System.nanoTime();
		
		return endTime - startTime;
		
	}
}
