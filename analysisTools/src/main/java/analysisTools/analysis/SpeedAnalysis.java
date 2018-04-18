package analysisTools.analysis;

import java.util.ArrayList;

import analysisTools.analysedPrograms.*;

public class SpeedAnalysis {
	int length;
	
	public SpeedAnalysis(int length) {
		this.length = length;
	}
	public SpeedAnalysis() {
		this(10000);
	}
	
	public ArrayList<DataClass> runComparison(int numberOfIterations) {
		int[] randomList = new int[length];
		
		long startTime;
		long endTime;
		
		ArrayList<DataClass> analysis = new ArrayList<DataClass>(numberOfIterations);
		for(int i = 0; i<numberOfIterations; i++) {
			randomList = CreateInts.giveInts(length);
			int[] originalList = new int[length];
			System.arraycopy(randomList, 0, originalList , 0, randomList.length -1);
			
			startTime = System.nanoTime();
			QuickSortOriginal.sort(randomList, 0, randomList.length -1);
			endTime = System.nanoTime();
		
			analysis.add(new DataClass(endTime - startTime, originalList));
		}
		return analysis;
	}
}
