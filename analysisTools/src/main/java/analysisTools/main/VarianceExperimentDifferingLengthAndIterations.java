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
			for(int i = 0; i < options.getNrOfMeasurements().length; i++) {//nr Of measurements
				for(int j = 0; j < options.getSortingAlgorithms().length; j++) {
					for(int k = 0; k < options.getListLength().length; k++) {
						forListsOfLength(options.getListLength()[k], 
								options.getSortingAlgorithms()[j], 
								options.getNrOfMeasurements()[i]);
					}
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void forListsOfLength(int listLength, String sortingAlgorithm, int nrOfMeasurements) throws FileNotFoundException{
		int[] listToBeSorted = CreateInts.giveInts(listLength);
		printList(options.getFileFamily() + listLength + "List" + sortingAlgorithm + ".csv", listToBeSorted);
		for(int i = 0; i < options.getNrOfSorts().length; i++) {
			forNrOfSorts(options.getNrOfSorts()[i], listToBeSorted, sortingAlgorithm, nrOfMeasurements);
		}
	}
	
	public void forNrOfSorts(int nrOfSorts, int[] listToBeSorted, String sortingAlgorithm, int nrOfMeasurements) throws FileNotFoundException {
		long[] timeMeasurements = new long[nrOfMeasurements];
		
		//Jit warmup
		for(int i = 0; i < 100; i++) {
			timer.timeToSort(listToBeSorted, sortingAlgorithm, nrOfSorts);
		}
		
		for(int i = 0; i < nrOfMeasurements; i++) {
			timeMeasurements[i] = timer.timeToSort(listToBeSorted, sortingAlgorithm, nrOfSorts);
		}
		printTimes(options.getFileFamily() + "Length" + listToBeSorted.length + sortingAlgorithm + "Times.csv", timeMeasurements);
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
