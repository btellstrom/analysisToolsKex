package analysisTools.analysis;

import analysisTools.analysedPrograms.*;

public class SpeedAnalysis {
	int length;
	
	public SpeedAnalysis(int length) {
		this.length = length;
	}
	public SpeedAnalysis() {
		this(10000);
	}
	
	public DataClass[] runComparison(int numberOfIterations) {
		int[] randomList = new int[length];
		int[] originalList = new int[length];
		
		long startTime;
		long endTime;
		
		DataClass[] analysis = new DataClass[numberOfIterations];
		for(int i = 0; i<numberOfIterations; i++) {
			randomList = CreateInts.giveInts(length);
			System.arraycopy(randomList, 0, originalList , 0, randomList.length -1);
			
			startTime = System.nanoTime();
			QuickSortOriginal.sort(randomList,0, randomList.length -1);
			endTime = System.nanoTime();
		
			analysis[i] = new DataClass(endTime - startTime, originalList);
		}
		return analysis;
	}
}
