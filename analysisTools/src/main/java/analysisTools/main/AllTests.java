package analysisTools.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import analysisTools.analysis.CreateInts;
import analysisTools.analysis.SpeedAnalysis;

public class AllTests {
	private static int lengthOfLists = 1000; //The length of the lists we are sorting
	private static int numberOfLists = 5000; //The number of lists we are comparing
	
	static public void runAllExperiments(String args[]) throws IOException{
		SpeedAnalysis timer = new SpeedAnalysis();
		ArrayList<int[]> listsToBeSorted = new ArrayList<int[]>(numberOfLists);
		String[] sortingAlgorithm = {/*"QuickSort", "QuickSortMone1","QuickSortMone2","QuickSortMone3","QuickSortMone4",
				"QuickSortMone5","QuickSortMone6","QuickSortMone7","QuickSortMone8","QuickSortMone9","QuickSortPone1",
				"QuickSortPone2","QuickSortPone3","QuickSortPone4","QuickSortPone5","QuickSortPone6","QuickSortPone7",
				"QuickSortPone8","QuickSortPone9","QuickSortPone10","QuickSortPone11","QuickSortPone12","QuickSortPone13",
				//Quicksort, 10 Mone, 13 Pone
				*/"BubbleSort","BubbleSortMone1","BubbleSortMone2",/*"BubbleSortMone3",*/"BubbleSortMone4","BubbleSortPone1",
				"BubbleSortPone2","BubbleSortPone3","BubbleSortPone4","BubbleSortPone5",
				//BubbleSort, 4 Mone, 5 Pone
				/*"HeapSort","HeapSortMone1","HeapSortMone2","HeapSortMone3","HeapSortMone4","HeapSortMone5","HeapSortMone6",
				"HeapSortMone7","HeapSortMone8","HeapSortPone1","HeapSortPone2","HeapSortPone3","HeapSortPone4",
				"HeapSortPone5","HeapSortPone6","HeapSortPone7",
				//HeapSort, 8 Mone, 7 Pone
				"InsertionSort",/*"InsertionSortMone1","InsertionSortMone2","InsertionSortPone1","InsertionSortPone2"//,
				//InsertionSort, 2 Mone, 2 Pone'
				/*"MergeSort","MergeSortMone1","MergeSortMone2","MergeSortMone2","MergeSortMone3","MergeSortMone4",
				"MergeSortPone1","MergeSortPone2","MergeSortPone3","MergeSortPone4",
				//MergeSort, Mone 4, Pone 4
				/*"SelectionSort",/*"SelectionSortMone1","SelectionSortMone2","SelectionSortMone3","SelectionSortMone4",
				"SelectionSortMone5",/*"SelectionSortMone6","SelectionSortMone7","SelectionSortPone1","SelectionSortPone2",
				/*"SelectionSortPone3",*//*"SelectionSortPone4",*/
				//SelectionSort, Mone 6, Pone 4
				/*"ShellSort","ShellSortMone1","ShellSortMone2","ShellSortMone3","ShellSortMone4","ShellSortMone5",
				"ShellSortMone6","ShellSortPone1","ShellSortPone2","ShellSortPone3","ShellSortPone4","ShellSortPone5"*/
				//ShellSort, Mone 6, Pone 5
		};
		
		for(int i = 0; i < numberOfLists; i++) {
			listsToBeSorted.add(CreateInts.giveInts(lengthOfLists));
		}
		
		
		PrintWriter inputLists = new PrintWriter("testedListsLongTest.csv");
		inputLists.println("Lists");
		int[] outList = new int[lengthOfLists];
		for(int i = 0; i < numberOfLists; i++) {
			outList = listsToBeSorted.get(i);
			for(int j = 0; j < lengthOfLists - 1; j++) {
				inputLists.print(outList[j]);
				inputLists.print(",");
			}
			inputLists.println(outList[lengthOfLists-1]);
		}
		inputLists.close();
		
		ArrayList<int[]> listsSortedByOriginal = new ArrayList<int[]>(listsToBeSorted.size());
		PrintWriter outTimes;
		PrintWriter outRanks;
		long[] currentTimes;
		long[] tenPercentWorstTimes; // the originals ten percent worst lists for nonoriginal
		int[] ranksOfTenPercentWorstTimes;
		
		for(int i = 0; i < sortingAlgorithm.length; i++) {
			currentTimes = new long[listsToBeSorted.size()];
			System.out.println(sortingAlgorithm[i]);
			if(sortingAlgorithm[i].contains("Mone") || sortingAlgorithm[i].contains("Pone")) {
				
				tenPercentWorstTimes = new long[(int)(listsToBeSorted.size()*0.1)];
				
				for(int j = 0; j < 15; j++) { //anti-Jit warmup
					timer.timeToSort(listsSortedByOriginal.get(0), sortingAlgorithm[i]);
				}
				
				for(int j = 0; j < listsSortedByOriginal.size(); j++) {
					currentTimes[j] = timer.timeToSort(listsSortedByOriginal.get(j), sortingAlgorithm[i]);
					if(j == 100 || j == 1000 || j == 2000 || j == 3000 || j == 4000) {
						System.out.println(j);
					}
				}
				outTimes = new PrintWriter("timesToSort5000" + sortingAlgorithm[i] + ".csv");
				outTimes.println("Time to sort list 5000 times");
				for(int j = 0; j < currentTimes.length; j++) {
					outTimes.println(currentTimes[j]);
				}
				outTimes.close();
				
				int k = 0;
				for(int j = (int)(currentTimes.length*0.9); j < currentTimes.length; j++) {
					tenPercentWorstTimes[k] = currentTimes[j];
					k++;
				}
				Arrays.sort(currentTimes);
				ranksOfTenPercentWorstTimes = new int[tenPercentWorstTimes.length];
				for(int j = 0; j < tenPercentWorstTimes.length; j++) {
					for(int l = 0; l < currentTimes.length; l++) {
						if(tenPercentWorstTimes[j] == currentTimes[l]) {
							ranksOfTenPercentWorstTimes[j] = l;
						}
					}
				}
				
				outRanks = new PrintWriter("ranksOfTenPercentWorstListsOfOriginalALgorithm" + 
											sortingAlgorithm[i] + ".csv");
				outRanks.println("Ranks");
				for(int j = 0; j < ranksOfTenPercentWorstTimes.length; j++) {
					outRanks.println(ranksOfTenPercentWorstTimes[j]);
				}
				outRanks.close();
			}
			else {
				for(int j = 0; j < 15; j++) { //anti-Jit warmup
					timer.timeToSort(listsToBeSorted.get(0), sortingAlgorithm[i]);
				}
				for(int j = 0; j < listsToBeSorted.size(); j++) {
					currentTimes[j] = timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
				}
				listsSortedByOriginal = timer.getSortedByOriginal(listsToBeSorted, currentTimes);
				Arrays.sort(currentTimes);
				outTimes = new PrintWriter("timesToSort5000" + sortingAlgorithm[i] + ".csv");
				outTimes.println("Time to sort list 5000 times");
				
				for(int j = 0; j < currentTimes.length; j++) {
					outTimes.println(currentTimes[j]);
				}
				outTimes.close();
			}
		}
		
	}
}
