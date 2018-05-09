package analysisTools.analysisTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.ArrayList;

import analysisTools.analysis.*;

/**
 * Unit test for simple App.
 */
public class ListCaseDistributionTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ListCaseDistributionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ListCaseDistributionTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testGetSortingRank() {
    	long[] times = {1, 3, 50, 100000000};
    	
    	int[] list = {1, 2, 3, 4, 5};
    	
    	ListCaseDistribution distr = new ListCaseDistribution(times);
    	assertTrue(0 < distr.getSortingRank(list) && distr.getSortingRank(list) < distr.getTimes().length);
    }
    
    
    public void testRunExperiment() {
    	int length = 10;
    	
    	long[] times = {1, 2, 3 ,4 , 5, 10000, 500000, 600000, 700000};
    	
    	int[] list = {5, 1, 4, 5, 5, 2, 91, 2298, 19293}; 
    	
    	ListCaseDistribution distr = new ListCaseDistribution(times);
    	
    	int[] ranks = distr.runExperiment(list, length);
    	
    	assertTrue(ranks.length == length);
    }
}
    
    