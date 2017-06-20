package framework.pfstoolscontroller;

import java.util.HashSet;
import java.util.Random;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;



/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  August 21, 2013
 */
public class PFSControllerBase extends RationalTestScript
{
	public static HashSet<TestObject> unRegisterList = new HashSet<TestObject>();
	
	
	public  void delayFor(double p_Seconds)
	{
		sleep(p_Seconds);
	}
	public AtlasScriptbase getExecutingScript(){
		
		return AtlasScriptbase.getExecutingScript();
	}
	 protected String generateRandom(int length) {
		    Random random = new Random();
		    char[] digits = new char[length];
		    digits[0] = (char) (random.nextInt(9) + '1');
		    for (int i = 1; i < length; i++) {
		        digits[i] = (char) (random.nextInt(10) + '0');
		    }
		    return new String(digits);
		}
}
