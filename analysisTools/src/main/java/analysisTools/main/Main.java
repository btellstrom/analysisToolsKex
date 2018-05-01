package analysisTools.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import analysisTools.analysis.*;

public class Main {

	public static void main(String[] args) {
		String fileFamily = "./timeAnalysisSort";
		int length = 10000;
		int size = 1000;
		String typeOfAnalysis = new String("returnTimes");
		boolean orig = false;
		
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
				System.out.println("--files : sets the family of files to write the payload to."
						+ " Default ./timeAnalysisSort");
				System.out.println("--type: sets the type of the output. returnTimes "
						+ "or rt returns a sorted list of all times. returnLists "
						+ "or rl returns all lists analysed sorted after time to "
						+ "sort. returnListTimes or all or rlt or rtl returns both times"
						+ " and lists, sorted by time."
						+ " Default is returnTimes."
						+ "compare or c compares with the previously "
						+ "created files."
						+ "separate or s creates two separate files, "
						+ "one for time and one for lists."
						+ "deep or d also runs the deepanalysis.");
				System.out.println("--original is run along with c if there is no "
						+ "previously created ...SortedLists.csv.");
				
				System.exit(0);
			}
			if (arg.contains("--length")) {
				length = Integer.parseInt(args[index+1]);
			}
			if (arg.contains("--size")) {
				size = Integer.parseInt(args[index+1]);
			}
			if (arg.contains("--files")) {
				fileFamily = args[index+1];
			}
			if(arg.equalsIgnoreCase("--type")) {
				typeOfAnalysis = args[index+1];
			}
			if(arg.equalsIgnoreCase("--original")) {
				orig = true;
			}

