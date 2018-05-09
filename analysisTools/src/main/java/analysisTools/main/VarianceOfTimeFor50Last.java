package analysisTools.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import analysisTools.analysis.ListCaseDistribution;
import analysisTools.analysis.SpeedAnalysis;

public class VarianceOfTimeFor50Last {
	private static int lengthOfLists = 1000; //The length of the lists we are sorting
	private static int numberOfLists = 5000; //The number of lists we are comparing
	private static int nrOfIterations = 100;
	private static SpeedAnalysis timer = new SpeedAnalysis();
	
	static public long[] getNTimes(int numberOfTimes, int[] listToSort, String sortingAlgorithm) {
		long[] times = new long[numberOfTimes];
		for(int i = 0; i < numberOfTimes; i++) {
			times[i] = timer.timeToSort(listToSort, sortingAlgorithm);
		}
		return times;	
	}
	
	static public void runVarianceExperiments(String args[]) throws IOException{
		ArrayList<int[]> listsToBeSorted = new ArrayList<int[]>(numberOfLists);
		String[] sortingAlgorithm = {
				"SelectionSort"
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
		BufferedReader inPerturbedTimes;
		PrintWriter outTimesWorst;
		PrintWriter outTimesBest;
		long[] currentTimes;
		ArrayList<long[]> timesOf50Worst; // the originals ten percent worst lists for nonoriginal
		ArrayList<long[]> timesOf50Best; 
		long[] perturbedTimes;
		int[] listToBeSorted;
		long[] timesBest;
		long[] timesWorst;
		
		for(int i = 0; i < sortingAlgorithm.length; i++) {
			currentTimes = new long[listsToBeSorted.size()];
			System.out.println(sortingAlgorithm[i]);
			timesOf50Worst = new ArrayList<long[]>(50);
			timesOf50Best = new ArrayList<long[]>(50);
			timesBest = new long[nrOfIterations];
			timesWorst = new long[nrOfIterations];
			if(sortingAlgorithm[i].contains("Mone") || sortingAlgorithm[i].contains("Pone")) {
				
				for(int j = 0; j < 100; j++) { //anti-Jit warmup
					timer.timeToSort(listsSortedByOriginal.get(j), sortingAlgorithm[i]);
				}
				
				for(int j = 0; j < 50; j++) {
					listToBeSorted = listsSortedByOriginal.get(listsSortedByOriginal.size() - (50 -j));
					timesOf50Worst.add(getNTimes(nrOfIterations, listToBeSorted, sortingAlgorithm[i]));
					
					listToBeSorted = listsSortedByOriginal.get(j);
					timesOf50Best.add(getNTimes(nrOfIterations, listToBeSorted, sortingAlgorithm[i]));
				}
				

				outTimesWorst = new PrintWriter("timesOfOnePercentWorstListsOfPerturbedALgorithm" + 
						sortingAlgorithm[i] + ".csv");
				
				outTimesBest = new PrintWriter("timesOfOnePercentBestListsOfPerturbedALgorithm" + 
						sortingAlgorithm[i] + ".csv");
				
				for(int j = 0; j < 50; j++) {
					listToBeSorted = listsSortedByOriginal.get(listsSortedByOriginal.size() - (50 -j));
					timesOf50Worst.add(getNTimes(nrOfIterations, listToBeSorted, sortingAlgorithm[i]));
					listToBeSorted = listsSortedByOriginal.get(j);
					timesOf50Best.add(getNTimes(nrOfIterations, listToBeSorted, sortingAlgorithm[i]));
				}
				
				outTimesWorst.println("Times");
				outTimesBest.println("Times");
				
				for(int j = 0; j < timesOf50Worst.size(); j++) {
					timesBest = timesOf50Best.get(j);
					timesWorst = timesOf50Worst.get(j);
					for(int k = 0; k < nrOfIterations; k++) {
						outTimesBest.println(timesBest[k]);
						outTimesWorst.println(timesWorst[k]);
					}
				}
				
				outTimesWorst.close();
				outTimesBest.close();
				
			}
			else {
				for(int j = 0; j < 100; j++) { //anti-Jit warmup
					timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
				}
				for(int j = 0; j < listsToBeSorted.size(); j++) {
					currentTimes[j] = timer.timeToSort(listsToBeSorted.get(j), sortingAlgorithm[i]);
				}
				listsSortedByOriginal = timer.getSortedByOriginal(listsToBeSorted, currentTimes);
				
				outTimesWorst = new PrintWriter("timesOfOnePercentWorstListsOfOriginalAlgorithm" + 
						sortingAlgorithm[i] + ".csv");
				
				outTimesBest = new PrintWriter("timesOfOnePercentBestListsOfOriginalAlgorithm" + 
						sortingAlgorithm[i] + ".csv");
				
				for(int j = 0; j < 50; j++) {
					listToBeSorted = listsSortedByOriginal.get(listsSortedByOriginal.size() - (50 -j));
					timesOf50Worst.add(getNTimes(nrOfIterations, listToBeSorted, sortingAlgorithm[i]));
					listToBeSorted = listsSortedByOriginal.get(j);
					timesOf50Best.add(getNTimes(nrOfIterations, listToBeSorted, sortingAlgorithm[i]));
				}
				
				outTimesWorst.println("Times");
				outTimesBest.println("Times");
				
				for(int j = 0; j < timesOf50Worst.size(); j++) {
					timesBest = timesOf50Best.get(j);
					timesWorst = timesOf50Worst.get(j);
					for(int k = 0; k < nrOfIterations; k++) {
						outTimesBest.println(timesBest[k]);
						outTimesWorst.println(timesWorst[k]);
					}
				}
				
				outTimesWorst.close();
				outTimesBest.close();
				
			}
		}
	}
}