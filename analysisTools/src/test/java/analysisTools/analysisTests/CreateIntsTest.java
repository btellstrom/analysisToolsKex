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
    	int[] old = new int[length];
    	int[] ne = new int[length];
    	boolean test = false;
    	
    	old = CreateInts.giveInts(length);
    	
    	for(int i = 0; i < length; i++) {
    		ne = CreateInts.giveInts(length);
    		for(int j = 0; j < length; j++) {
    			if(ne[j] == old[j]) {
    				test = false;
    			}
    			else {
    				test = true;
    				break;
    			}
    		}
    		if(test) {
    			break;
    		}
    		old = ne;
    	}
    	assertTrue(test);
    }
    
    public void testAlwaysOneZero() {
    	int testLength = 100;
    	int nrOfInts = 10;
    	boolean test = true;
    	int[] list;
    	for(int i = 0; i < testLength; i++) {
    		list = CreateInts.giveInts(nrOfInts);
    		for(int j = 0; j < nrOfInts; j++) {
    			if(list[j] == 0) {
    				test = false;
    				break;
    			}
    			else {
    				test = true;
    			}
    		}
    		if(test) {
    			assertTrue(test);
    		}
    		
    	}
    	assertTrue(test);
    }
    public void testLength() {
    	int length = 100;
    	int[] list = CreateInts.giveInts(length);
    	assertEquals(list.length, length);
    }
}
