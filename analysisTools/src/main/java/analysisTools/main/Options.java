package analysisTools.main;

import java.io.*;

public class Options {
	//TODO add so that they can be iterated over
	private String typeOfAnalysis;
	private int[] nrOfMeasurements;
	private int[] nrOfSorts;
	private int[] listLength;
	private int nrOfLists;
	private String[] sortingAlgorithms;
	private String listFile = null;
	private String fileFamily = null;
	private boolean orig = false; //legacy comatibility, should probably change

	public Options(String[] args) {
		int index = 0;
		//TODO add complete string of the commandline arguments?
		for(String arg: args) {
			String[] splitString;
			if(arg.contains("--help")) {
				//TODO rewrite options from commandline and such
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
				splitString = args[index+1].split(",");
				listLength = new int[splitString.length];
				for(int i = 0; i < splitString.length; i++) {
					listLength[i] = Integer.parseInt(splitString[i]);
				}
				
			}
			if (arg.contains("--size")) {
				nrOfLists = Integer.parseInt(args[index+1]);
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
	}
	
	public Options(String file) {
		try {
			this.readFile(file);
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readFile(String file) throws IOException, FileNotFoundException {
		String currentLine;
		BufferedReader f = new BufferedReader(new FileReader(file));
		while((currentLine = f.readLine()) != null) {
			if(currentLine.contains("type")) { //TODO is "this." bad form in constructor?
				this.setType(currentLine.split("=")[1]);
			}
			if(currentLine.contains("nrOfMeasurements")) {
				this.setnrOfMeasurements(currentLine.split("=")[1]);
			}
			if(currentLine.contains("nrOfSorts")) {
				this.setNrOfSorts(currentLine.split("=")[1]);
			}
			if(currentLine.contains("listLength")) {
				this.setListLength(currentLine.split("=")[1]);
			}
			if(currentLine.contains("nrOfLists")) {
				this.setNrOfLists(currentLine.split("=")[1]);
			}
			if(currentLine.contains("sortingAlgorithms")) {
				this.setSortingAlgorithms(currentLine.split("=")[1]);
			}
			if(currentLine.contains("listFile")) {
				this.setListFile(currentLine.split("=")[1]);
			}
			if(currentLine.contains("fileFamily")) {
				this.setFileFamily(currentLine.split("=")[1]);
			}
			
		}
		f.close();
	}
	/*
	 * All different setters and getters
	 * TODO: add 
	 */
	public void setSortingAlgorithms(String algorithms) {
		this.sortingAlgorithms = algorithms.split(",");
	}
	public void setSortingAlgorithms(String[] algorithms) {
		this.sortingAlgorithms = algorithms;
	}
	public void setType(String type) {
		this.typeOfAnalysis = type;
	}
	public void setnrOfMeasurements(String nrOfMeasurements) {
		String[] splitString = nrOfMeasurements.split(",");
		this.nrOfMeasurements = new int[splitString.length];
		for(int i = 0; i < splitString.length; i++) {
			this.nrOfMeasurements[i] = Integer.parseInt(splitString[i]);
		}
	}
	public void setNrOfSorts(String nrOfSorts) {
		String[] splitString = nrOfSorts.split(",");
		this.nrOfSorts = new int[splitString.length];
		for(int i = 0; i < splitString.length; i++) {
			this.nrOfSorts[i] = Integer.parseInt(splitString[i]);
		}
	}
	public void setListLength(String listLength) {
		String[] splitString = listLength.split(",");
		this.listLength = new int[splitString.length];
		for(int i = 0; i < splitString.length; i++) {
			this.listLength[i] = Integer.parseInt(splitString[i]);
		}
	}
	public void setNrOfLists(String nrOfLists) {
		String ifFile = "loadFromFile";
		if(nrOfLists.contains(ifFile)) {
			this.nrOfLists = 0;
			listFile = nrOfLists.substring(nrOfLists.indexOf(ifFile) + ifFile.length()
										   ,nrOfLists.length()-1);
		}
		else {
			this.nrOfLists = Integer.parseInt(nrOfLists);
		}
	}
	public void setListFile(String file) {
		listFile = file;
	}
	public void setFileFamily(String file) {
		fileFamily = file;
	}
	
	public String getTypeOfAnalysis() {
		return typeOfAnalysis;
	}
	public int[] getNrOfSorts() {
		return nrOfSorts;
	}
	public int[] getNrOfMeasurements() {
		return nrOfMeasurements;
	}
	public int[] getListLength() {
		return listLength;
	}
	public int getNrOfLists() {
		return nrOfLists;
	}
	public String[] getSortingAlgorithms() {
		return sortingAlgorithms;
	}
	public String getFileFamily() {
		return fileFamily;
	}
	public String getListFile() {
		return listFile;
	}
	public boolean getOrig() {
		return orig;
	}
}
