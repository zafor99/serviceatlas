package framework.mswallettoolcontroller;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  March 20, 2014
 */
public abstract class MSWalletToolControllerBase extends RationalTestScript
{
	
	public AtlasScriptbase getExecutingScript(){
		
		return AtlasScriptbase.getExecutingScript();
	}

	public  void delayFor(double p_Seconds)
	{
		sleep(p_Seconds);
	}
	
}
