package framework;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 04, 2014
 */
public class AtlasScriptExecution 
{
	public static Boolean dbValidation = true;
	public static Boolean scriptRun = true;
	public AtlasScriptExecution(){
		
	}
	public static Boolean getDBValidation(){
		return dbValidation;
	}
	public static Boolean getScriptRun(){
		return scriptRun;
	}
	public static void executeDBValidationOnly(){
		dbValidation = true;
		scriptRun = false;
	}
	
	public static void executeWitoutDBValidation(){
		dbValidation = false;
		scriptRun = true;
	}
	
}
