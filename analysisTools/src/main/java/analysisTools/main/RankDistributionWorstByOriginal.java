package analysisTools.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import analysisTools.analysis.ListCaseDistribution;
import analysisTools.analysis.SpeedAnalysis;

public class RankDistributionWorstByOriginal {
	private static int lengthOfLists = 1000; //The length of the lists we are sorting
	private static int numberOfLists = 5000; //The number of lists we are comparing
	private static int nrOfRanks = 100;
	private static SpeedAnalysis timer = new SpeedAnalysis();
	
	static public int[] getNRanks(int numberOfRanks, long[] listOfTimes,int[] listToSort, String sortingAlgorithm) {
		int[] ranks = new int[numberOfRanks];
		long time;
		for(int i = 0; i < numberOfRanks; i++) {
			time = timer.timeToSort(listToSort, sortingAlgorithm);
			ranks[i] = ListCaseDistribution.getSortingRank(listOfTimes, time);
		}
		return ranks;	
	}
	
	static public void runRankExperiments(String args[]) throws IOException{
		ArrayList<int[]> listsToBeSorted = new ArrayList<int[]>(numberOfLists);
		String[] sortingAlgorithm = {"QuickSort",
				"QuickSortMone1","QuickSortMone3",
				"QuickSortMone9","QuickSortPone4","QuickSortPone13",
				"HeapSort","HeapSortMone2","HeapSortPone2",
				"InsertionSort","InsertionSortMone1","InsertionSortPone1",
				"SelectionSort","SelectionSortMone3","SelectionSortPone2",
				"ShellSort","ShellSortMone1","ShellSortMone6","ShellSortPone1","ShellSortPone3"
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
		inputLists.close();
		
		ArrayList<int[]> listsSortedByOriginal = new ArrayList<int[]>(listsToBeSorted.size());
		PrintWriter outRanks;
		long[] currentTimes;
		ArrayList<int[]> ranksOf50Worst; // the originals ten percent worst lists for nonoriginal
		long[] perturbedTimes;
		int[] listToBeSorted;
		int[] currentRanks;
		listsSortedByOriginal.addAll(listsToBeSorted);
		
		for(int i = 0; i < sortingAlgorithm.length; i++) {
			currentTimes = new long[listsToBeSorted.size()];
			System.out.println(sortingAlgorithm[i]);
			if(sortingAlgorithm[i].contains("Mone") || sortingAlgorithm[i].contains("Pone")) {
				
				ranksOf50Worst = new ArrayList<int[]>(50);
				perturbedTimes = new long[numberOfLists];

				for(int j = 0; j < 100; j++) { //anti-Jit warmup
					timer.timeToSort(listsSortedByOriginal.get(j), sortingAlgorithm[i]);
				}
				
				for(int j = 0; j < numberOfLists; j++) {
					if(j ==100 || j == 1000 || j == 2500 || j == 4000) {
						System.out.println(j);
					}
					perturbedTimes[j] = timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
				}
				
				Arrays.sort(perturbedTimes);
				outRanks = new PrintWriter("ranksOfOnePercentWorstListsOfOriginalALgorithm" + 
											sortingAlgorithm[i] + ".csv");
				
				for(int j = 0; j < 50; j++) {
					listToBeSorted = listsSortedByOriginal.get(listsSortedByOriginal.size() - (50 -j));
					ranksOf50Worst.add(getNRanks(nrOfRanks, perturbedTimes, listToBeSorted, sortingAlgorithm[i]));
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
					timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
				}
				for(int j = 0; j < listsToBeSorted.size(); j++) {
					if(j ==100 || j == 1000 || j == 2500 || j == 4000) {
						System.out.println(j);
					}
					currentTimes[j] = timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
				}
				listsSortedByOriginal.clear();
				listsSortedByOriginal = timer.getSortedByOriginal(listsToBeSorted, currentTimes);
				Arrays.sort(currentTimes);
				
				outRanks = new PrintWriter("ranksOfOnePercentWorstListsOfOriginalALgorithm" + 
						sortingAlgorithm[i] + ".csv");

				
				ranksOf50Worst = new ArrayList<int[]>(50);
				currentRanks = new int[100];
				
				for(int j = 0; j < 50; j++) {
					if(j == 10 || j == 25  || j == 50) {
						System.out.println(j);
					}
					listToBeSorted = listsSortedByOriginal.get(listsSortedByOriginal.size() - (50 -j));
					ranksOf50Worst.add(getNRanks(nrOfRanks, currentTimes, listToBeSorted, sortingAlgorithm[i]));
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