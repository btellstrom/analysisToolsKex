package analysisTools.analysisTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

import analysisTools.analysis.*;

/**
 * Unit test for simple App.
 */
public class SpeedAnalysisTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SpeedAnalysisTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SpeedAnalysisTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testListUniqueness()
    {
    	int testLength = 10;
    	boolean test = true;
    	SpeedAnalysis speed = new SpeedAnalysis(testLength);
    	ArrayList<DataClass> data = speed.runComparison(testLength);
    	
    	
    	for(int i = 0; i < testLength; i++) {
    		if(data.get(1).getList()[i] == data.get(2).getList()[2]) {
    			fail();
    		}
    	}
    	assertTrue(test);
    }
}