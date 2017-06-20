package framework.sapcontroller;

import org.apache.log4j.Logger;

import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPDialogModel;
import framework.sapmodel.SAPTopLevelToolbarModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 16, 2013
 */
public abstract class SAPDialogController extends SAPDialogModel
{
	private static Logger logger = Logger.getLogger(SAPDialogController.class);
	private SAPDialogModel appModel = null;
	private SAPTopLevelTestObject dialog = null;
	
	private SAPTopLevelToolbarController topLevelToolbar = null;
	
	public SAPDialogController(SAPTopLevelTestObject dialog){
		super(dialog);
		this.dialog = dialog;
	}
	
	
	public SAPTopLevelToolbarController topLevelToolbar(){
		return new SAPTopLevelToolbarController();
	}
	
	public void selectMenuItem(Subitem menu){
		topLevelMenuBar().selectMenuItem(menu);
	}
	
	public boolean waitForExistence(){
		logger.info("waitForExistence");
		boolean result = false;
		if(dialog!=null){
			dialog.waitForExistence(120, 5);
			if(dialog.exists()){
				result = true;
			}
		}
		
		return result;
	}
	
	public boolean waitForExistence(int sec){
		logger.info("waitForExistence("+sec+")");
		boolean result = false;
		
		if(dialog!=null){
			dialog.waitForExistence(sec, 10);
			if(dialog.exists()){
				result = true;
			}
		}
		
		return result;
	}

	public void close(){
		logger.info("close");
		dialog.close();
		AtlasScriptbase.getExecutingScript().delayFor(3);
		
	}
	
	public String getStatusBarText(){
		logger.info("getStatusBar");
		return bottomStatusBar().getProperty("Text").toString();
	}
	
	public boolean verifyStatusBar(String vpName){
		logger.info("verifyStatusBar("+vpName+")");
		boolean result = false;
		result = AtlasScriptbase.getExecutingScript().vpManual(vpName, getStatusBarText()).performTest();
		AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
		return result;
	}
	
}
