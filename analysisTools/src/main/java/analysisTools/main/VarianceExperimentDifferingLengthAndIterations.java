package analysisTools.main;

import java.io.*;

import analysisTools.analysis.CreateInts;
import analysisTools.analysis.SpeedAnalysis;

public class VarianceExperimentDifferingLengthAndIterations {
	private Options options;
	private SpeedAnalysis timer = new SpeedAnalysis();
	
	public VarianceExperimentDifferingLengthAndIterations(Options options) {
		this.options = options;
	}
	
	public void runExperiment() {
		try {
			for(int j = 0; j < options.getSortingAlgorithms().length; j++) {
				for(int k = 0; k < options.getListLength().length; k++) {
					forListsOfLength(options.getListLength()[k], 
						options.getSortingAlgorithms()[j], options.getNrOfMeasurements());
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void forListsOfLength(int listLength, String sortingAlgorithm, int[] nrOfMeasurements) throws FileNotFoundException{
		int[] listToBeSorted = CreateInts.giveInts(listLength);
		printList(options.getFileFamily() + listLength + "List" + sortingAlgorithm + ".csv", listToBeSorted);
		for(int i = 0; i < options.getNrOfSorts().length; i++) {
			forNrOfSorts(options.getNrOfSorts()[i], listToBeSorted, sortingAlgorithm, nrOfMeasurements);
		}
	}
	
	public void forNrOfSorts(int nrOfSorts, int[] listToBeSorted, String sortingAlgorithm, int[] nrOfMeasurements) throws FileNotFoundException {
		for(int k = 0; k < nrOfMeasurements.length; k++) {
			int currentNrOfMeasurements = nrOfMeasurements[k];
			long[] timeMeasurements = new long[currentNrOfMeasurements];
		
			//Jit warmup
			for(int i = 0; i < 100; i++) {
				if(nrOfSorts != 1) {
					timer.timeToSort(listToBeSorted, sortingAlgorithm, nrOfSorts);
				}
				else {
						for(int j = 0; j < 100; j++) {
					timer.timeToSortOnlyOnce(listToBeSorted, sortingAlgorithm);
					}
				}
			}
			
			for(int i = 0; i < currentNrOfMeasurements; i++) {
				if(nrOfSorts != 1) {
					timeMeasurements[i] = timer.timeToSort(listToBeSorted, sortingAlgorithm, nrOfSorts);
				}
				else {
					timeMeasurements[i] = timer.timeToSortOnlyOnce(listToBeSorted, sortingAlgorithm);
				}
			}
			printTimes(options.getFileFamily() + "Length" + listToBeSorted.length + sortingAlgorithm + nrOfSorts + 
					"Sorts" + currentNrOfMeasurements + "Times.csv", timeMeasurements);
		}
	}
	
	public void printList(String file, int[] list) throws FileNotFoundException {
		PrintWriter listFile = new PrintWriter(file);
		
		for(int i = 0; i < list.length; i++) {
			listFile.print(list[i]);
			if(i != list.length-1) {
				listFile.print(",");
			}
		}
		listFile.close();
	}
	
	public void printTimes(String file, long[] timeMeasurements) throws FileNotFoundException {
		PrintWriter timeFile = new PrintWriter(file);
		
		for(int i = 0; i < timeMeasurements.length; i++) {
			timeFile.println(timeMeasurements[i]);
		}
		timeFile.close();
	}
}
