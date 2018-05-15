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
		String[] sortingAlgorithm = {"MergeSort","MergeSortMone1","MergeSortMone2","MergeSortMone2","MergeSortMone3","MergeSortMone4",
				"MergeSortPone1","MergeSortPone2","MergeSortPone3","MergeSortPone4"
				//MergeSort, Mone 4, Pone 4
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

		listsSortedByOriginal.addAll(listsToBeSorted);
		
		for(int i = 0; i < sortingAlgorithm.length; i++) {
			currentTimes = new long[listsToBeSorted.size()];
			System.out.println(sortingAlgorithm[i]);
			if(sortingAlgorithm[i].contains("Mone") || sortingAlgorithm[i].contains("Pone")) {
				
				tenPercentWorstTimes = new long[(int)(listsToBeSorted.size()*0.1)];
				
				for(int j = 0; j < 100; j++) { //anti-Jit warmup
					timer.timeToSort(listsSortedByOriginal.get(j), sortingAlgorithm[i]);
					System.out.println(j);
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
				for(int j = 0; j < 100; j++) { //anti-Jit warmup
					timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
					System.out.println(j);
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
