package analysisTools.analysis;

public class DataClass implements Comparable<DataClass> {
	private long analysisTime;
	private int[] analysedList;
	
	public DataClass(long time, int[] list) {
		analysisTime = time;
		analysedList = list;
	}
	
	public long getTime() {
		return analysisTime;
	}
	
	public int[] getList() {
		return analysedList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(analysedList.length);
		for(int i: analysedList) {
			sb.append(i);
		}
		return (analysisTime + ", " + sb.toString());
	}

	
	/*
	 * (non-Javadoc)
	 * Unsure of how to properly convert Long into int without messing up order and signs.
	 * Instead used the Long compareTo-method to ensure they're sorted to Longs natural ordering.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DataClass a) {
		Long at = new Long(analysisTime);
		Long t = new Long(a.getTime());
		return (at.compareTo(t));
	}
}
