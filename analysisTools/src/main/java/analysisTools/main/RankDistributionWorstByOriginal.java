package analysisTools.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import analysisTools.analysis.SpeedAnalysis;

public class RankDistributionWorstByOriginal {
	private static int lengthOfLists = 1000; //The length of the lists we are sorting
	private static int numberOfLists = 5000; //The number of lists we are comparing
	
	static public void runRankExperiments(String args[]) throws IOException{
		SpeedAnalysis timer = new SpeedAnalysis();
		ArrayList<int[]> listsToBeSorted = new ArrayList<int[]>(numberOfLists);
		String[] sortingAlgorithm = {"QuickSort", "QuickSortMone1",/*"QuickSortMone2",*/"QuickSortMone3",/*"QuickSortMone4",
				"QuickSortMone5","QuickSortMone6","QuickSortMone7","QuickSortMone8",*/"QuickSortMone9",/*"QuickSortPone1",
				"QuickSortPone2","QuickSortPone3",*/"QuickSortPone4",/*"QuickSortPone5","QuickSortPone6","QuickSortPone7",
				"QuickSortPone8","QuickSortPone9","QuickSortPone10","QuickSortPone11","QuickSortPone12",*/"QuickSortPone13",
				//Quicksort, 10 Mone, 13 Pone
				//"BubbleSort","BubbleSortMone1","BubbleSortMone2",/*"BubbleSortMone3","BubbleSortMone4","BubbleSortPone1",
				//"BubbleSortPone2","BubbleSortPone3","BubbleSortPone4","BubbleSortPone5",*/ 
				//BubbleSort, 4 Mone, 5 Pone
				"HeapSort",/*"HeapSortMone1",*/"HeapSortMone2",/*"HeapSortMone3","HeapSortMone4","HeapSortMone5","HeapSortMone6",
				"HeapSortMone7","HeapSortMone8","HeapSortPone1",*/"HeapSortPone2",/*"HeapSortPone3","HeapSortPone4",
				"HeapSortPone5","HeapSortPone6","HeapSortPone7",*/
				//HeapSort, 8 Mone, 7 Pone
				"InsertionSort","InsertionSortMone1",/*"InsertionSortMone2",*/"InsertionSortPone1",//"InsertionSortPone2",
				//InsertionSort, 2 Mone, 2 Pone'
				/*"MergeSort","MergeSortMone1","MergeSortMone2","MergeSortMone2","MergeSortMone3","MergeSortMone4",
				"MergeSortPone1","MergeSortPone2","MergeSortPone3","MergeSortPone4",*/
				//MergeSort, Mone 4, Pone 4
				"SelectionSort",/*"SelectionSortMone1","SelectionSortMone2",*/"SelectionSortMone3",/*"SelectionSortMone4",
				"SelectionSortMone5","SelectionSortMone6","SelectionSortMone7","SelectionSortPone1",*/"SelectionSortPone2",
				//"SelectionSortPone3","SelectionSortPone4",
				//SelectionSort, Mone 6, Pone 4
				"ShellSort","ShellSortMone1",/*"ShellSortMone2","ShellSortMone3","ShellSortMone4","ShellSortMone5",
				"ShellSortMone6",*/"ShellSortPone1"//,"ShellSortPone2","ShellSortPone3","ShellSortPone4","ShellSortPone5"
				//ShellSort, Mone 6, Pone 5
		};
		
		
		BufferedReader inputLists = new BufferedReader(new FileReader("testedListsLongTest.csv"));
		
		String inputRow;
		String[] splitLine;
		
		inputLists.readLine();
		
		for(int i = 0; i < numberOfLists; i++) {
			inputRow = inputLists.readLine();
			splitLine = inputRow.split(",");
			int[] list = new int[splitLine.length];
			for(int j = 0; j < splitLine.length; j++) {
				list[j] = Integer.parseInt(splitLine[j]);
			}
			listsToBeSorted.add(list);
		}
		
		ArrayList<int[]> listsSortedByOriginal = new ArrayList<int[]>(listsToBeSorted.size());
		BufferedReader inPerturbedTimes;
		PrintWriter outRanks;
		long[] currentTimes;
		ArrayList<int[]> ranksOf50Worst; // the originals ten percent worst lists for nonoriginal
		long[] perturbedTimes;
		int[] listToBeSorted;
		long timeToSortPerturbed;
		int[] currentRanks;
		
		for(int i = 0; i < sortingAlgorithm.length; i++) {
			currentTimes = new long[listsToBeSorted.size()];
			System.out.println(sortingAlgorithm[i]);
			if(sortingAlgorithm[i].contains("Mone") || sortingAlgorithm[i].contains("Pone")) {
				
				ranksOf50Worst = new ArrayList<int[]>(50);

				
				inPerturbedTimes = new BufferedReader(new FileReader("timesToSort5000" + sortingAlgorithm[i] + ".csv"));
				inPerturbedTimes.readLine();
				
				perturbedTimes = new long[numberOfLists];
				

				for(int j = 0; j < 100; j++) { //anti-Jit warmup
					timer.timeToSort(listsSortedByOriginal.get(0), sortingAlgorithm[i]);
				}
				
				for(int j = 0; j < numberOfLists; j++) {
					perturbedTimes[j] = Long.parseLong(inPerturbedTimes.readLine());
				}
				
				outRanks = new PrintWriter("ranksOfOnePercentWorstListsOfOriginalALgorithm" + 
											sortingAlgorithm[i] + ".csv");
				
				
				int l;
				
				for(int j = 0; j < 50; j++) {
					listToBeSorted = listsSortedByOriginal.get(listsSortedByOriginal.size() - (50 -j));
					currentRanks = new int[100];
					for(int k = 0; k < 100; k++) {
						timeToSortPerturbed = timer.timeToSort(listToBeSorted, sortingAlgorithm[i]);
						if(j*k == 100 || j*k == 1000 || j*k == 2000 || j*k == 3000 || j*k == 4000) {
							System.out.println(k);
						}
						l = 0;
						
						while(timeToSortPerturbed > perturbedTimes[l] && l < numberOfLists-1) {
							l++;
						}
						currentRanks[k] = l;
						
					}
					ranksOf50Worst.add(currentRanks);
				}
				
				outRanks.println("Ranks");
				for(int j = 0; j < ranksOf50Worst.size(); j++) {
					currentRanks = ranksOf50Worst.get(j);
					
					for(int k = 0; k < currentRanks.length; k++) {
						outRanks.print(currentRanks[k]);
						if(k < currentRanks.length-1) {
							outRanks.print(",");
						}
					}
					outRanks.println();
				}
				outRanks.close();
			}
			
			
			else {
				for(int j = 0; j < 100; j++) { //anti-Jit warmup
					timer.timeToSort(listsToBeSorted.get(0), sortingAlgorithm[i]);
				}
				for(int j = 0; j < listsToBeSorted.size(); j++) {
					currentTimes[j] = timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
				}
				listsSortedByOriginal = timer.getSortedByOriginal(listsToBeSorted, currentTimes);
				Arrays.sort(currentTimes);
				
				outRanks = new PrintWriter("ranksOfOnePercentWorstListsOfOriginalALgorithm" + 
						sortingAlgorithm[i] + ".csv");

				
				ranksOf50Worst = new ArrayList<int[]>(50);
				currentRanks = new int[100];
				int l;
				
				for(int j = 0; j < 50; j++) {	

					currentRanks = new int[100];
					for(int k = 0; k < 100; k++) {

						timeToSortPerturbed = timer.timeToSort(listsSortedByOriginal.get(numberOfLists - (50-j)), sortingAlgorithm[i]);
						if(j*k == 100 || j*k == 1000 || j*k == 2000 || j*k == 3000 || j*k == 4000) {
							System.out.println(k);
						}
						l = 0;
						
						while(timeToSortPerturbed > currentTimes[l] && l < numberOfLists-1) {
							l++;
						}
						currentRanks[k] = l;
					}
					ranksOf50Worst.add(currentRanks);
				}
				
				
				outRanks.println("Ranks");
				for(int j = 0; j < ranksOf50Worst.size(); j++) {
					currentRanks = ranksOf50Worst.get(j);
				
					for(int k = 0; k < currentRanks.length; k++) {
						outRanks.print(currentRanks[k]);
						if(k < currentRanks.length-1) {
							outRanks.print(",");
						}
					}
					outRanks.println();
				}
				outRanks.close();
			}
		}
		
	}
}