package framework.sapcontroller;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;

public class SAPControllerBase extends RationalTestScript{
	
	public AtlasScriptbase getExecutingScript(){
		
		return AtlasScriptbase.getExecutingScript();
	}
	
	public  void delayFor(double p_Seconds)
	{
		sleep(p_Seconds);
	}
	

}
