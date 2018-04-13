package analysisTools.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import analysisTools.analysis.WorstCaseComparisonAfterPerturbation;

public class Main {

	public static void main(String[] args) {
		String filename = "./timeAnalysisQuickSort.csv";
		int length = 10000;
		int size = 1000;
		
		int index = 0;
		for(String arg: args) {
			if (arg.contains("--length")) {
				length = Integer.parseInt(args[index+1]);
			}
			if (arg.contains("--size")) {
				length = Integer.parseInt(args[index+1]);
			}
			if (arg.contains("--file")) {
				filename = args[index+1];
			}
			index++;
	
		}
		
		WorstCaseComparisonAfterPerturbation analysis = new WorstCaseComparisonAfterPerturbation(length);
		long[] times = analysis.runComparison(size);
		
		try{
			PrintWriter out = new PrintWriter(filename);
			out.print("WorstCaseTimes");
			out.print(",");
			out.print("RandomCaseTimes");
			for (int i = 0; i < size; i++) {
				out.print(times[i*2]);
				out.print(",");
				out.print(times[i*2+1]);
				out.println();
			}
			out.close();
		}catch(FileNotFoundException e) {
			System.out.println("Something was wrong with the file");
			System.out.println(e.getMessage());
		}	
	}
}
