package analysisTools.mainTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;

import analysisTools.main.*;

/**
 * Unit test for simple App.
 */
public class OptionsTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OptionsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( OptionsTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCommandLine() {
    	String cmdLine = "--type largeRanks --length 1000,2000,5000 --size 1000 --files bullshit";
    	int[] length = {1000, 2000, 5000};
    	String[] args = cmdLine.split("\\s+");
    	Options options = new Options(args);
    	//Correct type
    	assertTrue(options.getTypeOfAnalysis().equals("largeRanks"));
    	//Correct listLengths
    	assertTrue(Arrays.equals(length, options.getListLength()));
    	//Correct nrOfLists
    	assertEquals(options.getNrOfLists(), 1000);
    	//Correct fileFamily
    	assertTrue(options.getFileFamily().equals("bullshit"));
    }
    
    public void testReadFile() {
    	//TODO change so that it loads "/src/test/resources" file instead, and assigns correct from it
    	String correctType = "largeRanks";
    	int[] correctNrOfMeasurements = {100, 200, 300};
    	int[] correctListLength = {100, 200, 500};
    	int[] correctNrOfSorts = {200, 300, 600};
    	int correctNrOfLists = 1;
    	String[] correctSortingAlgorithms = {"QuickSort", "BubbleSort"};
    	String correctListFile = "something";
    	
    	Options options = new Options("src/test/resources/testConfigFile.cfg");
    	assertTrue(options.getTypeOfAnalysis().equals(correctType));
    	assertTrue(Arrays.equals(options.getNrOfMeasurements(),correctNrOfMeasurements));
    	assertTrue(Arrays.equals(options.getListLength(),correctListLength));
    	assertTrue(Arrays.equals(options.getNrOfSorts(),correctNrOfSorts));
    	assertTrue(options.getNrOfLists() == correctNrOfLists);
    	assertTrue(Arrays.equals(options.getSortingAlgorithms(),correctSortingAlgorithms));
    	assertTrue(options.getListFile().equals(correctListFile));
    }
}