			index++;
	
		}

		SpeedAnalysis analysis = new SpeedAnalysis(length);
		
		//If comparison run this block of code
		
		if(typeOfAnalysis.equals("compare") || typeOfAnalysis.equals("c")) {
			ArrayList<DataClass> output = new ArrayList<DataClass>((int)(size));
			//List<DataClass> outputWorst = new ArrayList<DataClass>((int)(size*0.1));
			
			try {
				/*
				 * reads file
				 */
				BufferedReader in;
				if(orig) {
					in = new BufferedReader(new FileReader(fileFamily + "Lists.csv"));
				}
				else {
					in = new BufferedReader(new FileReader(fileFamily + "SortedLists.csv"));
				}
				//BufferedReader in2 = new BufferedReader(new FileReader(fileFamily + "Times.csv"));
				List<DataClass> input = new ArrayList<DataClass>((size));
				String[] splitLine;
				int[] list;
				
				try{
					//in2.readLine(); //first line is always "time to sort"
					String str = in.readLine();

					while(str != null) {
						splitLine = str.split(",");
						list = new int[splitLine.length];
						for(int i = 0; i < splitLine.length; i++) {
							list[i] = Integer.parseInt(splitLine[i]);
						}
						input.add(new DataClass(0, list));
						str = in.readLine();
					}

					in.close();
					
					/*
					 * runs the analysis on input and subset of input
					 */
					//TODO better way of setting nrOfIteratios
					output = analysis.runDeepAnalysis(input, 100);
	
					if(orig) {
						Collections.sort(output);
					}
					//outputWorst = output.subList((9*output.size())/10, output.size());
					
					/*
					 * Prints to file
					 */
					PrintWriter out = new PrintWriter(fileFamily + "DeepTimes.csv");	
					//PrintWriter out2 = new PrintWriter(fileFamily + "DeepWorstTimes.csv");
					
					out.println("Time to sort");
					//out2.println("Time to sort");
	
					for (int i = 0; i < output.size(); i++) {
						out.println(output.get(i).getTime());
					}
					out.close();
					/*
					for(int i = 0; i < outputWorst.size(); i++) {
						out2.println(outputWorst.get(i).getTime());
					}
					out2.close();
					*/
					if(orig) {
						out = new PrintWriter(fileFamily + "SortedLists.csv");
						
						for (int i = 0; i < size; i++) {
							out.println(output.get(i).listToString());
						}	
						out.close();
					}
					
					
				}catch(IOException e) {
					System.out.println(e.getMessage());
				}
				
			}catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		
		else if(typeOfAnalysis.equals("listdistribution") || typeOfAnalysis.equals("ld")) {
			
			try {
				/*
				 * reads file
				 */
				BufferedReader in = new BufferedReader(new FileReader(fileFamily + "SortedLists.csv"));
				BufferedReader in2 = new BufferedReader(new FileReader(fileFamily + "DeepTimes.csv"));
				long[] inputTimesFirst = new long[size-1];
				long[] inputTimesMiddle = new long[size-1];
				long[] inputTimesLast = new long[size-1];
				int[] first = new int[length];
				int[] middle = new int[length];
				int[] last = new int[length];
				
				in2.readLine(); //first line is always "time to sort"
				String str = in.readLine();
				int check = 1;
				String[] splitLine;
					
				while(str != null) {
					if(check == 1) {
						splitLine = str.split(",");
						first = new int[splitLine.length];
						for(int i = 0; i < splitLine.length; i++) {
							first[i] = Integer.parseInt(splitLine[i]);
						}
					}
					else if(check == 0.5*size) {
						splitLine = str.split(",");
						middle = new int[splitLine.length];
						for(int i = 0; i < splitLine.length; i++) {
							middle[i] = Integer.parseInt(splitLine[i]);
						}
					}
					else if(check == size) {
						splitLine = str.split(",");
						last = new int[splitLine.length];
						for(int i = 0; i < splitLine.length; i++) {
							last[i] = Integer.parseInt(splitLine[i]);
						}
					}
					check++;
					str = in.readLine();
				}
					
				int j = 0;
				int k = 0;
				long time;
				
				for(int i = 0; i < size-2; i++) {
					time = Integer.parseInt(in2.readLine());
					if(i != 0) {
						inputTimesFirst[i-1] = time; 
					}
					if( i != (0.5*size)) {
						inputTimesMiddle[j] = time;
						j++;
					}
					if(i != (size-1)){
						inputTimesLast[k] = time;
						k++;
					}
				}
				
				Arrays.sort(inputTimesFirst);
				Arrays.sort(inputTimesMiddle);
				Arrays.sort(inputTimesLast);

				in.close();
				in2.close();
				
				ListCaseDistribution firstList = new ListCaseDistribution(inputTimesFirst);
				ListCaseDistribution middleList = new ListCaseDistribution(inputTimesMiddle);
				ListCaseDistribution lastList = new ListCaseDistribution(inputTimesLast);
				
				int[] firstRanks = firstList.runExperiment(first, length);
				int[] middleRanks = firstList.runExperiment(middle, length);
				int[] lastRanks = firstList.runExperiment(last, length);
				
				PrintWriter outFirst = new PrintWriter(fileFamily + "FirstListDistribution.csv");
				PrintWriter outMiddle = new PrintWriter(fileFamily + "MiddleListDistribution.csv");
				PrintWriter outLast = new PrintWriter(fileFamily + "LastListDistribution.csv");
				
				outFirst.println("Ranks");
				outMiddle.println("Ranks");
				outLast.println("Ranks");
				
				for(int i = 0; i < length; i++) {
					outFirst.println(firstRanks[i]);
					outMiddle.println(middleRanks[i]);
					outLast.println(lastRanks[i]);
				}
					
				outFirst.close();
				outMiddle.close();
				outLast.close();
							
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		//Else this blocks determine the way to format the output
		else {

			ArrayList<DataClass> analysed = analysis.runComparison(size+16);
				
			List<DataClass> output = analysed.subList(15, analysed.size()-1);
			
			try{
				if(typeOfAnalysis.equals("returnTimes") || typeOfAnalysis.equals("rt")) {
					PrintWriter out = new PrintWriter(fileFamily + "Times.csv");				
					out.println("Time to sort");
		
					Collections.sort(output);
					for (int i = 0; i < size; i++) {
						out.println(output.get(i).getTime());
					}
					out.close();
				}
				else if(typeOfAnalysis.equals("returnLists") || typeOfAnalysis.equals("rl")) {
					PrintWriter out = new PrintWriter(fileFamily + "Lists.csv");
	
					Collections.sort(output);
					for (int i = 0; i < size; i++) {
						out.println(output.get(i).listToString());
					}	
					out.close();
				}
				else if(typeOfAnalysis.equals("returnListTimes") || typeOfAnalysis.equals("all") || 
						typeOfAnalysis.equals("rlt") || typeOfAnalysis.equals("rtl")) {
					PrintWriter out = new PrintWriter(fileFamily + ".csv");
	
					Collections.sort(output);
					for (int i = 0; i < size; i++) {
						out.println(output.get(i));
					}
					out.close();
				}
				else if(typeOfAnalysis.equals("unsorted")) {
					PrintWriter out = new PrintWriter(fileFamily + "Times.csv");				
					out.println("Time to sort");
					for (int i = 0; i < size; i++) {
						out.println(output.get(i).getTime());
					}
					out.close();
				}
				else if(typeOfAnalysis.equals("separate") || typeOfAnalysis.equals("s")) {
					
					Collections.sort(output);
					PrintWriter out = new PrintWriter(fileFamily + "Times.csv");				
					out.println("Time to sort");
					PrintWriter out2 = new PrintWriter(fileFamily + "Lists.csv");
					
					for (int i = 0; i < size; i++) {
						out.println(output.get(i).getTime());
						out2.println(output.get(i).listToString());
					}	
					out2.close();
					out.close();
				}
				else if(typeOfAnalysis.equals("deep") || typeOfAnalysis.equals("d")) {
					
					Collections.sort(output);
					ArrayList<DataClass> ret = new ArrayList<DataClass>((int)(0.1*output.size()));
					ret = analysis.runDeepAnalysis(output, size);
						
					PrintWriter out = new PrintWriter(fileFamily + "Times.csv");
					out.println("Time to sort");
					PrintWriter out2 = new PrintWriter(fileFamily + "Lists.csv");
					for (int i = 0; i < size; i++) {
						out.println(ret.get(i).getTime());
						out2.println(ret.get(i).listToString());
					}	
					out2.close();
					out.close();
				}
				
			}catch(FileNotFoundException e) {
				System.out.println("Something was wrong with the file");
				System.out.println(e.getMessage());
			}
		}
	}
}
