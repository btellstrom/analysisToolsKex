package analysisTools.analysisTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import analysisTools.analysis.*;

/**
 * Unit test for simple App.
 */
public class CreateIntsTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CreateIntsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CreateIntsTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCreateIntsRandomness()
    {
    	int length = 100;
    	int[] list1 = CreateInts.giveInts(length);
    	int[] list2 = CreateInts.giveInts(length);
        boolean test = true;
        for (int i = 0; i < length; i++) {
        	if(list1[i] == list2[i]) {
        		test = false;
        	}
        	else {
        		test = true;
        	}
        }
        assertTrue(test);
    }
}
