package analysisTools.analysis;

import analysisTools.analysedPrograms.*;

public class WorstCaseComparisonAfterPerturbation {
	int length;
	
	public WorstCaseComparisonAfterPerturbation(int length) {
		this.length = length;
	}
	public WorstCaseComparisonAfterPerturbation() {
		this(100000);
	}
	
	public long[] runComparison(int numberOfIterations) {
		int[] worstCase = new int[length];
		int[] randomList = new int[length];
		
		long worstStartTime;
		long worstEndTime;
		long randomStartTime;
		long randomEndTime;
		
		long[] totalTime = new long[2*numberOfIterations];
		for(int i = 0; i<numberOfIterations; i++) {
			worstCase = ListGenerator.generateList(length, 0); 
			randomList = CreateInts.giveInts(length);
			
			worstStartTime = System.nanoTime();
			QuickSortPONE.sort(worstCase,0, worstCase.length -1);
			worstEndTime = System.nanoTime();
		
			randomStartTime = System.nanoTime();
			QuickSortPONE.sort(randomList, 0, randomList.length -1);
			randomEndTime = System.nanoTime();
			
			totalTime[2*i] = worstEndTime - worstStartTime;
			totalTime[2*i+1] = randomEndTime - randomStartTime;
		}
		return totalTime;
	}
}
