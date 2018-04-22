package analysisTools.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import analysisTools.analysis.DataClass;
import analysisTools.analysis.SpeedAnalysis;

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
						+ " Default is returnTimes.");
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
				BufferedReader in2 = new BufferedReader(new FileReader(fileFamily + "Times.csv"));
				List<DataClass> input = new ArrayList<DataClass>((size));
				String[] splitLine;
				int[] list;
				
				try{
					in2.readLine(); //first line is always "time to sort"
					String str = in.readLine();

					while(str != null) {
						splitLine = str.split(",");
						list = new int[splitLine.length];
						for(int i = 0; i < splitLine.length; i++) {
							list[i] = Integer.parseInt(splitLine[i]);
						}
						input.add(new DataClass(Integer.parseInt(in2.readLine()), list));
						str = in.readLine();
					}

					in.close();
					in2.close();
					
					/*
					 * runs the analysis on input and subset of input
					 */
					//TODO better way of setting nrOfIteratiosn
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
