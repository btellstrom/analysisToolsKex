package analysisTools.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

import analysisTools.analysis.*;

public class Main {

	public static void main(String[] args) {
		String filename = "./timeAnalysisQuickSort.csv";
		int length = 10000;
		int size = 1000;
		String typeOfAnalysis = new String("returnTimes");
		
		int index = 0;
		for(String arg: args) {
			if(arg.contains("--help")) {
				System.out.println("Usage java -jar target/... ["
						+ "<switches>...]");
				System.out.println();
				System.out.println("<Switches>");
				System.out.println("--help : display this help");
				System.out.println("--size : set the number of lists analysed.");
				System.out.println("--length : sets the length of the analyesed lists. "
						+ "Default 10000");
				System.out.println("--file : sets the file to write the payload to."
						+ " Default payload.csv");
				System.out.println("--type: sets the type of the output. returnTimes "
						+ "or rt returns a sorted list of all times. returLists "
						+ "or rl returns all lists analysed sorted after time to "
						+ "sort. returnListTimes or all or rlt or rtl returns both times"
						+ " and lists, sorted by time."
						+ " Default is returnTimes.");
				System.exit(0);
			}
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
			if(arg.equalsIgnoreCase("--type")) {
				typeOfAnalysis = args[index+1];
			}
	
		}
		
		SpeedAnalysis analysis = new SpeedAnalysis(length);
		DataClass[] analysed= analysis.runComparison(size);

		Arrays.sort(analysed);
			
		try{
			PrintWriter out = new PrintWriter(filename);				
			out.println("Time to sort");

			if(typeOfAnalysis.equals("returnTimes") || typeOfAnalysis.equals("rt")) {
				for (int i = 0; i < size; i++) {
					System.out.println(analysed[i].getTime());
					}	
			}
			else if(typeOfAnalysis.equals("returnLists") || typeOfAnalysis.equals("rl")) {
				for (int i = 0; i < size; i++) {
					System.out.println(analysed[i].getList());
					}	
			}
			else if(typeOfAnalysis.equals("returnListTimes") || typeOfAnalysis.equals("all") || 
					typeOfAnalysis.equals("rlt") || typeOfAnalysis.equals("rtl")) {
				for (int i = 0; i < size; i++) {
					System.out.println(analysed[i]);
					}	
			}
			out.close();
		}catch(FileNotFoundException e) {
			System.out.println("Something was wrong with the file");
			System.out.println(e.getMessage());
		}	
	}
}
