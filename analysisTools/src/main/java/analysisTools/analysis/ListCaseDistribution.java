package analysisTools.analysis;

import java.util.Arrays;

public class ListCaseDistribution {
	private long[] times;
	private SpeedAnalysis analysis = new SpeedAnalysis();
	
	public ListCaseDistribution(long[] listOfTimes) {
		times = listOfTimes;
	}
	
	public int getSortingRank(int[] list) {
		long time = 0;
		int rank = -1;
		
		time = analysis.timeToSort(list, 1);

		long[] timesCopy = new long[times.length];
		//System.arraycopy(list, 0, oList, 0,  oList.length);
		for(int i = 0; i < times.length -1; i++) {
			timesCopy[i] = times[i];
		}
		
		timesCopy[times.length-1] = time;
		
		Arrays.sort(timesCopy);
		
		for(int i = 0; i < timesCopy.length; i++) {
			if (timesCopy[i] == time) {
				rank = i;
				break;
			}
		}
		return rank;
		
	}
	
	public static int getSortingRank(long[] listOfTimes, long time) {
		int i;
		for(i = 0; i < listOfTimes.length-1; i++) {
			if(time < listOfTimes[i]) {
				break;
			}
		}
		return (i+1); //+1 due to 0 = rank 1
	}
	
	public int[] runExperiment(int[] list, int length) {
		int[] ranks = new int[length];
		
		for (int i = 0; i < length; i++) {
			ranks[i] = getSortingRank(list);
		}
		return ranks;
	}
	
	public long[] getTimes() {
		return times;
	}
}
